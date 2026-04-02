import com.petadoption.model.Animal;

/*
cat subclass inheriting universal animal attributes from animal class
 cat subclass has fields not in parent class because they are not universal
 */
public class Cat extends Animal {
   private boolean isFixed;
    private double weight;
    private int animalID;
    // A subclass does not inherit constructors — it must explicitly invoke them.

    public Cat(String name,int age, char gender,int animalID, boolean isFixed,double weight) {
        super(name,age,gender,animalID);
        this.weight = weight;
        this.isFixed = isFixed;

//Constructors initialize the fields of the specific object being created.
    }

    // Instance method (NOT static)
    public void displayInfo() {
        // Calling getters to access and print private variables
        System.out.println("Name: " + getName());
        System.out.println("AnimalID: " + getAnimalID());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Adoption status: " + isAdopted());
        System.out.println("Fixed status: " + isFixed);
        System.out.println("Weight: " + weight);
        System.out.println("=====================");
    }
}