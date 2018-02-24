package com.houoy.game.saigou.config;

import com.houoy.common.vo.SessionRootUserVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by andyzhao on 2/24/2018.
 */
public class URLInterceptor implements HandlerInterceptor {
    public static final String Default_Session_Key = "user";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();

        //如果是其他请求地址，进行拦截
        HttpSession session = request.getSession();
        String sessionID = session.getId();
        SessionRootUserVO sessionRootUserVO = (SessionRootUserVO) session.getAttribute(Default_Session_Key);
        if (sessionRootUserVO != null) {
            String sessionid = SessionData.getSessionIDMap().get(sessionRootUserVO.getSession_user_code());
            //如果用户名存在（即登录放行）
            if (sessionid.equals(sessionID)) {
                return true;
            } else { //如果请求的sessionID和此账号Map中存放的sessionID不一致，跳转到登陆页
                //判断如果是异步请求，设置响应头 sessionstatus为timeout，自动跳转，否则重定向
                if (request.getHeader("x-requested-with") != null
                        && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    response.setHeader("sessionstatus", "timeout");
                    //让浏览器用utf8来解析返回的数据
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    //是告诉servlet用UTF-8转码，而不是用默认的ISO8859
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("<p>用户已经在其他地方登录</p>");
                    return false;
                } else {
//                    String indexurl = request.getContextPath() + "/login.do";
//                    response.sendRedirect(indexurl);
                    //让浏览器用utf8来解析返回的数据
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    //是告诉servlet用UTF-8转码，而不是用默认的ISO8859
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("<p>用户已经在其他地方登录</p>");
                    return false;
                }
            }
        }

        //如果session中没有admin，跳转到登陆页
//        String contextPath = request.getContextPath();
//        request.getRequestDispatcher(contextPath + "/index.html").forward(request, response);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("<p>用户已经在其他地方登录</p>");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 获取访问的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
