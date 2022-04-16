package edu.school21.cinema.servlets;

import edu.school21.cinema.exceptions.ValidException;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LoginService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signUp", "/signIn"})
public class LoginServlet extends HttpServlet {
    private final String JSP_SIGN_IN_PATH = "/WEB-INF/signIn.jsp";
    private final String JSP_SIGN_UP_PATH = "/WEB-INF/signUp.jsp";
    private LoginService loginService;

    @Override
    public void init(ServletConfig config) {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        loginService = context.getBean(LoginService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/signIn")) {
            request.getRequestDispatcher(JSP_SIGN_IN_PATH).forward(request, response);
        } else if (requestURI.startsWith("/signUp")) {
            request.getRequestDispatcher(JSP_SIGN_UP_PATH).forward(request, response);
        }
    }

    protected void processSignId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty()) {
            throw new ValidException("Please enter username", JSP_SIGN_IN_PATH);
        }

        if (password == null || password.isEmpty()) {
            throw new ValidException("Please enter password", JSP_SIGN_IN_PATH);
        }

        User user = loginService.findUser(username, password);

        if (user == null) {
            throw new ValidException("Unknown login, please try again", JSP_SIGN_IN_PATH);
        } else if (user.getPassword() == null) {
            throw new ValidException("Wrong password, please try again", JSP_SIGN_IN_PATH);
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }

    protected void processSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");


        if (username == null || username.isEmpty() || lastname == null || lastname.isEmpty() || password == null || password.isEmpty() || phone == null || phone.isEmpty()) {
            throw new ValidException("You must fill in all of the fields", JSP_SIGN_UP_PATH);
        }

        if (loginService.signUp(new User(username, lastname, password, phone))) {
            request.getSession().setAttribute("user", username);
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            throw new ValidException("User with name already exists", JSP_SIGN_UP_PATH);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        try {
            if (requestURI.startsWith("/signIn")) {
                processSignId(request, response);
            } else if (requestURI.startsWith("/signUp")) {
                processSignUp(request, response);
            }
        } catch (ValidException ex) {
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher(ex.getPathURI()).forward(request, response);
        }
    }
}
