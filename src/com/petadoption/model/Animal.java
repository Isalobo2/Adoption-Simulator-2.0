package com.petadoption.model;

public class Animal {
    private String name;
    private int age;
    private int animalID;
    private char gender;
    private boolean isAdopted = false;

//private attributes that can only be accessed from same animal class


    //constructor initializing universal animal attributes making object usable
    public Animal( String name, int age,  char gender, int animalID ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.animalID = animalID;
//"this refers to the current object being created
    }

    /*Using getters because private variables can only be accessed within same class.
    Getters and setters allow them to be accessed outside of the class
     */
    //these allow controlled access to private variables
    public String getName() {
        return name;
    }


    public int getAnimalID() {
        return animalID;
    }

    public int getAge() {
        return age;
    }


    public char getGender() {
        return gender;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void adopt() {
        isAdopted = true;
    }
}



