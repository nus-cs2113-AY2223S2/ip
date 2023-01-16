import java.io.*;
import java.util.Scanner;
public class Greet {
    public void GreetUser()
    {
        System.out.println("__________________________________________");
        System.out.println("Hey person! My name is Duke, that is D U K E Duke");
        System.out.println("How may I be of your assistance today my friend?");
        System.out.println("__________________________________________");
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your name");
        String userName = myObj.nextLine();
        System.out.println("Hello " + userName + "! Aside from greeting I am not able to do much yet, so please check back soon!");
        System.out.println(("Bye bye!"));
    }
}
