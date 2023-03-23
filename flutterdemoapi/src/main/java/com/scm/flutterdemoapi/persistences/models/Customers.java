package com.scm.flutterdemoapi.persistences.models;

import com.scm.flutterdemoapi.forms.CustomerForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String phone;
    @Column
    String address;
    @Column
    Date dob;
    @Column
    String password;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    Date updatedAt;

    public Customers(CustomerForm form){
        this.name = form.getName();
        this.email = form.getEmail();
        this.phone = form.getPhone();
        this.address = form.getAddress();
        this.password = form.getPassword();
        this.dob = form.getDob();
    }
}
