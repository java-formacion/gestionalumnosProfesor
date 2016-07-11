<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.pojo.TipoCurso"%>
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<jsp:include page="../includes/header.jsp"/>
<main>
	<%
	Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
	int op = -1;
	if(curso!=null){
		op = Constantes.OP_UPDATE;
	%>	
	<title>Curso <% out.write(curso.getNombre()); %></title>
	<% 
	}else{
		%>
		<title>Curso - Curso nuevo</title>
		<%
		op = Constantes.OP_CREATE;
		curso = new Curso();
		}
		%>
</head>

	<div id="wrapper">
	<a class="btn btn-info" href="<%=Constantes.SERVLET_CURSOS %>"><span class="glyphicon glyphicon-arrow-left">Atrás</span></a>
	<br><br>
	<%
	
	if(curso!=null){
	%>
		<form class="form-horizontal" name="" method="post" action="<%=Constantes.SERVLET_CURSOS%>">
			<input type="hidden" 
				id="<%=Constantes.PAR_OPERACION%>" 
				name="<%=Constantes.PAR_OPERACION%>" 
				value="<%=op%>"/>
			<input type="hidden" 
				id="<%=Constantes.PAR_CODIGO%>" 
				name="<%=Constantes.PAR_CODIGO%>"
				value="<%=curso.getCodigo()%>"/>
			<div class="form-group">		
			<div  class= col-xs-1>	
				<label for="<%=Constantes.PAR_NOMBRE%>">NOMBRE:</label>
			</div>
			
			<div  class= col-xs-2>	
				<input type="text" class="form-control" 
					id="<%=Constantes.PAR_NOMBRE%>" 
					name="<%=Constantes.PAR_NOMBRE%>" 
					value="<%=curso.getNombre()%>"/>
		     </div>
		    </div>
		     
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Tipo curso:</label>
			    </div>
			   <div class= col-xs-2>
			    <select class="form-control" name="<%=Constantes.PAR_TIPOCURSO %>">
			     		<%
			     		for(TipoCurso tipo: TipoCurso.values()){
			     		%>
			     		<option <%= curso.getTipo() == tipo ? "selected" : "" %>
			     		value="<%=tipo.getCodigo() %>"> <%=tipo.getValor()  %></option>
			     		<% 	
			     		}
	
			     	%>
			    </select>	
			    </div>
			</div> 
			
			<div class="form-group">
				<div class= col-xs-1>
			    <label>Modulos:</label>
			    </div>
			    <div class= col-xs-2>
			     
			     	<%
			     	List<Modulo> modulos = (List<Modulo>)request.getAttribute(Constantes.ATT_LISTADO_MODULOS);
			     	int nHoras =0;
			     	if(modulos!=null){
			     		for(Modulo modulo: modulos){
			     			Map<Integer,Modulo> mods = curso.getModulos(); //curso.getModulos() devuelve un mapa
			     			boolean existe =false;
			     			if(mods.containsKey(modulo.getCodigo())){
			     				existe = true;
			     				nHoras+=modulo.getHoras().getDuracion();
			     			}
			     		%>
			     		<input type="checkbox" name="<%=Constantes.PAR_MODULOS %>" 
			     		<%= existe ? "checked" : "" %>
			     		value="<%=modulo.getCodigo() %>"> <%=modulo.getNombre() %></>
			     		<% 	
			     		}
			     	}
			     	%>
				</div> 
		    </div> 
		    
		    
		 <div class="form-group">
				<div class= col-xs-1>
			    <label>Alumnos:</label>
			    </div>
			    <div class= col-xs-2>
			     	<%
			     	List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
			     	if(alumnos!=null){
			     		for(Alumno alum: alumnos){
			     			boolean existe =false;
			     			if(curso.getAlumnos().containsKey(alum.getDni())){ //Dni es la clave en el mapa de alumnos
			     				existe = true;
			     			}
			     		%>
			     		<input type="checkbox" name="<%=Constantes.PAR_ALUMNOS %>" 
			     		<%= existe ? "checked" : "" %>
			     		value="<%=alum.getCodigo() %>"> <%=alum.getNombre() %></>
			     		<% 	
			     		}
			     	}
			     	%>
				</div> 
		    </div> 
		    
		    
		    <div class="form-group">
		    <div  class= col-xs-1>	
				<label>TOTAL MODULOS:<%=curso.getModulos().size()%></label>
		    </div>	
		    </div>
		    
		     <div class="form-group">
			<div  class= col-xs-1>	
				<label>TOTAL ALUMNOS:<%=curso.getAlumnos().size()%></label>
			</div>
		    </div>
		    
		    <div class="form-group">
		    <div  class= col-xs-1>	
				<label>TOTAL HORAS:<%=nHoras%></label>
		    </div>	
		    </div>
		    
		    <button type="submit" class="btn btn-success">Guardar</button>
		</form>
	<% }
		%>
	</div>
</main>
<%@ include file="../includes/footer.jsp" %>