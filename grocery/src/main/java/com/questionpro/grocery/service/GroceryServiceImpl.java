package com.questionpro.grocery.service;

import com.questionpro.grocery.dao.GroceryRepository;
import com.questionpro.grocery.entity.Grocery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    private GroceryRepository groceryRepository;

    @Autowired
    public GroceryServiceImpl(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    @Override
    public List<Grocery> findAll() {
        return groceryRepository.findAll();
    }

    @Override
    public Grocery findById(int theId) {
        Optional<Grocery> result = groceryRepository.findById(theId);

        Grocery theGrocery = null;

        if(result.isPresent()) {
            theGrocery = result.get();
        }

        return theGrocery;
    }

    @Override
    public Grocery save(Grocery theGrocery) {
        return groceryRepository.save(theGrocery);
    }

    @Override
    public void deleteById(int theId) {
        groceryRepository.deleteById(theId);
    }

    @Transactional
    @Override
    public List<Grocery> buyGroceries(List<Grocery> theList) {
        List<Grocery> newList = new ArrayList<>();

        for(int i=0;i<theList.size();i++){
            Grocery theGrocery = theList.get(i);

            //Finding grocery item in db
            Grocery dbGrocery = findById(theGrocery.getId());

            //Adding item to list only if it is available in database
            if(dbGrocery!=null) {

                int itemInDb = dbGrocery.getCount();
                int required = theGrocery.getCount();

                //Changing count for dbGrocery item
                if(itemInDb>required ){
                    dbGrocery.setCount(itemInDb-required);
                }
                else{
                    theGrocery.setCount(itemInDb);
                    dbGrocery.setCount(0);
                }

                //Updating database entry
                save(dbGrocery);

                //Providing values to returned grocery list
                theGrocery.setName(dbGrocery.getName());
                theGrocery.setPrice(dbGrocery.getPrice());

                //Adding available item to list
                newList.add(theGrocery);
            }
        }
        return newList;
    }
}
