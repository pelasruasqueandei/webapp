package br.com.compliancesoftware.model;

/**
 * Representa��o de um local por coordenadas geogr�ficas.
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
