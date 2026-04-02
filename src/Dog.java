import com.petadoption.model.Animal;

//dog subclass inheriting universal animal attributes from animal class
//dog subclass has fields not in parent class because it is not universal
//private fields so weight, isfixed, and breed cannot be changed
//this defines a class named dog which extends animal
//a class is a blueprint for creating objects
public class Dog extends Animal {
    private boolean isFixed;
    private double weight;
    private String breed;

    //these store data for each dog object
    //A subclass does not inherit constructors it must explicitly invoke them.
    //a constructor runs when we create a new object
    //it initializes the object's data
    public Dog(String name, String breed, int age, char gender, int animalID, double weight, boolean isFixed) {
        super(name, age, gender, animalID);
        this.isFixed = isFixed;
        this.weight = weight;
        this.breed = breed;
    }

    public void displayInfo() {
        //private fields are accessed through public methods
//public  method printing shared info using getters and prints dog-specific info directly
        // and prints adoption status

        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("AnimalID: " + getAnimalID());
        System.out.println("Breed: " + breed);
        System.out.println("Gender: " + getGender());
        System.out.println("Adoption status: " + isAdopted());
        System.out.println("Fixed status: " + isFixed);
        System.out.println("Weight: " + weight);
        System.out.println("======================");
    }

    public boolean isFixed() {
        return isFixed;
    }
}




