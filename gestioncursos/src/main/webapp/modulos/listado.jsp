<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<%
	List<Modulo> modulos = (List<Modulo>) request
			.getAttribute(Constantes.ATT_LISTADO_MODULOS);
%>
<main>
<div class="row">
	<div class="col-xs-6">
		<div class="row">
			<div class="col-xs-12">
				<%
					if (modulos != null) {
						String formulario = "";
						for (Modulo modulo : modulos) {
				%>
				<form action='<%=Constantes.SERVLET_MODULOS%>' method='POST' name='' id=''>
					<a class="col-xs-7"
						href='<%=Constantes.SERVLET_MODULOS%>?<%=Constantes.PAR_CODIGO%>=<%=modulo.getCodigo()%>'><%=modulo.getReferencia()%>
						- <%=modulo.getNombre()%></a> <input type='hidden' id='<%=Constantes.PAR_CODIGO%>'
						name='<%=Constantes.PAR_CODIGO%>' value='<%=modulo.getCodigo()%>' /> <input type='hidden'
						id='<%=Constantes.PAR_OPERACION%>' name='<%=Constantes.PAR_OPERACION%>'
						value='<%=Constantes.OP_DELETE%>' />
					<div class='col-xs-5'>
						<button type='submit' class='btn btn-danger'>
							<span class='fa fa-times'></span>
						</button>
					</div>
				</form>
				<%
					}
					}
				%>
				<div class="col-xs-12">
					<a class="btn btn-success"
						href="<%out.write(Constantes.SERVLET_MODULOS + "?" + Constantes.PAR_CODIGO
					+ "=" + Modulo.CODIGO_MODULO);%>">
						Añadir <span class="fa fa-plus"></span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
</main>
<%@include file="../includes/footer.jsp"%>