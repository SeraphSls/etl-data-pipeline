package br.com.excel.maniac.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerDebug {

    @KafkaListener(topics = "raw-data-import", groupId = "excel-maniac-group")
    public void listen(String message) {
        System.out.println("Mensagem recebida do Kafka: " + message);
    }
}
