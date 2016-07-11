<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<%
	List<Alumno> alumnos = (List<Alumno>) request
			.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
%>
<main>
<div class="row">
	<div class="col-xs-6">

		<div class="row">
			<div class="col-xs-12">

				<%
					if (alumnos != null) {
						for (Alumno alumno : alumnos) {
				%>

				<form action='<%=Constantes.SERVLET_ALUMNOS%>' method='POST' name='' id=''>
					<a class="col-xs-7"
						href='<%=Constantes.SERVLET_ALUMNOS%>?<%=Constantes.PAR_CODIGO%>=<%=alumno.getCodigo()%>'><%=alumno.getApellidos()%>, <%=alumno.getNombre()%></a>
					<input type='hidden' id='<%=Constantes.PAR_CODIGO%>' name='<%=Constantes.PAR_CODIGO%>'
						value='<%=alumno.getCodigo()%>' /> <input type='hidden' id='<%=Constantes.PAR_OPERACION%>'
						name='<%=Constantes.PAR_OPERACION%>' value='<%=Constantes.OP_DELETE%>' />
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
						href="<%out.write(Constantes.SERVLET_ALUMNOS + "?" + Constantes.PAR_CODIGO
					+ "=" + Alumno.CODIGO_ALUMNO);%>">
						Añadir <span class="fa fa-plus"></span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
</main>
<%@include file="../includes/footer.jsp"%>