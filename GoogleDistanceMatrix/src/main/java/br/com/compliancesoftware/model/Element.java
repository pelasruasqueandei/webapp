/**
 * 
 */
package br.com.compliancesoftware.model;

/**
 * Representa um elemento do unmarsh
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class Element 
{
	private Duration duration;
	private Distance distance;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Distance getDistance() {
		return distance;
	}
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
}
