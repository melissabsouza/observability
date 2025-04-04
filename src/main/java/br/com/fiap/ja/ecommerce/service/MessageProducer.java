package br.com.fiap.ja.ecommerce.service;

import br.com.fiap.ja.ecommerce.dto.ProdutoDTO;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private CustomMetricService customMetricService;

    @Timed("time-send-message")
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("meu.exchange", "minha.routingKey", message);
        customMetricService.incrementCounter();
    }

    public void sendMessage(ProdutoDTO produto) {
        String message = produto.toString();
        this.sendMessage(message);
    }

}

