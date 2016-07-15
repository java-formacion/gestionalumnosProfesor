package com.ipartek.formacion.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.controller.Constantes;

public class InitListener implements ServletContextListener,
		ServletContextAttributeListener {
	public final static String PROPS_NAME="properties";
	private final static Logger LOG = Logger.getLogger(InitListener.class);
	private final static String PATH_LOG4J = "WEB-INF/conf/log4j.properties";

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {

	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		loadLog4j(sce);
	}

	public void loadProperties(ServletContextEvent sce) {
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();
		InputStream inputstream = classloader
				.getResourceAsStream("constantes.properties");
		Properties props = new Properties();
		try {
			props.load(inputstream);
		} catch (IOException e) {
			LOG.error("no se han cargado las properties");
		}
		sce.getServletContext().setAttribute(PROPS_NAME, props);
	}

	private void loadLog4j(ServletContextEvent sce) {
		String ruta = sce.getServletContext().getRealPath("/");
		try {
			PropertyConfigurator.configure(ruta + PATH_LOG4J);
			/* LOG */LOG.info("LOaded LOg");
		} catch (Exception e) {

		}
	}

}
