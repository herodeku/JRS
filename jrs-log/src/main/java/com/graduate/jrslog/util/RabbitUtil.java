package com.graduate.jrslog.util;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RabbitUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    public void populaceEnqueue(List<String> list){
        rabbitTemplate.convertAndSend("Exchange.populace","Queue.populace",list);
    }
    public void judicialOfficerEnqueue(List<String> list){
        rabbitTemplate.convertAndSend("Exchange.judicialOfficer","Queue.judicialOfficer",list);
    }
    private void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("Exchange.populace"));
        amqpAdmin.declareExchange(new DirectExchange("Exchange.judicialOfficer"));
        amqpAdmin.declareQueue(new Queue("Queue.populace"));
        amqpAdmin.declareQueue(new Queue("Queue.judicialOfficer"));
        amqpAdmin.declareBinding(new Binding("Queue.populace",
                Binding.DestinationType.EXCHANGE,
                "Exchange.populace",
                "Queue.populace",
                null));
        amqpAdmin.declareBinding(new Binding("Queue.popjudicialOfficerulace",
                Binding.DestinationType.EXCHANGE,
                "Exchange.judicialOfficer",
                "Queue.judicialOfficer",
                null));
    }
}
