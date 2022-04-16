package edu.school21.cinema.servlets;

import edu.school21.cinema.config.ApplicationConfig;
import edu.school21.cinema.models.Photo;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/images")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 10,     // 10 MB
        maxRequestSize = 1024 * 1024 * 100  // 100 MB
)
@Slf4j
public class ImageServlet extends HttpServlet {
    private ApplicationConfig applicationConfig;
    private ImageService imageService;

    @Override
    public void init(ServletConfig config) {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        imageService = context.getBean(ImageService.class);
        applicationConfig = context.getBean(ApplicationConfig.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            User user = (User) request.getSession().getAttribute("user");
            String path = "";

            for (Photo photo : user.getPhotos()) {
                if (String.valueOf(photo.getId()).equals(id)) {
                    path = photo.getPath();
                    break;
                }
            }

            request.getSession().setAttribute("path", path);
            request.getRequestDispatcher("/WEB-INF/image.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String uploadPath = request.getServletContext().getRealPath(applicationConfig.getImgPath()) + user.getId();
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            // mkdir return false
            boolean success = uploadDir.mkdirs();

            if (!success) {
                log.info("Can't create directory");
            }
        }

        List<Part> parts = request.getParts().stream()
                .filter(part -> part.getSize() > 0)
                .collect(Collectors.toList());

        for (Part part : parts) {
            String nameFile = new SimpleDateFormat("HHmmssddMMyyyyy").format(new Date());
            String uploadPathFile = uploadPath + File.separator + nameFile + part.getSubmittedFileName();
            String pathBd = applicationConfig.getImgPath() + user.getId() + File.separator + nameFile + part.getSubmittedFileName();

            part.write(uploadPathFile);

            Photo photo = new Photo(user.getId(), part.getSubmittedFileName(), pathBd, part.getSize(), part.getContentType());
            photo.setId(imageService.save(photo));
        }

        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
