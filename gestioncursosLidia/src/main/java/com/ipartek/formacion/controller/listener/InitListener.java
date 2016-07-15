package com.ipartek.formacion.controller.listener;

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
 *cargar properties 
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {
   
	//log de la librería que hemos importado
	private final static Logger LOG=Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J="WEB-INF/conf/log4j.properties";//dentro de WEB-INF,creamos carpeta conf,creamos archivo de properties
    //PROPERTIES
	public final static String PROPS_NAME="properties";
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
    public void contextDestroyed(ServletContextEvent sce)  { //cuando se para la app,justo antes
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
    public void contextInitialized(ServletContextEvent sce)  { //cuando se despliega la app
         loadLog4j(sce);
         //cargar properties
         loadProperties(sce);
    }

	private void loadProperties(ServletContextEvent sce) {
		ClassLoader classloader=Thread.currentThread().getContextClassLoader();//acceder a la carpera de maven de resources
		InputStream input=classloader.getResourceAsStream("constantes.properties");
		
		Properties props=new Properties();
		try{
			props.load(input);
		}catch(Exception e){
			LOG.error("no se han cargado las properties");
		}
		
	}

	private void loadLog4j(ServletContextEvent sce) {
		//ruta absoluta donde está el contexto
		String ruta=sce.getServletContext().getRealPath("/");//raíz de la app web,la carpeta donde está la app
		
		
		//Comprobar si está bien cargado
		try{
			//cargar el logger que hemos creado en properties
			PropertyConfigurator.configure(ruta+PATH_LOG4J);//la ruta absoluta del fichero 
			LOG.info("LOG CARGADO");
			/*if(LogManager.exists("PANTALLA")!=null){//comprobar si existe el logger (pantalla o fichero)
				log.info("LOG CARGADO");
			}else{
				log.info("pasa");
			}*/
				
		}catch(Exception e){
			
		}
		
	}
	
}
