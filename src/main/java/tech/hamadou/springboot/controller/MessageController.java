package tech.hamadou.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.hamadou.springboot.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(final KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @RequestMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
       kafkaProducer.envoyerMessage(message);
       return ResponseEntity.ok("Message envoyé avec succès");
    }
}
