package com.scm.flutterdemoapi.bl.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scm.flutterdemoapi.persistences.models.Customers;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    Integer id;
    String name;
    String email;
    String phone;
    String address;
    Date dob;
    @JsonIgnore
    String password;
    Date createdAt;
    Date updatedAt;

    public CustomerDTO(Customers customers){
        this.id = customers.getId();
        this.name = customers.getName();
        this.email = customers.getEmail();
        this.phone = customers.getPhone();
        this.address = customers.getAddress();
        this.dob = customers.getDob();
        this.password = customers.getPassword();
        this.createdAt = customers.getCreatedAt();
        this.updatedAt = customers.getUpdatedAt();
    }
}
