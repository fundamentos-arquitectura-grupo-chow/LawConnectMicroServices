package org.lorem.profilesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProfilesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfilesServiceApplication.class, args);
    }

}
