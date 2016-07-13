package com.ipartek.formacion.controller.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {
	private final static Logger log = Logger.getLogger("ACCESOS");
	private static int totalUsuarios = 0;

	public static int getTotalUsiarios(){
		return totalUsuarios;
	}
	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
	 */
	@Override
	public void sessionDidActivate(HttpSessionEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent se)  {
		HttpSession session = se.getSession();
		if(session.getAttribute(Constantes.ATT_USUARIO)!=null){
			//Usuario usuario = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);
			//log.info(usuario.getUserName());
			totalUsuarios++;
			addUsuario(se);
			addSession(se);
		}
	}

	private void addSession(HttpSessionBindingEvent se) {
		Map<String,HttpSession> sesiones = null;
		if( sesiones == null){
			sesiones = new HashMap<String, HttpSession>();
		}
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		sesiones.put(session.getId(), session);
		context.setAttribute(Constantes.ATT_LISTADO_SESIONES,sesiones);
	}
	private void addUsuario(HttpSessionBindingEvent se) {
		Usuario user = null;
		List<Usuario> usuarios = null;

		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();

		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);

		//si es la primera vez que se loguea alguien
		if(usuarios == null){
			usuarios = new ArrayList<Usuario>();
		}

		user = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(user);
		context.setAttribute(Constantes.ATT_LIST_USUARIOS, usuarios);
		log.info(user.getUserName());
	}
	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se)  {
		if(se.getName().equals(Constantes.ATT_USUARIO)){
			//Usuario user = (Usuario) se.getValue();
			totalUsuarios--;
			removeUsuario(se);
		}
	}

	private void removeUsuario(HttpSessionBindingEvent se) {
		List<Usuario> usuarios = null;
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);
		Usuario user = (Usuario) se.getValue();
		if(removeList(usuarios,user)){
			log.info("usuario deslogeado");
		}

	}

	private boolean removeList(List<Usuario> usuarios, Usuario user) {
		boolean encontrado = false;
		int i = 0, len = usuarios.size();
		while(i< len && encontrado==false){
			if(usuarios.get(i).equals(user)){
				encontrado = true;
				usuarios.remove(i);
			}
			i++;
		}

		return encontrado;
	}
	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
	 */
	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0)  {
		// TODO Auto-generated method stub
	}

}
