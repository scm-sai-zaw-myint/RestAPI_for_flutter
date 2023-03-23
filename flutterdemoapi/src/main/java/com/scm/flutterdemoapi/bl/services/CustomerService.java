package com.scm.flutterdemoapi.bl.services;

import com.scm.flutterdemoapi.forms.CustomerForm;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public ResponseEntity<?> getCustomers();
    public ResponseEntity<?> createCustomer(CustomerForm form);
    public ResponseEntity<?> updateCustomer(Integer id, CustomerForm form);

    public ResponseEntity<?> deleteCustomer(Integer id);
}
