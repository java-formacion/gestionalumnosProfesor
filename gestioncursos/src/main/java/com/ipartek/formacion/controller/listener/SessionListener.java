package com.ipartek.formacion.controller.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
	private final static Logger log=Logger.getLogger("ACCESOS");
	private static int totalUsuarios=0;
	
	public static int getTotalUsuarios(){
		return totalUsuarios;
	}
	
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent sbe)  { 
        if (sbe.getName().equalsIgnoreCase(Constantes.ATT_USUARIO)) {
			Usuario user=(Usuario)sbe.getValue();
			
			removeUsuario(sbe);
			totalUsuarios--;
		}
    	
    	
    	
    }

	private void removeUsuario(HttpSessionBindingEvent sbe) {
		List<Usuario>usrList=null;
		HttpSession session=sbe.getSession();
		ServletContext sC=session.getServletContext();
		usrList=(List<Usuario>)sC.getAttribute(Constantes.ATT_LIST_USUARIO);
		Usuario usr=(Usuario)sbe.getValue();
		if (removeList(usrList,usr)) {
			log.info("usuario deslogueado");
		}
		session.getId();
	}

	private boolean removeList(List<Usuario> usrList, Usuario usr) {
		boolean found=false;
		int i=0, len=usrList.size();
		while (i<len && found==false) {
			if (usrList.get(i).equals(usr)) {
				found=true;
				usrList.remove(i);
			}
			i++;
		}
		return found;
	}


	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent sbe)  { 
         HttpSession session=sbe.getSession();
         if (session.getAttribute(Constantes.ATT_USUARIO)!=null) {
        	 Usuario usr=(Usuario)session.getAttribute(Constantes.ATT_USUARIO);
        	 InetAddress addr;
        	 String ip="";
			try {
				addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	totalUsuarios++;
        	addUsuario(sbe);
        	addSession(sbe);
        	 
			log.info("Se ha logueado "+usr.getUser()+"desde la IP: "+ip);
		}
    	
    }

	private void addSession(HttpSessionBindingEvent sbe) {
		Map<String,HttpSession>sesiones=null;
		if (sesiones==null) {
			sesiones=new HashMap<String,HttpSession>();
		}
		HttpSession session=sbe.getSession();
		ServletContext context=session.getServletContext();
		sesiones.put(session.getId(), session);
		context.getAttribute(Constantes.ATT_LISTADO_SESIONES);
	}


	private void addUsuario(HttpSessionBindingEvent sbe) {
		Usuario usr=null;
		List<Usuario> usrList=null;
		HttpSession session=sbe.getSession();
		ServletContext sC=session.getServletContext();
		
		usrList=(List<Usuario>)sC.getAttribute(Constantes.ATT_LIST_USUARIO);
		//primer login
		if (usrList==null) {
			usrList=new ArrayList<Usuario>();
		}
		
		usr=(Usuario)session.getAttribute(Constantes.ATT_USUARIO);
		usrList.add(usr);
		log.info(usr.getUser());
		sC.setAttribute(Constantes.ATT_LIST_USUARIO, usrList);
		
	}


	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
