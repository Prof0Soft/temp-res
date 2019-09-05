package by.profsoft.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class of application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class TaskSpringApplication {

    /**
     * Main method.
     *
     * @param args the arguments for main method.
     */
    public static void main(final String[] args) {
        SpringApplication.run(TaskSpringApplication.class, args);
    }

}
