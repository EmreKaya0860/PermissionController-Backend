package com.example.permissionControl.Persons;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "permission_list")
@Getter
@Setter

public class permissionTable {

    @Id
    private Integer id;
    private String status;
    private Date start_date;
    private Date finish_date;
    private Integer taken_by;
    private Integer confirm_by;

    public permissionTable(Integer id, String status, Date start_date, Date finish_date, Integer taken_by, Integer confirm_by) {
        this.id = id;
        this.status = status;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.taken_by = taken_by;
        this.confirm_by = confirm_by;
    }

    public permissionTable() {
    }
}
