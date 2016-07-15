package com.ipartek.formacion.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {

	private final static Logger log = Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J ="WEB-INF/conf/log4j.properties"; //ruta relativa al contexto
    /**
     * Default constructor. 
     *
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0){ 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
         // TODO Auto-generated method stub
    	loadLog4j(arg0);
    	loadConstantes(arg0);
    }

	private void loadConstantes(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classloader.getResourceAsStream("constantes.properties");
		
		Properties proper = new Properties();
		try {
			proper.load(inputStream);
			log.info("se han cargado las properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("no se han cargado las constantes properties");
		}
		
	}

	private void loadLog4j(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String ruta = arg0.getServletContext().getRealPath("/");
		PropertyConfigurator.configure(ruta + PATH_LOG4J);
		try {
			PropertyConfigurator.configure(ruta + PATH_LOG4J);
			//if(LogManager.exists("PANTALLA")!=null)
			//{
				log.info("Log Cargado");
			//}
		} catch (Exception e) {
			// TODO: handle exception
				log.error("Error al cargar el log de log4j");
		}
	}
	
}
