package fr.customer.accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class Application {
    public static void main(String[] args) {
        System.out.println("Test branche");
        System.out.println("Test branche from test branche");
        SpringApplication.run(Application.class, args);
    }
}