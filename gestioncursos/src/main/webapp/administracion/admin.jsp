<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>
 	<jsp:include page="../includes/error.jsp"/>
 	<!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
    -->
    <main class="container-fluid">
	<div class="row">	 
	<section class="col-xs-12 col-md-7">
		<header> <h2>Administración</h2></header>

<body>
<h1>hola</h1>

<%@ include file="../includes/footer.jsp" %><!-- Aqui no se compila previamente, no tiene codigo java -->