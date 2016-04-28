package spssoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class App {

    public static void main(String... args) throws Exception {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }
}