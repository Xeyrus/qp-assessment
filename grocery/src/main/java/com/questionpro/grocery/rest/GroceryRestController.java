package com.questionpro.grocery.rest;

import com.questionpro.grocery.entity.Grocery;
import com.questionpro.grocery.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroceryRestController {

    private GroceryService groceryService;

    @Autowired
    public GroceryRestController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping("/groceries")
    public List<Grocery> findAll(){
        return groceryService.findAll();
    }

    @GetMapping("/groceries/{groceryId}")
    public Grocery getGrocery(@PathVariable int groceryId){
        Grocery theGrocery = groceryService.findById(groceryId);

        if(theGrocery == null){
            throw new RuntimeException("Grocery not found with id : "+groceryId);
        }

        return theGrocery;
    }

    @PostMapping("/groceries")
    public Grocery addGrocery(@RequestBody Grocery theGrocery){

        //Setting Id to 0, just in case user passes Id along with the body
        //This forces a save rather than update
        theGrocery.setId(0);

        Grocery dbGrocery = groceryService.save(theGrocery);

        return dbGrocery;
    }

    @PutMapping("/groceries")
    public Grocery updateGrocery(@RequestBody Grocery theGrocery){

        Grocery dbGrocery = groceryService.save(theGrocery);
        return dbGrocery;

    }

    @DeleteMapping("/groceries/{groceryId}")
    public String deleteGrocery(@PathVariable int groceryId){

        Grocery dbGrocery = groceryService.findById(groceryId);

        if(dbGrocery == null){
            return "Grocery not found with Id : "+groceryId;
        }

        groceryService.deleteById(groceryId);
        return "Deleted Grocery Id : " + groceryId;
    }


    @PutMapping("/groceries/buy")
    public List<Grocery> addGroceries(@RequestBody List<Grocery> theGroceriesList) throws InterruptedException {

        return groceryService.buyGroceries(theGroceriesList);
    }
}
