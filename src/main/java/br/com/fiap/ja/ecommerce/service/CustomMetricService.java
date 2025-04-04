package br.com.fiap.ja.ecommerce.service;





import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricService {

    public final Counter customMetricCounter;

    public CustomMetricService(MeterRegistry meterRegistry){
        customMetricCounter = Counter.builder("contador_envio_mq")
                .description("contagem de mensagens ms")
                .tags("ambiente", "dev")
                .register(meterRegistry);
    }

    public void incrementCounter(){
        customMetricCounter.increment();
    }
}
