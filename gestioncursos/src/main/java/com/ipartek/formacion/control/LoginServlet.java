package com.ipartek.formacion.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
	private final static Logger Log = Logger.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuario = null;

		String user = request.getParameter(Constantes.PAR_USER);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String idioma = request.getParameter(Constantes.PAR_IDIOMA);

		if ("password".equals(pass) && "Josu@josu.es".equals(user)) {

			createSession(request);
			usuario = new Usuario();
			usuario.setPass(pass);
			usuario.setUser(user);
			usuario.setNick("Stukov");
			usuario.setSessionId(session.getId());
			usuario.setIdioma(idioma);
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getMessage());
			}
		} else {
			createSession(request);
			// rd = request.getRequestDispatcher("index.jsp");
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrecta");
			mensaje.setType(Mensaje.MSG_TYPE_ERROR);
			// request.setAttribute(Constantes.ATT_MENSAJE,mensaje);
			session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.error(e.getMessage());
			}
		}

	}

	private void createSession(HttpServletRequest request) {
		session = request.getSession(true);
		/*
		 * getSession(True) Si la sesion no existe la crea, si existe te la coge
		 * getSession(false) Te coge la session activa, no crea una nueva. Si no
		 * existe sigues sin session
		 */
		session.setMaxInactiveInterval(60 * 60 * 15);// en milisegundos, 15
														// minutos. Es un
														// autologout

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);

	}

}