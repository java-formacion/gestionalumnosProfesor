package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener, ServletContextAttributeListener {
	private final static Logger log = Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J ="";
	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0)  {
		// TODO Auto-generated method stub
	}

}
