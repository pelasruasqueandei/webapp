package br.com.compliancesoftware.model;

/**
 * Representação de um local por coordenadas geográficas.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Local 
{
	private double lat = 0;
	private double lng = 0;
	
	public Local(double latitude, double longitude)
	{
		this.lat = latitude;
		this.lng = longitude;
	}
	
	public double getLat() 
	{
		return lat;
	}
	
	public double getLng() 
	{
		return lng;
	}
}
