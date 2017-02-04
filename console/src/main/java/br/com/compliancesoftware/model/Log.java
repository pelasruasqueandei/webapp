package br.com.compliancesoftware.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.compliancesoftware.Model.HtmlTableAttribute;
import br.com.compliancesoftware.model.auxModels.FMT;
import br.com.compliancesoftware.model.auxModels.FMT.DateFormat;

/**
 * Entidade relacional do banco de dados conectada aos Logs de ações do sistema.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
@Entity
@Table(name = "logs")
public class Log
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@HtmlTableAttribute
	@Column(name = "acao", nullable = false)
	private String acao;
	
	@HtmlTableAttribute
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Calendar getData() {
		return data;
	}
	
	public String getFmtData() {
		return FMT.getStringFromCalendar(this.data, DateFormat.EDMY);
	}

	public void setData(Calendar data) {
		try{
			if(data == null)
				this.data = FMT.getAgora();
			else
				this.data = data;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
