package edu.school21.cinema.servlets;

import edu.school21.cinema.models.AuthData;
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
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private LoginService loginService;

    @Override
    public void init(ServletConfig config) {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        loginService = context.getBean(LoginService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<AuthData> authData = loginService.getAuth(user.getId());
        user.setAuthData(authData);
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
