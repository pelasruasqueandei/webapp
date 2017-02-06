package br.com.compliancesoftware.Model;
/**
 * 
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import br.com.compliancesoftware.Model.Params.DateFormat;

/**
 * Esta classe é a responsável por construir uma tabela dinâmica em torno de uma lista de objectos de mesma classe.
 * Para funcionar, é necessário que tenha o framework jQuery instalado no front-end.
 * O objeto a ser listado deve, por padrão, ser um POJO e possuir, obrigatoriamente, um atributo "id" com seus respectivos métodos getId e setId.
 * 
 * PS.: As actions desta classe obedencem ao framework Bootstrap, de forma que deve ter o mesmo instalado no front-end, já no back-end, você deve ter os devidos mapeamentos
 * para as requisições que podem ser feitas (encorajando o uso de Ajax)
 * 
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class HtmlTableBuilder 
{
	private String classe;
	private String id;
	private String sumario;
	private String titulo;
	private ArrayList<String> cabecalhos;
	private String cabecalho_width;
	private ArrayList<Linha> linhas;
	private ArrayList<?> beanList;
	private boolean updateAction = false;
	private boolean detailAction = false;
	private boolean deleteAction = false;
	private Params params = null;
	
	/**
	 * Caso "true" você habilita uma coluna a mais na tabela em cada item da mesma permitindo que seja criado um link
	 * para atualizar este item através da requisição "atualizar\<nome_da_classe\>?id=\<id_objeto_a_ser_atualizado\>
	 * @param updateAction
	 */
	public void setUpdateAction(boolean updateAction) {
		this.updateAction = updateAction;
	}

	/**
	 * Caso "true" você habilita uma coluna a mais na tabela em cada item da mesma permitindo que seja criado um link
	 * para detalhar este item através da requisição "detalhar\<nome_da_classe\>?id=\<id_objeto_a_ser_detalhado\>
	 * @param detailAction
	 */
	public void setDetailAction(boolean detailAction) {
		this.detailAction = detailAction;
	}

	/**
	 * Caso "true" você habilita uma coluna a mais na tabela em cada item da mesma permitindo que seja criado um link
	 * para remover este item através da requisição "remover\<nome_da_classe\>?id=\<id_objeto_a_ser_removido\>
	 * @param deleteAction
	 */
	public void setDeleteAction(boolean deleteAction) {
		this.deleteAction = deleteAction;
	}

	/**
	 * Configura os parametros de construção de tabela.
	 * @param params
	 */
	public void setParams(Params params) {
		this.params = params;
	}
	
	/**
	 * Construtor da classe que recebe os parametros fundamentais de construção:
	 * @param classe = atributo class da tabela o qual pode ser usado para customiza-la no front-end
	 * @param id = atributo id da tabela o qual pode ser usado para identifica-la no front-end
	 * @param sumario = atributo sumary da tabela
	 * @param titulo = atributo caption da tabela
	 * @param beanList = Lista de Objetos que irão popular a tabela. (Uma lista de objetos do mesmo tipo)
	 */
	public HtmlTableBuilder(String classe, String id, String sumario, String titulo, ArrayList<?> beanList)
	{
		if(classe == null || classe.equals(null) || classe.equals(""))
			this.classe = "tableclass";
		else
			this.classe = classe;
		
		if(id == null || id.equals(null) || id.equals(""))
			this.id = "tableid";
		else
			this.id = id;
		
		if(sumario == null || sumario.equals(null) || sumario.equals(""))
			this.sumario = "Tabela construída dinâmicamente";
		else
			this.sumario = sumario;
		
		if(titulo == null || titulo.equals(null) || titulo.equals(""))
			this.titulo = "Tabela construída dinâmicamente";
		else
			this.titulo = titulo;
		
		this.cabecalhos = new ArrayList<String>();
		this.cabecalho_width = "0%";
		this.linhas = new ArrayList<Linha>();
		Linha vazia = new Linha(0, false, new ArrayList<Object>());
		this.linhas.add(vazia);
		
		if(beanList != null && beanList.size() > 0)
		{
			this.beanList = beanList;
			Object model = beanList.get(0);
			Field[] atributos = model.getClass().getDeclaredFields();
			for(Field atb : atributos)
			{
				if(atb.isAnnotationPresent(HtmlTableAttribute.class))
				{
					String cabecalho = atb.getName().substring(0,1).toUpperCase()+atb.getName().substring(1);
					this.cabecalhos.add(cabecalho);
				}
			}
			
			int cabqtd = cabecalhos.size();
			int perc = (int)(100.0d / (double)cabqtd);
			String cabwid = perc+"%";
			
			this.cabecalho_width = cabwid;
			
			ArrayList<String> metodos = new ArrayList<String>();
			for(String cab : this.cabecalhos)
			{
				String metodo = "get"+cab;
				metodos.add(metodo);
			}
			
			ArrayList<Object> celulas = null;
			this.linhas = new ArrayList<Linha>();
			boolean par = false;
			
			for(Object item : beanList)
			{
				par = !par;
				celulas = new ArrayList<Object>();
				
				try
				{
					for(String metname : metodos)
					{
						Object valor = item.getClass().getMethod(metname).invoke(item);
						celulas.add(valor);
					}
					long index = (long)item.getClass().getMethod("getId").invoke(item);
					Linha linha = new Linha(index, par,celulas);
					this.linhas.add(linha);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Método usado para facilitar a atualização de uma beanList sem precisar reinstancia-la
	 * @param beanList
	 * @return
	 */
	public String updateBeanList(ArrayList<?> beanList)
	{
		this.beanList = beanList;
		HtmlTableBuilder builder = new HtmlTableBuilder(classe,id,sumario,titulo,beanList);
		builder.setDeleteAction(deleteAction);
		builder.setDetailAction(detailAction);
		builder.setUpdateAction(updateAction);
		builder.setParams(params);
		
		return builder.getTable();
	}
	
	/**
	 * Classe que representa uma linha da tabela.
	 * @author Douglas Fernandes <douglasf.filho@gmail.com>
	 *
	 */
	public class Linha
	{
		private long id;
		private boolean par;
		private ArrayList<Object> celulas;
		
		/**
		 * Construtor da linha
		 * @param id = usado para as actions, deve ser, de preferencia, o id do objeto
		 * @param par = identifica se é uma linha par ou não (usado apenas para customização no front-end)
		 * @param celulas = lista de objetos que representa a popularização das celulas da linha, atento à ordem para que esteja igual à ordem do cabeçalho.
		 */
		public Linha(long id, boolean par, ArrayList<Object> celulas)
		{
			this.id = id;
			this.par = par;
			this.celulas = celulas;
		}
		
		public long getId() {
			return id;
		}
		
		public boolean getPar() {
			return par;
		}
		
		public ArrayList<Object> getCelulas() {
			return celulas;
		}
	}
	
	/**
	 * Método usado para pegar a tabela em forma de String e assim lança-la no front-end através de um Model.
	 * @return
	 */
	public String getTable()
	{
		String table = "<div id=\"tableparentdiv\"><div id=\"tablediv_id\" class=\"tablediv_class\">																						"+
				"		<table id=\""+id+"\" summary=\""+sumario+"\" class=\"table table-responsive "+classe+"\" cellpadding=\"3\">						"+
				"			<caption>"+titulo+"</caption>																						"+
				"			<thead>																													"+
				"				<tr>																												";
				int count = 0;
				for(String cabecalho : cabecalhos)
				{
					table += "<th id=\""+count+"\" scope=\"col\" width=\""+cabecalho_width+"\">"+cabecalho+"</th>";
					count++;
				}
		
				table += "				</tr>                                                                                                               "+
				"			</thead>                                                                                                                "+
				"			<tbody>                                                                                                                 ";
				
				for(Linha linha : linhas)
				{
					if(linha.getPar())
						table += "<tr role=\"row\" bgcolor=\"#ffffff\">";
					else
						table += "<tr role=\"row\" bgcolor=\"#fafafb\">";
					for(Object celula : linha.getCelulas())
					{
						String type = celula.getClass().getName();
						if(type.endsWith("Calendar"))
						{
							String formatter = "dd/MM/yyyy HH:mm:ss";
							if(params != null) {
								DateFormat formato = params.getFormatoDeData();
								if(formato != null)
									formatter = formato.toString();
							}
							
							Calendar cell = (Calendar)celula;
							String cellvalue = new SimpleDateFormat(formatter).format(cell.getTime());
							table += "<td role=\"gridcell\">"+cellvalue+"</td>";
						}
						else if(type.endsWith("Date"))
						{
							String formatter = "dd/MM/yyyy HH:mm:ss";
							if(params != null) {
								DateFormat formato = params.getFormatoDeData();
								if(formato != null)
									formatter = formato.toString();
							}
							
							Date cell = (Date)celula;
							String cellvalue = new SimpleDateFormat(formatter).format(cell);
							table += "<td role=\"gridcell\">"+cellvalue+"</td>";
						}
						else if(type.endsWith("boolean") || type.endsWith("Boolean"))
						{
							boolean cell = (boolean)celula;
							if(cell)
								table += "<td role=\"gridcell\">"+"Sim"+"</td>";
							else
								table += "<td role=\"gridcell\">"+"N&atildeo"+"</td>";
						}
						else if(type.endsWith("double") || type.endsWith("Double"))
						{
							double cell = (double)celula;
							String cellvalue = String.format("%.2f", cell);
							table += "<td role=\"gridcell\">"+cellvalue+"</td>";
						}
						else
							table += "<td role=\"gridcell\">"+celula+"</td>";
					}
					
					if(updateAction || deleteAction || detailAction)
					{
						String beanName = beanList.get(0).getClass().getSimpleName();
						table += "<td role=\"gridcell\">";
						
						if(updateAction)
							table += "<br><a><button onclick=\"atualizar"+beanName+"("+linha.getId()+")\" class=\"btn btn-info\">Atualizar</button></a>";
						if(detailAction)
							table += "<br><a><button onclick=\"detalhar"+beanName+"("+linha.getId()+")\" class=\"btn btn-info\">Detalhar</button></a>";
						if(deleteAction)
						{
							table += "<br><a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal"+linha.getId()+"\">Remover</a>"+
			            	         "<div id=\"myModal"+linha.getId()+"\" class=\"modal fade\" role=\"dialog\">"+
			  						 	"<div class=\"modal-dialog\">"+
			  						 		"<div class=\"modal-content\">"+
			  						 			"<div class=\"modal-header\">"+
			  						 				"<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>"+
			  						 				"<h4 class=\"modal-title\">Tem certeza?</h4>"+
			  						 			"</div>"+
			  						 			"<div class=\"modal-body\">"+
			  						 				"<p>Deseja realmente remover este item?</p>"+
			  						 			"</div>"+
			  						 			"<div class=\"modal-footer\">"+
			  						 				"<a><button onclick=\"remover"+beanName+"("+linha.getId()+")\" class=\"btn btn-info\">Sim</button></a>"+
			  						 			"</div>"+
			  						 		"</div>"+
			  						 	"</div>"+
			  						"</div>";
						}
						table += "</tr>";
					}
					
					table += "</tr>";
				}
				
				table += "			</tbody>                                                                                                                "+
				"		</table>                                                                                                                    "+
				"	</div></div>";
		
		String style = "<style>															"+
				"	table."+classe+" {border-collapse: collapse; width: 100%} "+
				"	table."+classe+" tr td {border:1px solid #d9d9d9;}        "+
				"	table."+classe+" thead{background: #ebedf0;}              "+
				"	table."+classe+" caption{background: #fafafb; text-align: center;}              "+
				"	.tablediv_class {width: 70%;margin: 0 auto;}                    "+
				"</style>";
		table += style;
		
		String script = "<script>																"+
				"	var color;                                                          "+
				"	function hexc(colorval) {                                           "+
				"		var parts = colorval.match(/^rgb\\((\\d+),\\s*(\\d+),\\s*(\\d+)\\)$/); "+
				"		delete(parts[0]);                                               "+
				"		for (var i = 1; i <= 3; ++i) {                                  "+
				"			parts[i] = parseInt(parts[i]).toString(16);                 "+
				"			if (parts[i].length == 1) parts[i] = '0' + parts[i];        "+
				"		}                                                               "+
				"		color = '#' + parts.join('');                                   "+
				"	}                                                                   "+
				"			                                                            "+
				"	$(\"tbody>tr\").mouseover(function(){                                 "+
				"		var bg = $(this).css('backgroundColor');                        "+
				"		hexc(bg);                                                       "+
				"				                                                        "+
				"		$(this).css('background-color','#6f92dc');                      "+
				"	});                                                                 "+
				"			                                                            "+
				"	$(\"tbody>tr\").mouseout(function(){                                  "+
				"		$(this).css('background-color',color);                          "+
				"	});                                                                 "+
				"			                                                            "+
				"$(\"th\").click(function(){							"+
				"	var id = $(this).attr(\"id\");                    "+
				"	$.get(\"get"+beanList.get(0).getClass().getSimpleName()+"TableOrderBy?field=\"+id,function(data){  "+
				"		$(\"div#tablediv_id\").parent().html(data);            "+
				"	});                                             "+
				"});                                                ";
				
				if(updateAction) {
					script += ""+
					"function atualizar"+beanList.get(0).getClass().getSimpleName()+"(id) {"+
		    			"var url = \"atualizar"+beanList.get(0).getClass().getSimpleName()+"\";"+
		    			"var params = {'id':id};"+
		    			"$.post(url,params,function(resposta){"+
		    				"$(\"div#tablediv_id\").parent().html(resposta);"+
		    			"});"+
		    		"}";
				}
				
				if(deleteAction) {
					script += ""+
					"function remover"+beanList.get(0).getClass().getSimpleName()+"(id) {"+
		    			"var url = \"remover"+beanList.get(0).getClass().getSimpleName()+"\";"+
		    			"var params = {'id':id};"+
		    			"$.post(url,params,function(resposta){"+
		    				"$(\"div#tablediv_id\").parent().html(resposta);"+
		    			"});"+
		    		"}";
				}
		
				script += "</script>";
		
		table += script;
		
		return table;
	}
	
	/**
	 * Método que ordena a tabela de acordo com um determinado campo e retorna a mesma na ordem solicitada.
	 * @param field = id do campo que será ordenado (deve ser um numero a partir de 0)
	 * @param order = ordem que será renderizada a tabela (caso 0 = crescente | caso 1 = decrescente)
	 * @return
	 */
	public String getTableOrderBy(String field, String order)
	{
		int campo = Integer.parseInt(field);
		int ordem = Integer.parseInt(order);
		
		Object model = beanList.get(0);
		
		Field[] fields = model.getClass().getDeclaredFields();
		ArrayList<String> campos = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
		for(Field f : fields){
			if(f.isAnnotationPresent(HtmlTableAttribute.class)){
				campos.add(f.getName());
				types.add(f.getType().getName());
			}
		}
		
		String f1 = campos.get(campo);
		String p1 = f1.substring(0, 1).toUpperCase();
		String p2 = f1.substring(1);
		final String getter = "get"+p1+p2;
		
		final String type = types.get(campo);
		
		if(ordem == 0)
		{
			Collections.sort(beanList, new Comparator<Object>()
					{

						@Override
						public int compare(Object o1, Object o2) 
						{
							try
							{
								if(type.endsWith("int") || type.endsWith("Integer"))
								{
									int value1 = (int)o1.getClass().getMethod(getter).invoke(o1);
									int value2 = (int)o2.getClass().getMethod(getter).invoke(o2);
									if(value1 > value2)
										return 1;
									else if(value1 < value2)
										return -1;
									else
										return 0;
								}
								else if(type.endsWith("double") || type.endsWith("Double"))
								{
									double value1 = (double)o1.getClass().getMethod(getter).invoke(o1);
									double value2 = (double)o2.getClass().getMethod(getter).invoke(o2);
									if(value1 > value2)
										return 1;
									else if(value1 < value2)
										return -1;
									else
										return 0;
								}
								else if(type.endsWith("Calendar"))
								{
									Calendar value1 = (Calendar)o1.getClass().getMethod(getter).invoke(o1);
									Calendar value2 = (Calendar)o2.getClass().getMethod(getter).invoke(o2);
									return value1.compareTo(value2);
								}
								else if(type.endsWith("Date"))
								{
									Date value1 = (Date)o1.getClass().getMethod(getter).invoke(o1);
									Date value2 = (Date)o2.getClass().getMethod(getter).invoke(o2);
									return value1.compareTo(value2);
								}
								else
								{
									String value1 = ""+o1.getClass().getMethod(getter).invoke(o1);
									String value2 = ""+o2.getClass().getMethod(getter).invoke(o2);
									return value1.compareTo(value2);
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
								return 0;
							}
						}
				
					});
			HtmlTableBuilder builder = new HtmlTableBuilder(classe, id, sumario, titulo, beanList);
			builder.setDeleteAction(deleteAction);
			builder.setDetailAction(detailAction);
			builder.setUpdateAction(updateAction);
			return builder.getTable();
		}
		else if(ordem == 1)
		{
			Collections.sort(beanList, new Comparator<Object>()
					{

						@Override
						public int compare(Object o1, Object o2) 
						{
							try
							{
								if(type.endsWith("int") || type.endsWith("Integer"))
								{
									int value1 = (int)o1.getClass().getMethod(getter).invoke(o1);
									int value2 = (int)o2.getClass().getMethod(getter).invoke(o2);
									if(value2 > value1)
										return 1;
									else if(value2 < value1)
										return -1;
									else
										return 0;
								}
								else if(type.endsWith("double") || type.endsWith("Double"))
								{
									double value1 = (double)o1.getClass().getMethod(getter).invoke(o1);
									double value2 = (double)o2.getClass().getMethod(getter).invoke(o2);
									if(value2 > value1)
										return 1;
									else if(value2 < value1)
										return -1;
									else
										return 0;
								}
								else if(type.endsWith("Calendar"))
								{
									Calendar value1 = (Calendar)o1.getClass().getMethod(getter).invoke(o1);
									Calendar value2 = (Calendar)o2.getClass().getMethod(getter).invoke(o2);
									return value2.compareTo(value1);
								}
								else if(type.endsWith("Date"))
								{
									Date value1 = (Date)o1.getClass().getMethod(getter).invoke(o1);
									Date value2 = (Date)o2.getClass().getMethod(getter).invoke(o2);
									return value2.compareTo(value1);
								}
								else
								{
									String value1 = ""+o1.getClass().getMethod(getter).invoke(o1);
									String value2 = ""+o2.getClass().getMethod(getter).invoke(o2);
									return value2.compareTo(value1);
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
								return 0;
							}
						}
				
					});
			HtmlTableBuilder builder = new HtmlTableBuilder(classe, id, sumario, titulo, beanList);
			builder.setDeleteAction(deleteAction);
			builder.setDetailAction(detailAction);
			builder.setUpdateAction(updateAction);
			return builder.getTable();
		}
		else
		{
			System.out.println("Não ordenou.");
			return this.getTable();
		}
	}
	
	/**
	 * Usado para poupar código em back-end para escrever a tabela na response quando feitas as requisições de actions via Ajax.
	 * 
	 * @param response = saida após requisição.
	 * @param table = a tabela deve ser pega do HtmlTableBuilder de forma externa devido poder haver o uso de getTableOrderBy
	 * 
	 */
	public void writeTable(HttpServletResponse response, String table)
	{
		String html = table;
		try 
		{
			PrintWriter page = response.getWriter();
			page.print(html);
			page.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
