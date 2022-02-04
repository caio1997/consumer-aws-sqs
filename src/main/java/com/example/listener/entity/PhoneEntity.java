package com.example.listener.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "people_id", foreignKey = @ForeignKey(name = "ID_PEOPLE"))
    private PeopleEntity people = new PeopleEntity();

}
