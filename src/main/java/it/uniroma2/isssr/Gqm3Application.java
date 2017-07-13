package it.uniroma2.isssr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author emanuele
 */

@SpringBootApplication
public class Gqm3Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Gqm3Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Gqm3Application.class, args);
    }
}

