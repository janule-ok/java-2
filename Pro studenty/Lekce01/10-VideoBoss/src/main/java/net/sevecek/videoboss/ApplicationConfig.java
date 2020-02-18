package net.sevecek.videoboss;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import net.sevecek.boot.war.*;

@Configuration
@ComponentScan
@EnableWebMvc
public class ApplicationConfig extends DefaultWebMvcConfigurer {

}
