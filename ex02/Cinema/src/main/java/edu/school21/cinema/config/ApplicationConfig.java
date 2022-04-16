package edu.school21.cinema.config;

import edu.school21.cinema.repositories.ImageRepository;
import edu.school21.cinema.repositories.ImageRepositoryImpl;
import edu.school21.cinema.repositories.LoginRepository;
import edu.school21.cinema.repositories.LoginRepositoryImpl;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.ImageServiceImpl;
import edu.school21.cinema.services.LoginService;
import edu.school21.cinema.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driverName;
    @Value("${storage.path}")
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverName);
        return dataSource;
    }

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginRepository loginRepository() {
        return new LoginRepositoryImpl(dataSource());
    }

    @Bean
    public LoginService loginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        return new LoginServiceImpl(loginRepository, passwordEncoder);
    }

    @Bean
    public ImageRepository imageRepository() {
        return new ImageRepositoryImpl(dataSource());
    }

    @Bean
    public ImageService imageService(ImageRepository imageRepository) {
        return new ImageServiceImpl(imageRepository);
    }
}
