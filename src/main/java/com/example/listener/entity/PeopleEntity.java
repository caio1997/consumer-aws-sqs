package com.example.listener.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "people")
public class PeopleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Integer age;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "ID_ADDRESS"))
    private AddressEntity address = new AddressEntity();

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<PhoneEntity> phone = new ArrayList<>();;

    public PeopleEntity(Long id, String name, String lastName, Integer age, AddressEntity address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }
}
