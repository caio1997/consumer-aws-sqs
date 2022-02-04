package com.example.listener.listener.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInput {
    private String street;
    private String district;
    private Integer number;
}
