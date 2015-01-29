package swipe.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import swipe.back.config.SpringConfig;



@EnableAutoConfiguration
@ComponentScan(basePackages="swipe.back.config")
public class Swipe extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);

	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Swipe.class);
    }

}
