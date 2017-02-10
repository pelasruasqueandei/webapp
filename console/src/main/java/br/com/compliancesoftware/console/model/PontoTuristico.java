package br.com.compliancesoftware.console.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Forma de representar um pono turistico no mapa.
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
@Entity
@Table(name = "pontos")
public class PontoTuristico {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "tipo", nullable = false)
	private String tipo; //Local ou Monumento
	
	@Column(name = "altitude", nullable = false)
	private double altitude;
	
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
	@Column(name = "endereco", nullable = false)
	private String endereco;
	
	@Column(name = "historia", nullable = false)
	private String historia;
	
	@Column(name = "foto", nullable = false)
	private byte[] foto;

	//Setters e getters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	public byte[] getFoto() {
		return this.foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
