package br.com.compliancesoftware.control;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.compliancesoftware.model.Local;
import br.com.compliancesoftware.model.Row;

/**
 * Responsável pelo unmarsh do serviço e disponibilização de métricas.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@XStreamAlias(value="DistanceMatrixResponse")
public class DistanceMatrixResponse 
{
	private static String url;
	private String status;
	private String origin_address;
	private String destination_address;
	private Row row;
	
	/**
	 * Modo de viagem no percurso.
	 * @author Douglas Fernandes <douglasf.filho@gmail.com>
	 *
	 */
	public enum Modo
	{
		DIRIGINDO("driving"),
		ANDANDO("walking"),
		PEDALANDO("bicycling");
		
		private String value = "driving";
		
		private Modo(String value)
		{
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return this.value;
		}
		
	}
	
	//Getters e Setters --------------------------------------------------
	public String getUrl() {
		return url;
	}
	
	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public String getDestination_address() {
		return destination_address;
	}

	public void setDestination_address(String destination_address) {
		this.destination_address = destination_address;
	}

	public String getOrigin_address() {
		return origin_address;
	}

	public void setOrigin_address(String origin_address) {
		this.origin_address = origin_address;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	//Fim dos getters e setters
	
	//Construtores
	
	/**
	 * Construtor padrão.
	 */
	public DistanceMatrixResponse()
	{
		
	}
	
	/**
	 * Captura o serviço do Google Maps e traz a informações de distancia e tempo entre dois pontos num mapa.
	 * @param origem = Coordenadas geográficas.
	 * @param destino = Coordenadas geograficas.
	 * @param modo = define qual o tempo de viagem de um ponto a outro com base no modo de transporte.
	 * @return
	 */
	public static DistanceMatrixResponse getInstance(Local origem, Local destino, Modo modo)
	{
		try
		{
			url = "https://maps.googleapis.com/maps/api/distancematrix/xml?"
					+ "origins="+origem.getLat()+","+origem.getLng()+"&"
					+ "destinations="+destino.getLat()+","+destino.getLng()+"&"
					+ "mode="+modo.toString()+"&"
					+ "language=pt-BR";
			
			URL add = new URL(url);
			HttpURLConnection con = (HttpURLConnection)add.openConnection();
			InputStream content = con.getInputStream();
			
			XStream xStream = new XStream(new DomDriver());
			xStream.autodetectAnnotations(true);
			xStream.alias("DistanceMatrixResponse", DistanceMatrixResponse.class);
			
			DistanceMatrixResponse dmr = (DistanceMatrixResponse)xStream.fromXML(content);
			
			content.close();
			con.disconnect();
			
			return dmr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Fim dos construtores
}
