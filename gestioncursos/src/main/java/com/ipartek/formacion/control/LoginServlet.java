package com.ipartek.formacion.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	HttpSession session = null;
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;
	private final static Logger log = Logger.getLogger(LoginServlet.class);
	

    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =request.getCookies();
		if (cookies!= null){
			String nUsuario="",password="";
			for (Cookie cookie: cookies){
				if (cookie.getName().equals("usuario")){
					nUsuario = cookie.getValue();
				} else{
					if (cookie.getName().equals("password")){
						password = cookie.getValue();
					}
				}
			}
			if (!"".equals(nUsuario)&&!"".equals(password)){
				createSession(request);
				//TODO falta hacer la redireccion
				Usuario usuario = null;
				usuario = new Usuario();
				usuario.setPass(password);
				usuario.setUser(nUsuario);
				usuario.setNick("Stukov");
				usuario.setSessionId(session.getId());
				session.setAttribute(Constantes.ATT_USUARIO, usuario);
				rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
				rd.forward(request, response);
			}
			log.trace("cookies");
		}
		Usuario usuario = null;
		
		String user= request.getParameter(Constantes.PAR_USER);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
			

			if ("password".equals(pass) && "Josu@josu.es".equals(user) || "password".equals(pass) && "Hola@hola.es".equals(user) ){
				
				createSession(request);
				usuario = new Usuario();
				usuario.setPass(pass);
				usuario.setUser(user);
				usuario.setNick("Stukov");
				usuario.setSessionId(session.getId());
				session.setAttribute(Constantes.ATT_USUARIO, usuario);
				if (checkboxes!=null && checkboxes.length==1){
					Cookie cookieNombre = new Cookie("usuario", user);
					Cookie cookiePassword = new Cookie("password", pass);
					cookieNombre.setMaxAge(60*60*15);
					cookiePassword.setMaxAge(60*60*15);
					response.addCookie(cookieNombre);
					response.addCookie(cookiePassword);
				}
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
				rd.forward(request, response);

			} else {
				createSession(request);
				//rd  = request.getRequestDispatcher("index.jsp");
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contraseña incorrecta");
				mensaje.setType(Mensaje.MSG_TYPE_ERROR);
				//request.setAttribute(Constantes.ATT_MENSAJE,mensaje);
				session.setAttribute(Constantes.ATT_MENSAJE,mensaje);
				try {
					response.sendRedirect(Constantes.JSP_HOME);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}

			
			//try {
				//rd.forward(request, response);
			//} catch (ServletException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//} catch (IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			
		
	}
	private void createSession(HttpServletRequest request){
		session = request.getSession(true);
		/* 
		 * getSession(True) Si la sesion no existe la crea, si existe te la coge
		 * getSession(false) Te coge la session activa, no crea una nueva. Si no existe sigues sin session
		 */
		session.setMaxInactiveInterval(60*60*15);// en milisegundos, 15 minutos. Es un autologout
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
		
		
		
		
	}

}
