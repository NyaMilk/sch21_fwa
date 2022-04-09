package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LoginService;
import edu.school21.cinema.services.LoginServiceImpl;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/signUp", "/signIn"})
public class LoginServlet extends HttpServlet {
    private LoginService loginService;

    @Override
    public void init(ServletConfig config) {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        loginService = context.getBean(LoginService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Response from servlet :3");

        String path = request.getRequestURI();
        writer.println(path);

        User user = loginService.findUserById(1L);
        writer.println(user);

        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/html/signIn.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
//            User user = loginService.findUser(username, password);
            User user = loginService.findUserById(1L);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/main");
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        response.setContentType("text/html");
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
