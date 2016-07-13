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
	
	private final static Logger log = Logger.getLogger(SessionListener.class);
	private static int tUsuario = 0;
	
	
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	
         HttpSession session = se.getSession();
         if(session.getAttribute(Constantes.ATT_USUARIO) != null){
        	 //Usuario usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
        	 //log.info("El usuario logeado es:"+usuario.getUserName());
        	 tUsuario++;
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
		ServletContext servletContext = session.getServletContext();
		servletContext.setAttribute(Constantes.ATT_SESIONES, sesiones);
	}

	private void addUsuario(HttpSessionBindingEvent se) {
		
		List<Usuario> usuarios = null;
		Usuario usuario = null;
		HttpSession hSession = se.getSession();
		ServletContext sContext = hSession.getServletContext();
		
		//cogemos la lista del contexto
		usuarios = (List<Usuario>) sContext.getAttribute(Constantes.ATT_LIST_USUARIO);
		
		//si es la primera vez que se logea alguien
		if(usuarios == null){
			usuarios = new ArrayList<Usuario>();
		}
		usuario = (Usuario) hSession.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(usuario);
		
		log.info(usuario.getUserName());
		
		//guardamos la lista en el contexto
		sContext.setAttribute(Constantes.ATT_LIST_USUARIO, usuarios);
	}

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	//aqui no necesitamos un objeto de tipo sesion, porque obviamente si ya hemos borrado el atributo de usuario, no puedo acceder a la sesion
    	if(se.getName().equalsIgnoreCase(Constantes.ATT_USUARIO)){
    		Usuario usuario = (Usuario) se.getValue();
    		tUsuario--;
    		removeUsuario(se);
    	}
    }

	private void removeUsuario(HttpSessionBindingEvent se) {
		
		List<Usuario> usuarios = null;
		HttpSession hSession = se.getSession();
		ServletContext servletContext = hSession.getServletContext();
		usuarios = (List<Usuario>) servletContext.getAttribute(Constantes.ATT_LIST_USUARIO);
		Usuario usuario = (Usuario) se.getValue();
		if(removeList(usuarios, usuario)){
			log.info("usuario deslogeado");
		}
	}

	private boolean removeList(List<Usuario> usuarios, Usuario usuario) {
		
		boolean encontrado = false;
		int i = 0, len = usuarios.size();
		while(i < len && encontrado == false){
			if(usuarios.get(i).equals(usuario)){
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
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
