<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<%
		Modulo modulo = (Modulo) request.getAttribute(Constantes.ATT_MODULO);
		int op = -1;
		if(modulo!=null)
		{
			op = Constantes.OP_UPDATE;		
 			 out.write(modulo.getNombre()); 
		}
		else
		{
			%>
				<title>Modulo nuevo</title>
			<%
				op = Constantes.OP_CREATE;
				modulo = new Modulo();	
		}
		%>
	
<body>

	<div id="wrapper">
		<a href="<%=Constantes.SERVLET_MODULOS %>">Atras</a>	
		<%
		if(modulo!=null){
		%>
		<%=Constantes.SERVLET_CURSOS%>
			<form name="" id="" method='post' 
				action="<%=Constantes.SERVLET_MODULOS%>">
				<input type="hidden" 
					id="<%=Constantes.PAR_OPERACION %>"
					name="<%=Constantes.PAR_OPERACION %>"  
					value="<%=op %>"/>
				<input type="hidden" 
					id="<%=Constantes.PAR_CODIGO %>" 
					name="<%=Constantes.PAR_CODIGO %>" 
					value="<%=modulo.getCodigo()%>"/>
				<label for="<%=Constantes.PAR_NOMBRE%>"></label>
				<input type="text" 
					name="<%=Constantes.PAR_NOMBRE%>" 
					id="<%=Constantes.PAR_NOMBRE%>" 
					value="<%=modulo.getNombre() %>"
					/>
			
				<input type="submit" />
			</form>			
	<%	}
		%>
		</div>

<%@ include file="../includes/footer.jsp"%>