package com.example.listener.listener;

import com.example.listener.listener.input.PeopleInput;
import com.example.listener.service.MessageReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerMessage {

    @Autowired
    private MessageReceivedService messageReceivedService;

    @SqsListener(value = "${cloud.aws.end-point.uri}" ,deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(PeopleInput message) {
            messageReceivedService.persist(message);
    }

}
