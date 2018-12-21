package oc.projet.biblio.client.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Paths;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages="oc.projet.biblio.client")
@PropertySource("classpath:application.properties")
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.err.println(Paths.get(System.getProperty("java.io.tmpdir")));
    }
}