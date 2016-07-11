<%@page import="com.ipartek.formacion.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.pojo.Modulo"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Gestion de Cursos</title>
<!--[if lt IE 9]>
	   <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	 <![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<!-- BOOTSTRAP BASE STYLES -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- FONT AWESOME -->
<link rel="stylesheet" href="css/font-awesome.min.css" />
<!-- MY THEME STYLES -->
<link rel="stylesheet" href="css/style.css" />
<!-- JQUERY -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>

</head>
<body class="container-fluid">
	<header class="row">
		<h1 class="col-xs-6">Gestion de Cursos Ipartek</h1>
	</header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Brand</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button">MODULOS <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=Constantes.SERVLET_MODULOS%>">Ver Modulos </a></li>
							<li><a
								href="<%=Constantes.SERVLET_MODULOS + "?" + Constantes.PAR_CODIGO
					+ "=" + Modulo.CODIGO_MODULO%>">Crear
									Modulo </a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">ALUMNOS <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=Constantes.SERVLET_ALUMNOS%>">Ver Alumnos </a></li>
							<li><a
								href="<%=Constantes.SERVLET_ALUMNOS + "?" + Constantes.PAR_CODIGO
					+ "=" + Modulo.CODIGO_MODULO%>">Crear
									Alumno </a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">CURSOS <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=Constantes.SERVLET_CURSOS%>">Ver Cursos </a></li>
							<li><a
								href="<%=Constantes.SERVLET_CURSOS + "?" + Constantes.PAR_CODIGO
					+ "=" + Modulo.CODIGO_MODULO%>">Crear
									Curso </a></li>
						</ul></li>

				</ul>
				<%
					Usuario user = (Usuario) session
							.getAttribute(Constantes.ATT_USUARIO);
					if (user != null) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<p class="navbar-text">
							Signed in as <strong><%=user.getAlias()%></strong>
						</p>
					<a href="<%=Constantes.SERVLET_LOGOUT%>" class="btn btn-danger navbar-btn">Log
							Out</a>
				</ul>
				<%
					}
				%>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>