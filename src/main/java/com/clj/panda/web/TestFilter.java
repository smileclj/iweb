package com.clj.panda.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wana on 2015/10/13.
 */
public class TestFilter implements Filter{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("过滤前");
        filterChain.doFilter(servletRequest, servletResponse);//放行。让其走到下个链或目标资源中
        logger.debug("过滤后");
    }

    @Override
    public void destroy() {

    }
}
