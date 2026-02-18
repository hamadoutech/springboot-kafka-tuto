package tech.hamadou.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.hamadou.springboot.kafka.JsonKafkaProducer;
import tech.hamadou.springboot.kafka.KafkaProducer;
import tech.hamadou.springboot.model.OrderEvent;

@RestController
@RequestMapping("/api/v1/orders/events")
public class OrderController {

    private final JsonKafkaProducer jsonKafkaProducer;

    public OrderController(final JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @RequestMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody OrderEvent orderEvent) {
        jsonKafkaProducer.envoyerMessage(orderEvent);
       return ResponseEntity.ok("Event envoyé avec succès");
    }
}
