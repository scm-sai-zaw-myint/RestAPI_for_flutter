package com.scm.flutterdemoapi.persistences.repositories;

import com.scm.flutterdemoapi.persistences.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
    @Query(value = "SELECT * FROM customers ORDER BY created_at DESC", nativeQuery = true)
    List<Customers> getAllCustomers();
}
