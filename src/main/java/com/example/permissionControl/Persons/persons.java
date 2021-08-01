package com.example.permissionControl.Persons;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class persons {


    @Id
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String gender;


    public persons(Integer id, String name, String email, String phone, String city, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
    }

    public persons() {
    }

}
