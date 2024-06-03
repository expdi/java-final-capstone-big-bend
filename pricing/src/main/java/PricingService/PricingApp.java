package PricingService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PricingApp {
    public static void main(String[] args) {
        SpringApplication.run(PricingApp.class,args);
    }

    @Component
    class PricingIni implements CommandLineRunner{
        public void run(String... args) throws Exception{
            System.out.println("Here we go with Spring Boot");
        }
    }
}
