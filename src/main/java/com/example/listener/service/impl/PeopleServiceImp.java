package com.example.listener.service.impl;

import com.example.listener.entity.PeopleEntity;
import com.example.listener.entity.PhoneEntity;
import com.example.listener.entity.response.PeopleResponse;
import com.example.listener.repository.PeopleRepository;
import com.example.listener.repository.PhoneRepository;
import com.example.listener.service.PeopleService;
import com.example.listener.service.converter.ConverterToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PeopleServiceImp implements PeopleService {

    @Autowired
    private ConverterToEntity converter;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<PeopleResponse> getAll() {
        List<PeopleEntity> response = peopleRepository.findAll();
        return converter.EntityToPeople(peopleRepository.findAll());
    }

    @Override
    public PeopleResponse getById(String id) throws Exception {
        Optional<PeopleEntity> response = peopleRepository.findById(Long.valueOf(id));
        if(response.isPresent())
            return converter.EntityToPeopleObject(response.get());
         else
            throw new Exception("Data is not found");

    }

    @Override
    public List<PeopleResponse> getByName(String name) throws Exception {
        List<PeopleEntity> response = peopleRepository.findAll();
        Stream<PeopleEntity> stream = response.stream().filter(e->
                e.getName().contains(name));
        List<PeopleEntity> collect = stream.collect(Collectors.toList());
        if(!collect.isEmpty())
            return converter.EntityToPeople(collect);
        else
            throw new Exception("Name is not found in database");

    }

    @Override
    public void deleteById(String id) throws Exception {
        Optional<PeopleEntity> response = peopleRepository.findById(Long.valueOf(id));
        if(response.isPresent())
            peopleRepository.deleteById(Long.valueOf(id));
        else
            throw new Exception("Data is not found");
    }


}
