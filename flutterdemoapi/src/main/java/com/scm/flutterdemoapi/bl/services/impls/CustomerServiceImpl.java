package com.scm.flutterdemoapi.bl.services.impls;

import com.scm.flutterdemoapi.bl.dtos.CustomerDTO;
import com.scm.flutterdemoapi.bl.services.CustomerService;
import com.scm.flutterdemoapi.common.Pagination;
import com.scm.flutterdemoapi.forms.CustomerForm;
import com.scm.flutterdemoapi.persistences.models.Customers;
import com.scm.flutterdemoapi.persistences.repositories.CustomerRepo;
import com.scm.flutterdemoapi.responses.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Value("${app.pagination.limit}")
    private Integer limit;

    @Override
    public ResponseEntity<?> getCustomers(Integer page) {
        List<CustomerDTO> customers = new ArrayList<>();
        for(Customers customer: this.customerRepo.getAllCustomers()){
            customers.add(new CustomerDTO(customer));
        }
        page = page == null ? 1 : page;
        Pagination pagination = new Pagination(customers, page, limit,"customers/list");
        if(pagination.getData() == null) {
            return Response.send(HttpStatus.ACCEPTED, false, "Not data", null,null,null);
        }
        return Response.send(HttpStatus.OK, true, "Get all customer success.", pagination.getData(),null, pagination);
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerForm form) {
        Customers customer = new Customers(form);
        Customers savedCustomer = this.customerRepo.save(customer);
        return Response.send(HttpStatus.CREATED, true, "Create customer success!", new CustomerDTO(savedCustomer),null, null);
    }

    @Override
    public ResponseEntity<?> updateCustomer(CustomerForm form) {
        Customers customers = this.customerRepo.getReferenceById(form.getId());
        if(form.getId()!=null) customers.setId(form.getId());
        if(form.getName()!=null) customers.setName(form.getName());
        if(form.getEmail()!=null) customers.setEmail(form.getEmail());
        if(form.getPhone()!=null) customers.setPhone(form.getPhone());
        if(form.getAddress()!=null) customers.setAddress(form.getAddress());
        if(form.getPhone()!=null) customers.setPhone(form.getPhone());
        if(form.getDob()!=null) customers.setDob(form.getDob());
        Customers updated = this.customerRepo.save(customers);
        return Response.send(HttpStatus.ACCEPTED, true, "Update customer success!", new CustomerDTO(updated),null, null);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Integer id) {
        Customers customers = this.customerRepo.getReferenceById(id);
        this.customerRepo.delete(customers);
        return Response.send(HttpStatus.ACCEPTED, true, "Delete customer success!", null,null, null);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteItems(List<Integer> items) {
        this.customerRepo.deleteCustomersByIdIn(items);
        return Response.send(HttpStatus.ACCEPTED, true, "Delete "+items.size()+" customers success!", null,null, null);
    }
}
