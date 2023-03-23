package com.scm.flutterdemoapi.persistences.repositories;

import com.scm.flutterdemoapi.persistences.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
}
