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
public class SessionListener implements HttpSessionBindingListener, HttpSessionActivationListener, HttpSessionAttributeListener, HttpSessionListener {

	private final static Logger log = Logger.getLogger("ACCESOS"); 
    private static int  totalUsuarios = 0; //esta variable java podría destruirla si en un periodo de tiempo no inicia sesión nadie y se perdería. Hay que guardarlo en el contexto de la aplicación.
    
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
         
    }

    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
         HttpSession session = se.getSession();
         
         if(session.getAttribute(Constantes.ATT_USUARIO)!=null){
        	// Usuario usuario = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);
        	//log.info(usuario.getAlias() + " ha iniciado sesion");
        	 totalUsuarios++;
        	 addUsuario(se);
        	 addSession(se);
         }
         
    }

    //método que añade sesiones al contexto
    private void addSession(HttpSessionBindingEvent se) {
    	Map<String, HttpSession> sesiones = null;
    	
    	
    	if(sesiones == null){
			sesiones = new HashMap<String, HttpSession>();
		}
    	
    	HttpSession session = se.getSession();
    	ServletContext context = session.getServletContext();
    	sesiones.put(session.getId(), session);
    	
    	context.setAttribute(Constantes.ATT_LIST_SESIONES, sesiones); //guardamos en el contexto
		
	}

	private void addUsuario(HttpSessionBindingEvent se) {
    	Usuario usuario = null;
		List<Usuario> usuarios = null;
		
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS); //el contexto se utiliza como la session, pero a nivel global de toda la aplicación(la sessión es a nivel de usuario)
		
		//si es la primera vez que se loguea
		if(usuarios == null){
			usuarios = new ArrayList<Usuario>();
		}
		
		
		usuario = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(usuario);
		context.setAttribute(Constantes.ATT_LIST_USUARIOS, usuarios);
		log.info(usuario.getAlias() + " ha iniciado sesion");
	}

	public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	//con se.getName() se coge el nombre del atributo que se ha quitado
    	 if(se.getName().equalsIgnoreCase(Constantes.ATT_USUARIO)){
    		 //Usuario usuario = (Usuario)se.getValue();
    		 removeUsuario(se);
    		 totalUsuarios--;
    	 }
    }

	
    private void removeUsuario(HttpSessionBindingEvent se) {
    	List<Usuario> usuarios = null;
    	HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS); //recojo la lista de usuarios
		Usuario usuario = (Usuario) se.getValue(); //recojo el objeto que quiero borrar
		if(removeList(usuarios,usuario)){
			log.info("usuario deslogueado");
		}
		
	}
    

	private boolean removeList(List<Usuario> usuarios, Usuario usuario) {
		
		boolean encontrado = false;
		int i = 0;
		
		while(i<usuarios.size()){
			if(usuarios.get(i).equals(usuario)){
				encontrado = true;
				usuarios.remove(i);
			}
			i++;
		}
		
		return encontrado;
	}

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
