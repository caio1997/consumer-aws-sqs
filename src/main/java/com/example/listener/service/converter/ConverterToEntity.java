package com.example.listener.service.converter;

import com.example.listener.entity.AddressEntity;
import com.example.listener.entity.PeopleEntity;
import com.example.listener.entity.PhoneEntity;
import com.example.listener.entity.response.AddressResponse;
import com.example.listener.entity.response.PeopleResponse;
import com.example.listener.entity.response.PhoneResponse;
import com.example.listener.listener.input.AddressInput;
import com.example.listener.listener.input.PeopleInput;
import com.example.listener.listener.input.PhoneInput;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ConverterToEntity {

    public PeopleResponse EntityToPeopleObject(PeopleEntity peopleEntity) {
                return new PeopleResponse(
                        peopleEntity.getName(),
                        peopleEntity.getLastName(),
                        peopleEntity.getAge(),
                        entityToAddress(peopleEntity.getAddress()),
                        entityToPhone(peopleEntity.getPhone())
                );
    }

    public List<PeopleResponse> EntityToPeople(List<PeopleEntity> peopleEntity) {
        Stream<PeopleResponse> obj = peopleEntity.stream().map(e ->
                new PeopleResponse(
                        e.getName(),
                        e.getLastName(),
                        e.getAge(),
                        entityToAddress(e.getAddress()),
                        entityToPhone(e.getPhone())
                )
        );
        return obj.collect(Collectors.toList());
    }

    private AddressResponse entityToAddress(AddressEntity addressEntity) {
        return new AddressResponse(
                addressEntity.getStreet(),
                addressEntity.getDistrict(),
                addressEntity.getNumber()
        );
    }

    private List<PhoneResponse> entityToPhone(List<PhoneEntity> phoneEntity) {
        Stream<PhoneResponse> obj = phoneEntity.stream().map(e ->
                new PhoneResponse(
                        e.getNumber()
                )
        );
        return obj.collect(Collectors.toList());
    }

    public PeopleEntity peopleToEntity(PeopleInput peopleInput) {
        return new PeopleEntity(null,
                peopleInput.getName(),
                peopleInput.getLastName(),
                peopleInput.getAge(),
                addressToEntity(peopleInput.getAddress()
                ));
    }

    private AddressEntity addressToEntity(AddressInput addressInput) {
        return new AddressEntity(
                null,
                addressInput.getStreet(),
                addressInput.getDistrict(),
                addressInput.getNumber()
        );
    }

    public List<PhoneEntity> phoneToEntity(List<PhoneInput> phoneInput, PeopleEntity people) {
        Stream<PhoneEntity> obj = phoneInput.stream().map(e ->
                new PhoneEntity(
                        null,
                        e.getNumber(),
                        new PeopleEntity(
                                people.getId(),
                                people.getName(),
                                people.getLastName(),
                                people.getAge(),
                                people.getAddress()
                        )
                )
        );
        return obj.collect(Collectors.toList());
    }

}
