package com.example.listener.service;

import com.example.listener.entity.PeopleEntity;
import com.example.listener.entity.PhoneEntity;
import com.example.listener.listener.input.PeopleInput;
import com.example.listener.repository.PeopleRepository;
import com.example.listener.repository.PhoneRepository;
import com.example.listener.service.converter.ConverterToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageReceivedService {

    @Autowired
    private ConverterToEntity converter;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public void persist(PeopleInput message) {

        PeopleEntity people = peopleRepository.save(converter.peopleToEntity(message));

        if(people.getId() != null) {
            List<PhoneEntity> list = converter.phoneToEntity(message.getPhone(), people);
            phoneRepository.saveAll(list);
        }

    }
}
