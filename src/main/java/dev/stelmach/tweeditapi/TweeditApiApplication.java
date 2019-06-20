package dev.stelmach.tweeditapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TweeditApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TweeditApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TweeditApiApplication.class);
    }

}
