package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // getSession(false) возвращает null
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (loggedIn && (requestURI.equals("/signIn") || requestURI.equals("/signUp"))) {
            response.sendRedirect(request.getContextPath() + "/profile");
        } else if (!loggedIn && requestURI.equals("/profile")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
