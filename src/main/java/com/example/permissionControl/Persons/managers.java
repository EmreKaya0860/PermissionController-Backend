package com.example.permissionControl.Persons;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "managers")
@Getter
@Setter
public class managers {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String gender;
    private String status;
    private String department;
    private Date started_date;

    public managers(Integer id, String name, String email, String phone, String city, String gender, String status, String department, Date started_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.gender = gender;
        this.status = status;
        this.department = department;
        this.started_date = started_date;
    }

    public managers() {
    }

}
