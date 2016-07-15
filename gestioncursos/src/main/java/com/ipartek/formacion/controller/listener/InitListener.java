package com.ipartek.formacion.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {

	private final static Logger log = Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J = "WEB-INF/conf/log4j.properties";
	private final static String PROPS_NAME = "properties";
	
	
	/**
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
    public void contextDestroyed(ServletContextEvent arg0)  { 
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
    public void contextInitialized(ServletContextEvent sce)  { 
    	loadLog4j(sce);
    	loadProperties(sce);
    }

	private void loadProperties(ServletContextEvent sce) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("constantes.properties");
		Properties props = new Properties();
		
		try{
			props.load(input);
		} catch(IOException e){
			log.error("No se han cargado las properties");
		}
		
		sce.getServletContext().setAttribute(PROPS_NAME, props);
	}

	private void loadLog4j(ServletContextEvent sce) {
		String ruta = sce.getServletContext().getRealPath("/");
		
		try{
			PropertyConfigurator.configure(ruta+PATH_LOG4J);
			log.info("LOG CARGADO");
			
			/*
			if(LogManager.exists("PANTALLA")!=null){
				log.info("LOG CARGADO");
			}
			*/
		} catch(Exception e){
			
		}
	}
	
}
