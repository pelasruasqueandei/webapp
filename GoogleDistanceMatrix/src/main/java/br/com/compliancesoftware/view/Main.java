package br.com.compliancesoftware.view;

import br.com.compliancesoftware.control.DistanceMatrixResponse;
import br.com.compliancesoftware.control.DistanceMatrixResponse.Modo;
import br.com.compliancesoftware.model.Local;

/**
 * Classe principal apenas para apresentação da API.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Exemplo de execução de unmarsh do GoogleMapsAPI (sem Key)\nUsa as libs XStream-1.4.9");
		
		Local origem = new Local(-8.1866353, -34.9233061);
		Local destino = new Local(-8.2928295, -35.045615);
		
		Modo modo = Modo.PEDALANDO;
		DistanceMatrixResponse dmr = DistanceMatrixResponse.getInstance(origem, destino, modo);
		
		try
		{
			System.out.println("Status: "+dmr.getStatus());
			System.out.println("URL: "+dmr.getUrl());
			System.out.println("Origem: "+dmr.getOrigin_address()+" | LAT: "+origem.getLat()+" LNG: "+origem.getLng());
			System.out.println("Destino: "+dmr.getDestination_address()+" | LAT: "+destino.getLat()+" LNG: "+destino.getLng());
			System.out.println("______________________________________________________________________________________________");
			System.out.println("Distância: "+dmr.getRow().getElement().getDistance().getText());
			System.out.println("Tempo de viagem "+modo.toString()+": "+dmr.getRow().getElement().getDuration().getText());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
