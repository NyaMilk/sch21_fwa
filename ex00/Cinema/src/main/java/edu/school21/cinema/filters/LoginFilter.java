package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signUp", "/signIn"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
//        HttpSession session = request.getSession(false);
        String loginPath = request.getRequestURI();
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (loggedIn || loginPath.startsWith("/signIn") || loginPath.startsWith("/signUp")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/signIn");
        }
    }
}
