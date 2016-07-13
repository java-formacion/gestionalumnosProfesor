<%@page import="com.ipartek.formacion.controller.Constantes"%>
	
<jsp:include page="/includes/header.jsp" />

<!--  BOOTSTRAP JS LIBS -->
<script src="js/bootstrap.min.js"></script>
		
<!-- JQUERY LIBRARY 1.11.3  -->
<script src="js/jquery.min.js"></script>

<main>
		<!--  
			<p>
				<jsp:include page="/includes/error.jsp"/>
			</p>
		-->
		
		<div class="container">
		    <div class="row">
		        <div class="col-md-8">
		        	<h2>Bienvenido</h2>
		           	Bienvenidos a la pagina de Gestion de Alumnos de Ipartek
		        </div>
		       	
		       	
		       	<%
		       		
		       	
					session = request.getSession();
					if(session == null || session.getAttribute(Constantes.ATT_USUARIO)==null){
					%>
						<div class="col-md-4">
				        	<jsp:include page="includes/mensaje.jsp" />
				        
				            <div class="sidebar-nav-fixed pull-right affix">
				                <div class="well">
									<div class="wrapper">
										<form class="form-signin" method="post" action="<%= Constantes.SERVLET_LOGIN %>">  
											<h2 class="form-signin-heading">Login</h2>
											
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
												<input type="text" class="form-control" name="<%= Constantes.PAR_USERNAME %>" placeholder="Email" required="" autofocus="" />
											</div>
											
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
												<input type="password" class="form-control" name="<%= Constantes.PAR_PASSWORD %>" placeholder="Contrase&ntilde;a" required=""/>  
											</div>
											
											<label class="checkbox">
												<input type="checkbox" value="1" id="<%=Constantes.PAR_REMEMBER %>" name="<%=Constantes.PAR_REMEMBER %>"> Recordarme
											</label>
											
											<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
										</form>
									</div>
				                </div>
				            </div>
				        </div>
					<%
					} 
				%>

		    </div>
		</div>
		
</main>

<%@ include file="/includes/footer.jsp" %>