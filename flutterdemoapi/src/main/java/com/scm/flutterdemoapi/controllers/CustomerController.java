package com.scm.flutterdemoapi.controllers;

import com.scm.flutterdemoapi.bl.services.CustomerService;
import com.scm.flutterdemoapi.forms.CustomerForm;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("list")
    public ResponseEntity<?> index(@Nullable @RequestParam Integer page){
        return customerService.getCustomers(page);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CustomerForm form){
        return customerService.createCustomer(form);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody CustomerForm form){
        return customerService.updateCustomer(form);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }

    @DeleteMapping("patchDelete")
    public ResponseEntity<?> deleteItems(@RequestParam("items") List<Integer> items){
        return customerService.deleteItems(items);
    }
}
