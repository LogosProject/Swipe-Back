package swipe.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import swipe.back.config.SpringConfig;



@EnableAutoConfiguration
@ComponentScan(basePackages="swipe.back.config")
public class Swipe {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);

	}

}
