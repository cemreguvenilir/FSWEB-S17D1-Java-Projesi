package com.workintech.springrest.mapping;

import com.workintech.springrest.entity.Animal;

public class AnimalResponse {
    private Animal animal;
    private String message;
    private int status;

    public AnimalResponse(Animal animal, String message, int status) {
        this.animal = animal;
        this.message = message;
        this.status = status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
