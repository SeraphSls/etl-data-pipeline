package br.com.excel.maniac.services;

import br.com.excel.maniac.config.RabbitConfig;
import br.com.excel.maniac.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExcelProducer {
    private final RabbitTemplate rabbitTemplate;

    public ExcelProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(Message message) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                message
        );
    }

}
