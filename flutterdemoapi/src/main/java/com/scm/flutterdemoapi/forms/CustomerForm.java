package com.scm.flutterdemoapi.forms;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerForm {
    Integer id;
    String name;
    String email;
    String phone;
    String address;
    Date dob;
    String password;
}
