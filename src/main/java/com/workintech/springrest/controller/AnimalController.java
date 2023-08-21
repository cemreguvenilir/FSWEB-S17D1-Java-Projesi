package com.workintech.springrest.controller;

import com.workintech.springrest.entity.Animal;
import com.workintech.springrest.mapping.AnimalResponse;
import com.workintech.springrest.validation.AnimalValidation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    @Value("${instructor.name}")
    private String name;

    private Map<Integer, Animal> animalMap;

    //public AnimalController() {
    //    animal = new HashMap<>();
    //}
    //PostConstruct
    @PostConstruct
    public void init(){
        animalMap = new HashMap<>();
    }

    @GetMapping("/hi")
    public String hi(){
        return name + " says hi!";
    }
    @GetMapping("/")
    public List<Animal> get(){
        return animalMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public AnimalResponse getById(@PathVariable int id){
        if(AnimalValidation.isIdValid(id)){
           return new AnimalResponse(null, "id is not valid", 400);

        }
        if(!AnimalValidation.isMapContainsKey(animalMap, id)){
           return new AnimalResponse(null, "animal is not exist", 400);

        }
        return new AnimalResponse(animalMap.get(id), "Success", 200);
    }
    @PostMapping("/")
    public AnimalResponse save(@RequestBody Animal animal){
        if(AnimalValidation.isMapContainsKey(animalMap, animal.getId())){
            return new AnimalResponse(null, "animal already exist", 400);
        }
        if(!AnimalValidation.isCredentialsValid(animal)){
            return new AnimalResponse(null, "invalid credentials", 400);
        }
       animalMap.put(animal.getId(), animal);
        return new AnimalResponse(animalMap.get(animal.getId()), "Success", 200);
    }
    @PutMapping("/{id}")
    public AnimalResponse update(@PathVariable int id, @RequestBody Animal animal){
        if(!AnimalValidation.isMapContainsKey(animalMap, animal.getId())){
            return new AnimalResponse(null, "animal is not exist", 400);
        }
        if(!AnimalValidation.isMapContainsKey(animalMap, id)){
            return new AnimalResponse(null, "invalid credentials", 400);
        }
        animalMap.put(id, new Animal(id, animal.getName()));
        return new AnimalResponse(animalMap.get(animal.getId()), "Success", 200);

    }
    @DeleteMapping("/{id}")
    public AnimalResponse delete(@PathVariable int id){
        if(!AnimalValidation.isMapContainsKey(animalMap, id)){
            return new AnimalResponse(null, "animal is not exist", 400);
        }
        Animal removedAnimal = animalMap.get(id);
        animalMap.remove(id);
        return new AnimalResponse(removedAnimal, "Success", 200);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("animal controller has been destroyed");
}

}
