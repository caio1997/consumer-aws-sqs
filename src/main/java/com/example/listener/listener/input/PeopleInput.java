package com.example.listener.listener.input;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleInput {
    private String name;
    @JsonAlias("last-name")
    private String lastName;
    private Integer age;
    private AddressInput address;
    private List<PhoneInput> phone;
}
