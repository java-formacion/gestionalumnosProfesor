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
 * @author Curso
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener,
    HttpSessionActivationListener, HttpSessionBindingListener {

  private final static Logger log = Logger.getLogger("ACCESOS");
  private static int nUsuarios = 0;

  /**
   * 
   * @return nUsuarios
   * 
   */
  public static int getnUsuarios() {
    return nUsuarios;
  }

  /**
   * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
   * @param se
   *          HttpSessionEvent
   */
  public void sessionCreated(HttpSessionEvent se) {

  }

  /**
   * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
   * @param event
   *          HttpSessionBindingEvent
   */
  public void valueBound(HttpSessionBindingEvent event) {
    // TODO Auto-generated method stub
  }

  /**
   * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
   * @param se
   *          HttpSessionEvent
   */
  public void sessionDestroyed(HttpSessionEvent se) {
    // TODO Auto-generated method stub
  }

  /**
   * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent
   * @param se
   *          HttpSessionEvent)
   */
  public void sessionDidActivate(HttpSessionEvent se) {
    // TODO Auto-generated method stub
  }

  /**
   * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
   * @param se
   *          HttpSessionEvent
   */
  public void attributeAdded(HttpSessionBindingEvent se) {
    if (se.getValue() instanceof Usuario) {
      nUsuarios++;
      addUser(se);
      addSession(se);
    }
  }

  /**
   * 
   * @param se
   *          HttpSessionBindingEvent
   */
  @SuppressWarnings("unchecked")
  private void addSession(HttpSessionBindingEvent se) {
    Map<String, HttpSession> sesiones = null;
    HttpSession session = se.getSession();
    ServletContext context = session.getServletContext();
    if (context.getAttribute(Constantes.ATT_LISTADO_SESIONES) instanceof Map<?, ?>) {
      /*
       * TODO Fix cast warning addSession *
       */
      sesiones = (Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LISTADO_SESIONES);
    }

    if (sesiones == null) {
      sesiones = new HashMap<String, HttpSession>();
    }
    sesiones.put(session.getId(), session);
    context.setAttribute(Constantes.ATT_LISTADO_SESIONES, sesiones);
  }

  /**
   * 
   * @param se
   *          HttpSessionBindingEvent
   */
  @SuppressWarnings("unchecked")
  private void addUser(HttpSessionBindingEvent se) {
    Usuario user = (Usuario) se.getValue();
    Map<String, Usuario> users = null;
    HttpSession session = se.getSession();
    ServletContext context = session.getServletContext();
    /*
     * TODO Fix cast warning addUser
     */
    users = (Map<String, Usuario>) context.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
    if (users == null) {
      users = new HashMap<String, Usuario>();
    }
    users.put(user.getIdSession(), user);
    context.setAttribute(Constantes.ATT_LISTADO_USUARIOS, users);
    log.info(user.getAlias() + " se ha conectado");
  }

  /**
   * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
   * @param se
   *          HttpSessionBindingEvent
   * 
   */
  public void attributeRemoved(HttpSessionBindingEvent se) {
    if (se.getValue() instanceof Usuario) {
      removeUser(se);
      nUsuarios--;
    }
  }

  /**
   * 
   * @param se
   *          HttpSessionBindingEvent
   */
  @SuppressWarnings("unchecked")
  private void removeUser(HttpSessionBindingEvent se) {
    Usuario user = (Usuario) se.getValue();
    Map<String, Usuario> users = null;
    HttpSession session = se.getSession();
    ServletContext context = session.getServletContext();
    /*
     * TODO Fix cast warning removeUser
     */
    users = (Map<String, Usuario>) context.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
    users.remove(user.getIdSession());
    context.setAttribute(Constantes.ATT_LISTADO_USUARIOS, users);
    log.info(user.getAlias() + " se ha desconectado");
  }

  /**
   * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
   * @param se
   *          HttpSessionBindingEvent
   */
  public void attributeReplaced(HttpSessionBindingEvent se) {
  }

  /**
   * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
   * @param se
   *          HttpSessionEvent
   */
  public void sessionWillPassivate(HttpSessionEvent se) {
  }

  /**
   * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
   * @param event
   *          HttpSessionBindingEvent
   */
  public void valueUnbound(HttpSessionBindingEvent event) {
  }

}
