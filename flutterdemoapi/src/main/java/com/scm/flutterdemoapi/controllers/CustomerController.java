package com.scm.flutterdemoapi.controllers;

import com.scm.flutterdemoapi.bl.services.CustomerService;
import com.scm.flutterdemoapi.forms.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping()
    public ResponseEntity<?> index(){
        return customerService.getCustomers();
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CustomerForm form){
        return customerService.createCustomer(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CustomerForm form){
        return customerService.updateCustomer(id, form);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }
}
