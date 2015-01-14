package swipe.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import swipe.back.filter.CORSFilter;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="swipe.back.dao")
@ComponentScan(basePackages={"swipe.back.controller", "swipe.back.services", "swipe.back.filter"})
@EntityScan(basePackages="swipe.back.domain")
public class SpringConfig {
	//@Autowired
	//CORSFilter corsFilter;
}
