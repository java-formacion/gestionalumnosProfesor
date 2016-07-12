package com.ipartek.formacion.control.listener;

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

import com.ipartek.formacion.control.Constantes;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {

	private final static Logger log = Logger.getLogger("ACCESOS");
	private static int totalUsuario = 0;
	
	
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
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         HttpSession session = arg0.getSession();
         if (session.getAttribute(Constantes.ATT_USUARIO)!=null){
        	 //Usuario usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
        	 //log.info("Usuario logueado: "+usuario.getUser()+" "+usuario.getPass()+" " +usuario.getNick());
        	 totalUsuario ++;
        	 addUsuario(arg0);
         }
    }

	private void addUsuario(HttpSessionBindingEvent arg0) {
		Usuario usuario = null;
		List <Usuario> usuarios = null;
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LISTA_USUARIOS);
		
		//El array puede estar vacio y ser la primera persona que se loguea.
		
		if (usuarios == null){
			usuarios = new ArrayList<Usuario>();
		}
		usuario = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(usuario);
		context.setAttribute(Constantes.ATT_LISTA_USUARIOS, usuarios);
		log.info("Usuario logueado: "+usuario.getUser()+" "+usuario.getPass()+" " +usuario.getNick());
	}

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
        if (arg0.getName().equalsIgnoreCase(Constantes.ATT_USUARIO)){
       	 //Usuario usuario = (Usuario) arg0.getValue();
       	 //log.info("Usuario deslogueado: "+usuario.getUser()+" "+usuario.getPass()+" "+usuario.getNick());
       	 totalUsuario --;
       	 removeUsuario(arg0);
        }
    }

	private void removeUsuario(HttpSessionBindingEvent arg0) {
		Usuario usuario = null;
		List <Usuario> usuarios = null;
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LISTA_USUARIOS);
		usuario = (Usuario) arg0.getValue();
		if (removeList(usuarios,usuario)){
			log.info("Usuario deslogueado: "+usuario.getUser()+" "+usuario.getPass()+" "+usuario.getNick());
		}
		
	}
	
	private boolean removeList(List<Usuario> usuarios, Usuario usuario) {
		boolean bool= false;
		int i = 0, len=usuarios.size();
		while (i<len && bool==false){
			if (usuarios.get(i).equals(usuario)){
				bool=true;
				usuarios.remove(i);
			}
			i++;
		}
		
		return bool;
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
