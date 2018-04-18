package com.csg.intshop.startup;

import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by admin on 2017/11/1.
 */

@WebListener
public class StartupListener implements ServletContextListener {

    public StartupListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LogManager.shutdown();
    }
}
