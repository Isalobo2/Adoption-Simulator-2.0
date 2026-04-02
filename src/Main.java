import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
//this is the main class that runs the program
public class Main {
    public static void main(String[] args) {
        int AdopterAgeRequirement = 18;
        boolean running = true;
        HashMap <Integer, Dog> Dogs = new HashMap<>();
        HashMap <Integer, Cat> Cats =  new HashMap<>();

        // Created an empty list that will store dog objects which can increase or decrease
        //ArrayList<Dog> dogs = new ArrayList<>();
        Dogs.put(0, new Dog("Peanut", "Chihuahuah", 2, 'M', 765, 6.2,false));
        Dogs.put(1, new Dog("Max", "Pitbull", 4, 'M', 932, 45.62,false));
        Dogs.put(2, new Dog("Daisy", "Labrador Retriever", 1, 'F', 556, 55.31,true));
        Dogs.put(3, new Dog("Sadie", "Beagle", 1, 'F', 210, 22.43,true));

        // Created an empty list that will store dog objects which can increase or decrease
        //ArrayList<Cat> cats = new ArrayList<>();
        Cats.put(0, new Cat("Tigger", 1, 'M', 234, false,10.13));
        Cats.put(1, new Cat("Milo", 3, 'M', 983, true,8.9));
        Cats.put(2, new Cat("Oreo", 1, 'F', 374, true,9.23));
        Cats.put(3, new Cat("Nina", 2, 'F', 249, true,10.24));

        // Scanner for user input
        Scanner input = new Scanner(System.in);
            System.out.println("\n===== Isabella's adoption center ======");
            System.out.println("1.Adopt a dog");
            System.out.println("2.Adopt a cat");
            System.out.println("3. Exit");
            System.out.println("Enter a number: ");
            int choice = input.nextInt();
            input.nextLine();

            try {
                if (choice == 1) {

                    System.out.print("Enter your full name: ");
                    String fullName = input.nextLine();
                    System.out.print("Enter your age: ");
                    int age = input.nextInt();

                    for (Dog dog : Dogs.values()) {
                        dog.displayInfo();
                    }System.out.println("Choose the name of the dog you would like to adopt: ");
                    String dogchoice = input.next();



                        if (dogchoice.equals("Peanut") || dogchoice.equals("peanut")) {
                            System.out.print("You have adopted Peanut!");
                        }
                        if (dogchoice.equals ("Max") || dogchoice.equals ("max")) {
                            System.out.print("You have adopted Max!");
                        }
                        if (dogchoice.equals ("Daisy") || dogchoice.equals ("daisy")) {
                            System.out.print("You have adopted Daisy!");
                        }
                        if (dogchoice.equals ("Sadie") || dogchoice.equals ("sadie")) {
                            System.out.print("You have adopted Sadie!");
                        } /*else {
                            System.out.print("Input invalid. Please try again.");
                            System.out.println(dogchoice);
                        } */
                    }

                if (choice == 2) {
                    System.out.print("Enter your full name: ");
                    String fullName2 = input.nextLine();
                    System.out.print("Enter your age: ");
                    int age2 = input.nextInt();
                    for (Cat cat : Cats.values()) {
                        cat.displayInfo();
                    }
                        System.out.println("Choose the name of the cat you would like to adopt: ");
                        String catchoice = input.next();

                        if (catchoice.equals ("Tigger") || catchoice.equals("tigger")) {
                            System.out.print("You have adopted Tigger!");
                        }
                        if (catchoice.equals ("Milo") || catchoice.equals ("milo")) {
                            System.out.print("You have adopted Milo!");
                        }
                        if (catchoice.equals ("Oreo") || catchoice.equals ("oreo")) {
                            System.out.print("You have adopted Oreo!");
                        }
                        if (catchoice.equals ("Nina") || catchoice.equals ("nina")) {
                            System.out.print("You have adopted Nina!");
                        }
                    }
                else if (choice == 3) {
                    running = false;
                    System.out.println("Exiting program....");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");

        }
        //input.close();
    }


}








