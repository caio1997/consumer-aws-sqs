package com.example.listener.entity.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleResponse {
    private String name;
    private String lastName;
    private Integer age;
    private AddressResponse address;
    private List<PhoneResponse> phone;

}
