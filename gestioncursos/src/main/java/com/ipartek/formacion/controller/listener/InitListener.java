package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class InitListener implements ServletContextListener, ServletContextAttributeListener
{
	private final static Logger log = Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J ="WEB-INF/conf/log4j.properties";


	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0)
	{
		
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0)
	{
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0){}

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		loadLog4j(sce);
	}

	private void loadLog4j(ServletContextEvent sce)
		{
			String ruta = sce.getServletContext().getRealPath("/");
			try
			{
				PropertyConfigurator.configure(ruta+PATH_LOG4J);
				/*LOG*/ log.info("LOaded LOg");
			}
			catch(Exception e)
			{
				
			}
		}

}
