package com.clj.panda.web.interceptors;

import com.clj.panda.common.enums.PandaCode;
import com.clj.panda.common.resp.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wana on 2015/10/13.
 */
public class PandaInterceptor implements HandlerInterceptor{
    /**
     * 该方法将在Controller处理之前进行调用
     * 当preHandle的返回值为false的时候整个请求就结束了
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //解决ajax跨域问题
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        return true;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行
     * 在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
     * 也就是说在这个方法中你可以对ModelAndView进行操作
     * @param request
     * @param response
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
