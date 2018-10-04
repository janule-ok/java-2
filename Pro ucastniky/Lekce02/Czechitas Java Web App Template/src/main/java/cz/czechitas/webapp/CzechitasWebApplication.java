package cz.czechitas.webapp;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.support.*;

@SpringBootApplication
public class CzechitasWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder()
                .sources(CzechitasWebApplication.class)
                .build();
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CzechitasWebApplication.class);
    }

}
