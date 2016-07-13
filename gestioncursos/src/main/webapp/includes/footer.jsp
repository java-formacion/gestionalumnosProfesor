<%@page import="com.ipartek.formacion.controller.listener.SessionListener"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer>
	<div class="row">
	
		<div class="col-xs-10">Página realizada por Ipartek Formación.</div>
		<div class="col-xs-2">
			<p>Usuarios Activos: <%=SessionListener.getnUsuarios() %></p>
		</div>
	</div>
</footer>
</body>
</html>