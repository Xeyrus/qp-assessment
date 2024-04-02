package com.questionpro.grocery.dao;

import com.questionpro.grocery.entity.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Grocery,Integer> {
}
