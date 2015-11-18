package com.clj.panda.web;

import com.clj.panda.mapper.test.TestStudentMapper;
import com.clj.panda.service.TestService;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wana on 2015/10/13.
 */
public class TestListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TestStudentMapper testStudentMapper = (TestStudentMapper)WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean("testStudentMapper");
        System.out.println("容器启动了");
        System.out.println(testStudentMapper);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
