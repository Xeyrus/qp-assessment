package com.questionpro.grocery.service;

import com.questionpro.grocery.entity.Grocery;

import java.util.List;

public interface GroceryService {

    List<Grocery> findAll();

    Grocery findById(int theId);

    Grocery save(Grocery theGrocery);

    void deleteById(int theId);

    List<Grocery> buyGroceries(List<Grocery> theList);
}
