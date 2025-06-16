package org.lorem.followupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FollowUpServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowUpServiceApplication.class, args);
    }

}
