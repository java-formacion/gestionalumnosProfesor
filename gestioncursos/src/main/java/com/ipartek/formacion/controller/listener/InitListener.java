package com.ipartek.formacion.controller.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 * @author Curso
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {
  public final static String PROPS_NAME = "properties";
  private final static Logger LOG = Logger.getLogger(InitListener.class);
  private final static String PATH_LOG4J = "WEB-INF/conf/log4j.properties";

  /**
   * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
   * @param scab
   *          ServletContextAttributeEvent
   * 
   */
  public void attributeAdded(ServletContextAttributeEvent scab) {

  }

  /**
   * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
   * @param scab
   *          ServletContextAttributeEvent
   */
  public void attributeRemoved(ServletContextAttributeEvent scab) {

  }

  /**
   * @see ServletContextListener#contextDestroyed(ServletContextEvent)
   * @param sce
   *          ServletContextEvent
   */
  public void contextDestroyed(ServletContextEvent sce) {
    // TODO Auto-generated method stub
  }

  /**
   * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
   * @param scab
   *          ServletContextAttributeEvent
   */
  public void attributeReplaced(ServletContextAttributeEvent scab) {
    // TODO Auto-generated method stub
  }

  /**
   * @see ServletContextListener#contextInitialized(ServletContextEvent)
   * @param sce
   *          ServletContextEvent
   */
  public void contextInitialized(ServletContextEvent sce) {
    loadLog4j(sce);
    loadProperties(sce);
  }

  /**
   * 
   * @param sce
   *          ServletContextEvent
   */
  private void loadProperties(ServletContextEvent sce) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream input = classLoader.getResourceAsStream("constantes.properties");
    Properties props = new Properties();
    try {
      props.load(input);
    } catch (Exception e) {
      LOG.error("No se ha cargado el archivo constantes");
    }
    sce.getServletContext().setAttribute(PROPS_NAME, props);
  }

  /**
   * 
   * @param sce
   * @param sce
   *          ServletContextEvent
   */
  private void loadLog4j(ServletContextEvent sce) {
    String ruta = sce.getServletContext().getRealPath("/");
    try {
      PropertyConfigurator.configure(ruta + PATH_LOG4J);
      LOG.info("LOG CARGADO");

    } catch (Exception e) {
    }
  }

}
