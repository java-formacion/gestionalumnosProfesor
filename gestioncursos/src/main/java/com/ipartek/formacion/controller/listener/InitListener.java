package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContext;
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
	private final static Logger log=Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J="WEB-INF/conf/log4j.properties";
	
	

   
    public void contextInitialized(ServletContextEvent sce)  { 
        loadLog4j(sce);
    }

	private void loadLog4j(ServletContextEvent sce) {
		String ruta=sce.getServletContext().getRealPath("/");
		try {
			PropertyConfigurator.configure(ruta+PATH_LOG4J);
			if(LogManager.exists("PANTALLA")!=null){
				log.info("LOG CARGADO");
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
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
    private void loadProperties(ServletContext sC){
    	
    	
    	
    }
	
}
