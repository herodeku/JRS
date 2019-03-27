package com.graduate.jrsmain.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RabbitUtil {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private List<String> populace;
    private List<String> judicialOfficer;

    public List<String> getPopulace() {
        return populace;
    }

    public void setPopulace(List<String> populace) {
        this.populace = populace;
    }

    public List<String> getJudicialOfficer() {
        return judicialOfficer;
    }

    public void setJudicialOfficer(List<String> judicialOfficer) {
        this.judicialOfficer = judicialOfficer;
    }

    @RabbitListener(queues = "Queue.populace")
    public void populaceListener(List<String> list){
        populace=list;
        //populace = (List<String>) rabbitTemplate.receiveAndConvert("Queue.populace");
    }
    @RabbitListener(queues = "Queue.judicialOfficer")
    public void judicialOfficerListener(List<String> list){
        judicialOfficer=list;
        //judicialOfficer = (List<String>) rabbitTemplate.receiveAndConvert("Queue.judicialOfficer");
    }
}
