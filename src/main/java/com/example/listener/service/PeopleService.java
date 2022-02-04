package com.example.listener.service;

import com.example.listener.entity.response.PeopleResponse;

import java.util.List;

public interface PeopleService {

    List<PeopleResponse> getAll();

    PeopleResponse getById(String id) throws Exception;

    List<PeopleResponse> getByName(String name) throws Exception;

    void deleteById(String id) throws Exception;
}
