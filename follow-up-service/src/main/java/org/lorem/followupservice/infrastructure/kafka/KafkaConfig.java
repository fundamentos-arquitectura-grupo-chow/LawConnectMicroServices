package org.lorem.followupservice.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic followupTopic() {
        return new NewTopic("followup-topic", 1, (short) 1);
    }
}