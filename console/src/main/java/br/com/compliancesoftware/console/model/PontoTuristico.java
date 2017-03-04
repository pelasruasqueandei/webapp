package br.com.compliancesoftware.console.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import br.com.compliancesoftware.console.model.auxModels.FMT;
import br.com.compliancesoftware.console.model.auxModels.FMT.DateFormat;

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
	
	@Column(name = "latitude", nullable = false)
	private String latitude;
	
	@Column(name = "longitude", nullable = false)
	private String longitude;
	
	@Column(name = "endereco", nullable = false)
	private String endereco;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@Column(name = "cep", nullable = false)
	private String cep;

	@Column(name = "historia", length = 8000,nullable = false)
	private String historia;
	
	@Column(name = "funciona_segunda", nullable = false)
	private boolean funcSegunda;
	
	@Column(name = "horario_segunda_inicio", nullable = false)
	private String horarioSegundaInicio;
	
	@Column(name = "horario_segunda_intervalo_in", nullable = false)
	private String horarioSegundaIntervaloIn;

	@Column(name = "horario_segunda_intervalo_out", nullable = false)
	private String horarioSegundaIntervaloOut;
	
	@Column(name = "horario_segunda_fim", nullable = false)
	private String horarioSegundaFim;
	
	@Column(name = "funciona_terca", nullable = false)
	private boolean funcTerca;
	
	@Column(name = "horario_terca_inicio", nullable = false)
	private String horarioTercaInicio;
	
	@Column(name = "horario_terca_intervalo_in", nullable = false)
	private String horarioTercaIntervaloIn;

	@Column(name = "horario_terca_intervalo_out", nullable = false)
	private String horarioTercaIntervaloOut;
	
	@Column(name = "horario_terca_fim", nullable = false)
	private String horarioTercaFim;
	
	@Column(name = "funciona_quarta", nullable = false)
	private boolean funcQuarta;
	
	@Column(name = "horario_quarta_inicio", nullable = false)
	private String horarioQuartaInicio;
	
	@Column(name = "horario_quarta_intervalo_in", nullable = false)
	private String horarioQuartaIntervaloIn;

	@Column(name = "horario_quarta_intervalo_out", nullable = false)
	private String horarioQuartaIntervaloOut;
	
	@Column(name = "horario_quarta_fim", nullable = false)
	private String horarioQuartaFim;
	
	@Column(name = "funciona_quinta", nullable = false)
	private boolean funcQuinta;
	
	@Column(name = "horario_quinta_inicio", nullable = false)
	private String horarioQuintaInicio;
	
	@Column(name = "horario_quinta_intervalo_in", nullable = false)
	private String horarioQuintaIntervaloIn;

	@Column(name = "horario_quinta_intervalo_out", nullable = false)
	private String horarioQuintaIntervaloOut;
	
	@Column(name = "horario_quinta_fim", nullable = false)
	private String horarioQuintaFim;
	
	@Column(name = "funciona_sexta", nullable = false)
	private boolean funcSexta;
	
	@Column(name = "horario_sexta_inicio", nullable = false)
	private String horarioSextaInicio;
	
	@Column(name = "horario_sexta_intervalo_in", nullable = false)
	private String horarioSextaIntervaloIn;

	@Column(name = "horario_sexta_intervalo_out", nullable = false)
	private String horarioSextaIntervaloOut;
	
	@Column(name = "horario_sexta_fim", nullable = false)
	private String horarioSextaFim;
	
	@Column(name = "funciona_sabado", nullable = false)
	private boolean funcSabado;
	
	@Column(name = "horario_sabado_inicio", nullable = false)
	private String horarioSabadoInicio;
	
	@Column(name = "horario_sabado_intervalo_in", nullable = false)
	private String horarioSabadoIntervaloIn;

	@Column(name = "horario_sabado_intervalo_out", nullable = false)
	private String horarioSabadoIntervaloOut;
	
	@Column(name = "horario_sabado_fim", nullable = false)
	private String horarioSabadoFim;
	
	@Column(name = "funciona_domingo", nullable = false)
	private boolean funcDomingo;
	
	@Column(name = "horario_domingo_inicio", nullable = false)
	private String horarioDomingoInicio;
	
	@Column(name = "horario_domingo_intervalo_in", nullable = false)
	private String horarioDomingoIntervaloIn;

	@Column(name = "horario_domingo_intervalo_out", nullable = false)
	private String horarioDomingoIntervaloOut;
	
	@Column(name = "horario_domingo_fim", nullable = false)
	private String horarioDomingoFim;
	
	@Column(name = "atualizacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar atualizacao;
	
	@Column(name = "foto", length = 15000000, nullable = false)
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
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
	
	public void setFoto() {
		try {
			String hexImage = "89504E470D0A1A0A0000000D49484452000000C0000000C0080000000077B733DB00000239494441547801EDDA090AEB201446E1EE7F4F3F820409489080040948E84ADE3CCFF199780BE7ACC0AF73AFF7F17CF1000000602E0000000000000000000000000000000000000000000C08000000000000000000000000FB1A833E15E2BABF16608B4E3FE4E2F62A802379FD329F8E570024A7DFE6927940F1FA63BED8062CFA6B8B61409DF40F4DD52A6077FAA7DC6E13B03BE97EC1A3FFF95B040600D5E944AE9A034C3AD5640DB0E8648B2D40D1E98A2980D7E9BC25405243C90EE0706AC81D6600494D253300AFA6BC15C0A6C6362380A8C6A211805363CE06605773BB09C0AAE6561380A8E6A209405073C10440ED09C0F301E000C04B08407B7C91F153E23F7FCCF1739A3F341DFE52F2A79EB14AEBE75062B4C870B7D7789D0B0EAE98DA2FF9B866E5A2BB7DD580650FD66DDA179E583963E9AF7DED92C557568F6D05000000000000000000C05400009492D21C267D5308734AA59807EC3905AF3FE443CABB4D40CD4BD03F16965C4D016A8E5E27F331571B806DF16ACC2FDB68C0169DDAEB316D798C3C7D0F4333A026AF6EF9546F0694599D9BCB8D803CE982A67C13207B5D94CF3700CAA40B9BCAC5803AEBE2E67A256075BA3CB75E0638826E291CD7008AD34DB9720520EBC6727F40D4ADC5DE80A89B8B7D01AB6E6FED09D834A0AD1FE0701A903BBA01660D69EE05281A54E904081A54E803A81A56ED025835ACB50B206858A10BC06958AE0B4003EB01385E1D500000000000000000000000000000000000000000000000000000000000000000C0D7DE02BAA0BAA278A4C57C0000000049454E44AE426082";
			char[] hex = new char[hexImage.length()];
			for(int i = 0;i < hexImage.length();i++)
			{
				hex[i] = hexImage.charAt(i);
			}
			byte[] data;
			data = Hex.decodeHex(hex);
			this.foto = data;
		} catch (DecoderException e) {
			this.foto = null;
			e.printStackTrace();
		}
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public boolean getFuncSegunda() {
		return funcSegunda;
	}

	public void setFuncSegunda(boolean funcSegunda) {
		this.funcSegunda = funcSegunda;
	}

	public String getHorarioSegundaInicio() {
		return horarioSegundaInicio;
	}

	public void setHorarioSegundaInicio(String horarioSegundaInicio) {
		this.horarioSegundaInicio = horarioSegundaInicio;
	}

	public String getHorarioSegundaIntervaloIn() {
		return horarioSegundaIntervaloIn;
	}

	public void setHorarioSegundaIntervaloIn(String horarioSegundaIntervaloIn) {
		this.horarioSegundaIntervaloIn = horarioSegundaIntervaloIn;
	}

	public String getHorarioSegundaIntervaloOut() {
		return horarioSegundaIntervaloOut;
	}

	public void setHorarioSegundaIntervaloOut(String horarioSegundaIntervaloOut) {
		this.horarioSegundaIntervaloOut = horarioSegundaIntervaloOut;
	}

	public String getHorarioSegundaFim() {
		return horarioSegundaFim;
	}

	public void setHorarioSegundaFim(String horarioSegundaFim) {
		this.horarioSegundaFim = horarioSegundaFim;
	}

	public boolean getFuncTerca() {
		return funcTerca;
	}

	public void setFuncTerca(boolean funcTerca) {
		this.funcTerca = funcTerca;
	}

	public String getHorarioTercaInicio() {
		return horarioTercaInicio;
	}

	public void setHorarioTercaInicio(String horarioTercaInicio) {
		this.horarioTercaInicio = horarioTercaInicio;
	}

	public String getHorarioTercaIntervaloIn() {
		return horarioTercaIntervaloIn;
	}

	public void setHorarioTercaIntervaloIn(String horarioTercaIntervaloIn) {
		this.horarioTercaIntervaloIn = horarioTercaIntervaloIn;
	}

	public String getHorarioTercaIntervaloOut() {
		return horarioTercaIntervaloOut;
	}

	public void setHorarioTercaIntervaloOut(String horarioTercaIntervaloOut) {
		this.horarioTercaIntervaloOut = horarioTercaIntervaloOut;
	}

	public String getHorarioTercaFim() {
		return horarioTercaFim;
	}

	public void setHorarioTercaFim(String horarioTercaFim) {
		this.horarioTercaFim = horarioTercaFim;
	}

	public boolean getFuncQuarta() {
		return funcQuarta;
	}

	public void setFuncQuarta(boolean funcQuarta) {
		this.funcQuarta = funcQuarta;
	}

	public String getHorarioQuartaInicio() {
		return horarioQuartaInicio;
	}

	public void setHorarioQuartaInicio(String horarioQuartaInicio) {
		this.horarioQuartaInicio = horarioQuartaInicio;
	}

	public String getHorarioQuartaIntervaloIn() {
		return horarioQuartaIntervaloIn;
	}

	public void setHorarioQuartaIntervaloIn(String horarioQuartaIntervaloIn) {
		this.horarioQuartaIntervaloIn = horarioQuartaIntervaloIn;
	}

	public String getHorarioQuartaIntervaloOut() {
		return horarioQuartaIntervaloOut;
	}

	public void setHorarioQuartaIntervaloOut(String horarioQuartaIntervaloOut) {
		this.horarioQuartaIntervaloOut = horarioQuartaIntervaloOut;
	}

	public String getHorarioQuartaFim() {
		return horarioQuartaFim;
	}

	public void setHorarioQuartaFim(String horarioQuartaFim) {
		this.horarioQuartaFim = horarioQuartaFim;
	}

	public boolean getFuncQuinta() {
		return funcQuinta;
	}

	public void setFuncQuinta(boolean funcQuinta) {
		this.funcQuinta = funcQuinta;
	}

	public String getHorarioQuintaInicio() {
		return horarioQuintaInicio;
	}

	public void setHorarioQuintaInicio(String horarioQuintaInicio) {
		this.horarioQuintaInicio = horarioQuintaInicio;
	}

	public String getHorarioQuintaIntervaloIn() {
		return horarioQuintaIntervaloIn;
	}

	public void setHorarioQuintaIntervaloIn(String horarioQuintaIntervaloIn) {
		this.horarioQuintaIntervaloIn = horarioQuintaIntervaloIn;
	}

	public String getHorarioQuintaIntervaloOut() {
		return horarioQuintaIntervaloOut;
	}

	public void setHorarioQuintaIntervaloOut(String horarioQuintaIntervaloOut) {
		this.horarioQuintaIntervaloOut = horarioQuintaIntervaloOut;
	}

	public String getHorarioQuintaFim() {
		return horarioQuintaFim;
	}

	public void setHorarioQuintaFim(String horarioQuintaFim) {
		this.horarioQuintaFim = horarioQuintaFim;
	}

	public boolean getFuncSexta() {
		return funcSexta;
	}

	public void setFuncSexta(boolean funcSexta) {
		this.funcSexta = funcSexta;
	}

	public String getHorarioSextaInicio() {
		return horarioSextaInicio;
	}

	public void setHorarioSextaInicio(String horarioSextaInicio) {
		this.horarioSextaInicio = horarioSextaInicio;
	}

	public String getHorarioSextaIntervaloIn() {
		return horarioSextaIntervaloIn;
	}

	public void setHorarioSextaIntervaloIn(String horarioSextaIntervaloIn) {
		this.horarioSextaIntervaloIn = horarioSextaIntervaloIn;
	}

	public String getHorarioSextaIntervaloOut() {
		return horarioSextaIntervaloOut;
	}

	public void setHorarioSextaIntervaloOut(String horarioSextaIntervaloOut) {
		this.horarioSextaIntervaloOut = horarioSextaIntervaloOut;
	}

	public String getHorarioSextaFim() {
		return horarioSextaFim;
	}

	public void setHorarioSextaFim(String horarioSextaFim) {
		this.horarioSextaFim = horarioSextaFim;
	}

	public boolean getFuncSabado() {
		return funcSabado;
	}

	public void setFuncSabado(boolean funcSabado) {
		this.funcSabado = funcSabado;
	}

	public String getHorarioSabadoInicio() {
		return horarioSabadoInicio;
	}

	public void setHorarioSabadoInicio(String horarioSabadoInicio) {
		this.horarioSabadoInicio = horarioSabadoInicio;
	}

	public String getHorarioSabadoIntervaloIn() {
		return horarioSabadoIntervaloIn;
	}

	public void setHorarioSabadoIntervaloIn(String horarioSabadoIntervaloIn) {
		this.horarioSabadoIntervaloIn = horarioSabadoIntervaloIn;
	}

	public String getHorarioSabadoIntervaloOut() {
		return horarioSabadoIntervaloOut;
	}

	public void setHorarioSabadoIntervaloOut(String horarioSabadoIntervaloOut) {
		this.horarioSabadoIntervaloOut = horarioSabadoIntervaloOut;
	}

	public String getHorarioSabadoFim() {
		return horarioSabadoFim;
	}

	public void setHorarioSabadoFim(String horarioSabadoFim) {
		this.horarioSabadoFim = horarioSabadoFim;
	}

	public boolean getFuncDomingo() {
		return funcDomingo;
	}

	public void setFuncDomingo(boolean funcDomingo) {
		this.funcDomingo = funcDomingo;
	}

	public String getHorarioDomingoInicio() {
		return horarioDomingoInicio;
	}

	public void setHorarioDomingoInicio(String horarioDomingoInicio) {
		this.horarioDomingoInicio = horarioDomingoInicio;
	}

	public String getHorarioDomingoIntervaloIn() {
		return horarioDomingoIntervaloIn;
	}

	public void setHorarioDomingoIntervaloIn(String horarioDomingoIntervaloIn) {
		this.horarioDomingoIntervaloIn = horarioDomingoIntervaloIn;
	}

	public String getHorarioDomingoIntervaloOut() {
		return horarioDomingoIntervaloOut;
	}

	public void setHorarioDomingoIntervaloOut(String horarioDomingoIntervaloOut) {
		this.horarioDomingoIntervaloOut = horarioDomingoIntervaloOut;
	}

	public String getHorarioDomingoFim() {
		return horarioDomingoFim;
	}

	public void setHorarioDomingoFim(String horarioDomingoFim) {
		this.horarioDomingoFim = horarioDomingoFim;
	}

	public Calendar getAtualizacao() {
		return atualizacao;
	}

	public String getFmtAtualizacao() {
		return FMT.getStringFromCalendar(atualizacao, DateFormat.DMYHM);
	}
	
	public void setAtualizacao(Calendar atualizacao) {
		this.atualizacao = atualizacao;
	}
}
