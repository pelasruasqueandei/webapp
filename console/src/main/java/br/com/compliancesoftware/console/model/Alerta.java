package br.com.compliancesoftware.console.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.compliancesoftware.Model.HtmlTableAttribute;
import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.FMT.DateFormat;

/**
 * Model entidade alertas do banco de dados.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "alertas")
public class Alerta implements Serializable
{
	/**
	 * Gerado em 31/01/2017 às 22:46h
	 */
	private static final long serialVersionUID = 3148759000397137509L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@HtmlTableAttribute
	@Column(name = "mensagem", nullable = false)
	private String mensagem;
	
	@HtmlTableAttribute
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	@HtmlTableAttribute
	@Column(name = "visto", nullable = false)
	private boolean visto;

	//Getters e setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Calendar getData() {
		return data;
	}
	
	public String getFmtData() {
		return FMT.getStringFromCalendar(this.data, DateFormat.DMY);
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public boolean getVisto() {
		return visto;
	}
	
	public String getFmtVisto() {
		if(this.visto)
			return "Sim";
		else
			return "Não";
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}
	
	//Fim dos getters e setters
}
