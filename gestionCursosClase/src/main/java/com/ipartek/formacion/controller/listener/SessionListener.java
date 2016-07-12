package com.ipartek.formacion.controller.listener;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

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
	private static int totalUsuarios = 0;
	
	
    /**
     * Default constructor. 
     *
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }
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
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session = arg0.getSession();
    	if(session.getAttribute(Constantes.ATT_USUARIO)!=null)
    	{
    		/*Usuario usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
    		log.info(usuario.getUserName());
    		totalUsuarios++;*/
    		addUsuario(arg0);
    	}
    }

	private void addUsuario(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		Usuario user = null;
		List<Usuario> usuarios = null;
		
		HttpSession session = arg0.getSession();
		ServletContext contexto = session.getServletContext();
		
		usuarios = (List<Usuario>) contexto.getAttribute(Constantes.ATT_LIST_USUARIO);
		
		//Si es la primera vez que se loguea alguien el array esta vacio
		if(usuarios==null)
		{
			usuarios = new ArrayList<Usuario>();
		}
		user = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(user);
		
		contexto.setAttribute(Constantes.ATT_LIST_USUARIO, usuarios);
		log.info(user.getUserName());
		
		
	}

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	if(arg0.getName().equalsIgnoreCase(Constantes.ATT_USUARIO))
    	{
    		/*Usuario user = (Usuario) arg0.getValue();
    		totalUsuarios--;*/
    		removeUsuario(arg0);
    	}
    }

	private void removeUsuario(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = null;
		ServletContext contexto = arg0.getSession().getServletContext();
		usuarios = (List<Usuario>) contexto.getAttribute(Constantes.ATT_LIST_USUARIO);
		
		Usuario user = (Usuario) arg0.getValue();
		if(removeList(usuarios, user))
		{
			log.info("Usuario deslogueado");
		}
	}

	
	private boolean removeList(List<Usuario> usuarios, Usuario user) {
		// TODO Auto-generated method stub
		boolean encontrado = false;
		
		int i = 0, len= usuarios.size();
		
		while(i<len && encontrado==false)
		{
			if(usuarios.get(i).equals(user))
			{
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
