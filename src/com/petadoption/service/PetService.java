package com.petadoption.service;

import com.petadoption.model.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PetService {
    private final List<Animal> pets = new ArrayList<>();

    public PetService() {
        // Seed data (in-memory) so the JavaFX UI runs without a database.
        pets.add(new Animal(101, "Peanut", "dog", "Chihuahua", 2, 6.2,
                "Tiny but fearless. Loves sunny windows and squeaky toys."));
        pets.add(new Animal(102, "Max", "dog", "Pit Bull", 4, 45.6,
                "Big heart, bigger cuddle potential. Knows sit and shake."));
        pets.add(new Animal(103, "Daisy", "dog", "Labrador Retriever", 1, 55.3,
                "Goofy, friendly, and always ready for a walk."));
        pets.add(new Animal(104, "Sadie", "dog", "Beagle", 1, 22.4,
                "Curious nose on legs. Would thrive with a fenced yard."));

        pets.add(new Animal(201, "Tigger", "cat", "Domestic Shorthair", 1, 10.1,
                "Playful and energetic. Great with people."));
        pets.add(new Animal(202, "Milo", "cat", "Tabby", 3, 8.9,
                "Sweet lap-cat who enjoys quiet afternoons."));
        pets.add(new Animal(203, "Oreo", "cat", "Tuxedo", 1, 9.2,
                "Talkative and curious. Loves chasing laser pointers."));
        pets.add(new Animal(204, "Nina", "cat", "Calico", 2, 10.2,
                "Gentle and observant. Warms up quickly with treats."));
    }

    public List<Animal> getAvailableAll() {
        return pets.stream()
                .filter(p -> !p.isAdopted())
                .sorted(Comparator.comparing(Animal::getSpecies).thenComparing(Animal::getName))
                .collect(Collectors.toList());
    }

    public List<Animal> getAvailableDogs() {
        return pets.stream()
                .filter(p -> !p.isAdopted())
                .filter(p -> "dog".equalsIgnoreCase(p.getSpecies()))
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());
    }

    public List<Animal> getAvailableCats() {
        return pets.stream()
                .filter(p -> !p.isAdopted())
                .filter(p -> "cat".equalsIgnoreCase(p.getSpecies()))
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());
    }

    public void adopt(int id, String adopterName) {
        if (adopterName == null || adopterName.isBlank()) {
            throw new IllegalArgumentException("Adopter name is required.");
        }

        Animal pet = pets.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pet not found: " + id));

        if (pet.isAdopted()) {
            throw new IllegalStateException(pet.getName() + " is already adopted.");
        }

        pet.adopt();
    }
}
