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
 *Listener para ver usuarios conectados
 */
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener, HttpSessionAttributeListener, HttpSessionBindingListener {

	private final static Logger log=Logger.getLogger("ACCESOS");
	private final static String PATH_LOG4J="WEB-INF/conf/log4j.properties";
	private static  int totalUsuarios=0;//cont usuarios ,cuando la sesión 
    /**
     * Default constructor. 
     */
	public static int getTotalUsuarios(){
		return totalUsuarios;
	}
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         
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
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         //cargar lista usuarios, cuando alguien se ha logeado (LoginServlet)
    	//coger atributo session,para saber si existe
    	HttpSession session=se.getSession();
    	//saber si el usuario es nulo o no
    	if(session.getAttribute(Constantes.ATT_USUARIO)!=null){
    		/*Usuario usuario=(Usuario)session.getAttribute(Constantes.ATT_USUARIO);
    		log.info(usuario.getUserName());*/
    		totalUsuarios++;//incrementar el cont de usuarios 
			//metodo addUsuario y le paso el session
			addUsuario(se);
			addSession(se);
    	}
    }
	private void addSession(HttpSessionBindingEvent se) {
		Map<String,HttpSession> sesiones = null;//nulo porq cuando inicio la app no hay ninguna sesión
		if( sesiones == null){
			sesiones = new HashMap<String, HttpSession>();
		}
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		sesiones.put(session.getId(), session);
		context.setAttribute(Constantes.ATT_LISTADO_SESIONES,sesiones);
		
	}
	private void addUsuario(HttpSessionBindingEvent se){
		Usuario user=null;
		List<Usuario> usuarios=null;
		//coger los usuarios
		HttpSession session=se.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);
		//si somos los primeros en logearnos el array estará vacío
		if(usuarios==null){//si está vacío creamos uno
			usuarios=new ArrayList<Usuario>();
		}
		//agregar el usuario a la lista
		user = (Usuario)session.getAttribute(Constantes.ATT_USUARIO);
		usuarios.add(user);
		//guardar lista dentro del contexto
		context.setAttribute(Constantes.ATT_LIST_USUARIOS,usuarios);
		log.info(user.getUserName());
					
	}

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	//no se puede acceder a la sesión porque es REMOVED, se accede al contexto, la sesión no está daría falso
         if(se.getName().equals(Constantes.ATT_USUARIO)){//si el nombre del atributo q has quitado, es el atributo de usuario dame el valor
     		Usuario usuario=(Usuario)se.getValue();
     		totalUsuarios--;
     		//cuando alguien se desloguea quitar usuario de la lista
   		    removeUsuario(se);
         }
		 
    }
	private void removeUsuario(HttpSessionBindingEvent se){
		List<Usuario> usuarios = null;
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		usuarios = (List<Usuario>) context.getAttribute(Constantes.ATT_LIST_USUARIOS);
		Usuario user = (Usuario) se.getValue();
		if(removeList(usuarios,user)){
			log.info("usuario deslogeado");
		}

		
	}
	private boolean removeList(List<Usuario> usuarios, Usuario user){//tb se puede hacer con un mapa y la id de la session
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
		// carpeta administración->ver usuarios logueados
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