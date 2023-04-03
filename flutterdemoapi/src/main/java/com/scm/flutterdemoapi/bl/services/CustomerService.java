package com.scm.flutterdemoapi.bl.services;

import com.scm.flutterdemoapi.forms.CustomerForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    public ResponseEntity<?> getCustomers(Integer page);
    public ResponseEntity<?> createCustomer(CustomerForm form);
    public ResponseEntity<?> updateCustomer(CustomerForm form);
    public ResponseEntity<?> deleteCustomer(Integer id);
    public ResponseEntity<?> deleteItems(List<Integer> items);
}
