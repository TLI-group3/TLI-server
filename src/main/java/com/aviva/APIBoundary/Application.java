package main.java.com.aviva.APIBoundary;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
	
	@Configuration
	public class MyConfiguration {

	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
		    @Override
		    public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
		    }
		};
	    }
	}

}
