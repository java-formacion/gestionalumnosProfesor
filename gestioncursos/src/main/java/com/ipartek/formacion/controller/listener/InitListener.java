package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class initListener
 *
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {

	private final static Logger log = Logger.getLogger(InitListener.class);//variable para hacer el log
	private final static String PATH_LOG4J = "WEB-INF/conf/log4j.properties";
	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scab)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
         
    	loadLog4j(sce);
    }

	private void loadLog4j(ServletContextEvent sce) {
		
		String ruta = sce.getServletContext().getRealPath("/");//lo tomamos que el acceso a la parte raiz de la app, que al juntarlo al web-inf de la siguiente fila, hacemos la ruta relativa entera
		PropertyConfigurator.configure(ruta+PATH_LOG4J); //cargar fichero de properties que hemos creado previamente en WEB-INF
		
		try{
			
			PropertyConfigurator.configure(ruta+PATH_LOG4J);
			log.info("LOG CARGADO");
		}catch(Exception e){
			
		}
		
		
	}
	
}
