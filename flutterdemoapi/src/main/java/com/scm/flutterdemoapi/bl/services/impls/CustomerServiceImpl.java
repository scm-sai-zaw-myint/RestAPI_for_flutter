package com.scm.flutterdemoapi.bl.services.impls;

import com.scm.flutterdemoapi.bl.dtos.CustomerDTO;
import com.scm.flutterdemoapi.bl.services.CustomerService;
import com.scm.flutterdemoapi.forms.CustomerForm;
import com.scm.flutterdemoapi.persistences.models.Customers;
import com.scm.flutterdemoapi.persistences.repositories.CustomerRepo;
import com.scm.flutterdemoapi.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public ResponseEntity<?> getCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();
        for(Customers customer: this.customerRepo.findAll()){
            customers.add(new CustomerDTO(customer));
        }
        return Response.send(HttpStatus.OK, true, "Get all customer success.", customers,null);
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerForm form) {
        Customers customer = new Customers(form);
        Customers savedCustomer = this.customerRepo.save(customer);
        return Response.send(HttpStatus.CREATED, true, "Create customer success!", new CustomerDTO(savedCustomer),null);
    }

    @Override
    public ResponseEntity<?> updateCustomer(Integer id, CustomerForm form) {
        Customers customers = this.customerRepo.getReferenceById(id);
        if(form.getId()!=null) customers.setId(form.getId());
        if(form.getName()!=null) customers.setName(form.getName());
        if(form.getEmail()!=null) customers.setEmail(form.getEmail());
        if(form.getPhone()!=null) customers.setPhone(form.getPhone());
        if(form.getAddress()!=null) customers.setAddress(form.getAddress());
        if(form.getPhone()!=null) customers.setPhone(form.getPhone());
        Customers updated = this.customerRepo.save(customers);
        return Response.send(HttpStatus.ACCEPTED, true, "Update customer success!", new CustomerDTO(updated),null);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Integer id) {
        Customers customers = this.customerRepo.getReferenceById(id);
        this.customerRepo.delete(customers);
        return Response.send(HttpStatus.ACCEPTED, true, "Delete customer success!", null,null);
    }
}
