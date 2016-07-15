<%@page import="com.ipartek.formacion.services.I18n.I18n"%>
<%@page import="com.ipartek.formacion.services.Idioma"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.ipartek.formacion.pojo.Curso"%>
<%@page import="com.ipartek.formacion.control.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:set var="language" value="en_EN"/>
<c:set var="language" value="<%=I18n.getBrowserLocale(response.getLocale()) %>"/>
<c:set var="language" value="${usuario.idioma}"/>
<c:set var="localeCode" value="${response.locale}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.ipartek.formacion.services.I18n.i18nmesages"/>
    
<jsp:include page="includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>
 	<jsp:include page="includes/error.jsp"/>
 	<!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
    -->
    <main class="container-fluid">
	<div class="row">	 
	<section class="col-xs-12 col-md-7">
		<header> <h2><fmt:message key="index.bienvenido"/> ${usuario.nick}</h2></header>
		<p>
			Bienvenidos a la pagina de Gestion de Alumnos de <span>Ipartek</span>.

		</p>
		<p>Esta apliación ha sido en conjunto de....</p>
		
	</section>
<%
if (session!=null && session.getAttribute(Constantes.ATT_USUARIO)!=null){
%>
		<div name="logout" id="logout" class="col-xs-12 col-md-5">
        	<a href="<%=Constantes.SERVLET_LOGOUT %>" class="btn btn-lg btn-primary btn-block btn-logout">Logout</a>
        </div>
                
		<%
	}
%>

    <div class="container">
    
        <div class="card card-container col-xs-12 col-md-5">
        	  
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <jsp:include page="includes/mensajes.jsp"/> 
            <form action="<%=Constantes.SERVLET_LOGIN %>" method='post' class="form-signin" >
                <span id="reauth-email" ></span>
                <input name="<%=Constantes.PAR_USER %>" value="${usuario.user}" type="email" id="<%=Constantes.PAR_USER %>" placeholder="Email address" class="form-control" required autofocus>
                <input name="<%=Constantes.PAR_PASSWORD %>" type="password" id="<%=Constantes.PAR_PASSWORD %>" class="form-control" placeholder="Password" required>
                <c:set var="idiomas" value="<%=Idioma.values() %>" />
				<select name="<%=Constantes.PAR_IDIOMA%>" class="select" title="idiomas">
				    <c:forEach items="${idiomas}" var="idioma">
				        <option value="${idioma.locale}">${idioma.nombre}</option>
				    </c:forEach>
				</select>
                <div id="remember" class="checkbox">
                    <label>
                        <input name="<%=Constantes.PAR_REMEMBER %>" id="<%=Constantes.PAR_REMEMBER %>" type="checkbox" value="1"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" >Sign in</button>
            </form><!-- /form -->
            <a href="#" class="forgot-password">
                Forgot the password?
            </a>
        </div><!-- /card-container -->
    </div><!-- /container -->
 
</main>
<%@ include file="includes/footer.jsp" %><!-- Aqui no se compila previamente, no tiene codigo java -->