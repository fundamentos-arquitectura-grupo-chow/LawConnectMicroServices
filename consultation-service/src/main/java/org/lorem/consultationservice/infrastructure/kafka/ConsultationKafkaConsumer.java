package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsultationKafkaConsumer {

    @KafkaListener(topics = "consultation-topic", groupId = "consultation-group")
    public void consumeMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
        // Procesar el mensaje aquí
    }
}