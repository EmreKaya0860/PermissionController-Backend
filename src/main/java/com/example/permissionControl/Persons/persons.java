package com.example.permissionControl.Persons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@Getter
@Setter
public class persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String gender;
}
