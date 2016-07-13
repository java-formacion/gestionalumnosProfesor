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

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         HttpSession session = se.getSession();
         if(session.getAttribute(Constantes.ATT_USUARIO)!=null){
        	 //Usuario usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
        	 //log.info("Username: " + usuario.getUserName());
        	 totalUsuarios++;
        	 
        	 addUsuario(se);
        	 addSession(se);
         }
    }

	private void addSession(HttpSessionBindingEvent se) {
		Map<String, HttpSession> sesiones = null;
		
		if(sesiones == null){
			sesiones = new HashMap<String, HttpSession>();
		}
		
		HttpSession session = se.getSession();
		sesiones.put(session.getId(), session);
		ServletContext context = session.getServletContext();
		context.setAttribute(Constantes.ATT_LISTADO_SESIONES, sesiones);
	}

	private void addUsuario(HttpSessionBindingEvent se) {
		Usuario user = null;
		List<Usuario> usuarios = null;
		
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);
		
		// Si es la primera vez que se logea alguien
		if(usuarios==null){
			usuarios = new ArrayList<Usuario>();
		}
		
		user = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(user);
		context.setAttribute(Constantes.ATT_LIST_USUARIOS, usuarios);
		
		log.info("Username: " + user.getUserName());
	}

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  {
         if(se.getName().equalsIgnoreCase(Constantes.ATT_USUARIO)){
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
		
		if(removeList(usuarios, user)){
			log.info("Usuario deslogeado.");
		}
	}


	private boolean removeList(List<Usuario> usuarios, Usuario user) {
		boolean encontrado = false;
		int i=0, len = usuarios.size();
		
		while(i<len && encontrado==false){
			if(usuarios.get(i).equals(user)){
				encontrado=true;
				usuarios.remove(i);
			}
			i++;
		}
		
		return encontrado;
	}

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
