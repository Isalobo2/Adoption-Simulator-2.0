package com.petadoption.model;

public class Animal {
    private final int id;
    private final String name;
    private final int ageYears;
    private final char gender;

    private final String species;
    private final String breed;
    private final double weightLbs;
    private final String description;

    private boolean isAdopted = false;

//private attributes that can only be accessed from same animal class


    //constructor initializing universal animal attributes making object usable
    public Animal(String name, int age, char gender, int animalID) {
        this.name = name;
        this.ageYears = age;
        this.gender = gender;
        this.id = animalID;
        this.species = "unknown";
        this.breed = "Unknown";
        this.weightLbs = 0.0;
        this.description = null;
//"this refers to the current object being created
    }

    public Animal(
            int id,
            String name,
            String species,
            String breed,
            int ageYears,
            double weightLbs,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.ageYears = ageYears;
        this.weightLbs = weightLbs;
        this.description = description;
        this.gender = '?';
    }

    /*Using getters because private variables can only be accessed within same class.
    Getters and setters allow them to be accessed outside of the class
     */
    //these allow controlled access to private variables
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAnimalID() {
        return id;
    }

    public int getAge() {
        return ageYears;
    }


    public char getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public double getWeightLbs() {
        return weightLbs;
    }

    public String getDescription() {
        return description;
    }

    public String getAgeDisplay() {
        if (ageYears == 1) return "1 yr";
        return ageYears + " yrs";
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void adopt() {
        isAdopted = true;
    }
}



