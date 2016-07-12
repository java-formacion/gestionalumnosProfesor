package com.ipartek.formacion.controller.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionActivationListener,
		HttpSessionBindingListener {

	private final static Logger log = Logger.getLogger("ACCESOS");
	private static int nUsuarios = 0;

	public static int getnUsuarios() {
		return nUsuarios;
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
	 */
	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (se.getValue() instanceof Usuario) {
			nUsuarios++;
			addUser(se);
		}
	}

	private void addUser(HttpSessionBindingEvent se) {
		Usuario user = (Usuario) se.getValue();
		Map<String, Usuario> users = null;
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		users = (Map<String, Usuario>) context
				.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
		if (users == null) {
			users = new HashMap<String, Usuario>();
		}
		users.put(user.getIdSession(), user);
		context.setAttribute(Constantes.ATT_LISTADO_USUARIOS, users);
		log.info(user.getAlias() + " se ha logeado");
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (se.getValue() instanceof Usuario) {
			removeUser(se);
			nUsuarios--;
		}
	}

	private void removeUser(HttpSessionBindingEvent se) {
		Usuario user = (Usuario) se.getValue();
		Map<String, Usuario> users = null;
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		users = (Map<String, Usuario>) context
				.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
		users.remove(user.getIdSession());
		context.setAttribute(Constantes.ATT_LISTADO_USUARIOS, users);
		log.info(user.getAlias() + " se ha desconectado");
		log.info("Usuarios conectados:" + users.size());
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
	 */
	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

}
