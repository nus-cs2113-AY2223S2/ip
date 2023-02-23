package siri.general;

import java.util.Scanner;

//Ui deals with interactions with the user
public class Ui {
    protected static String logo = "  ______     __     __  _____     __\n"
            + " | _____|   |__|   |  |/ ____|   |__|\n"
            + " | |____     __    |   /          __\n"
            + " |_____ |   |  |   |  |          |  |\n"
            + " _____| |   |  |   |  |          |  |\n"
            + " |______|   |__|   |__|          |__|\n";

    protected static String straightLine = "=====================================================================================";

    protected static String siriChatBox = "Siri:\n";

    protected static String userChatBox = "User:\n";
    public Ui(){
    }

    public void drawUserChatBox (){
        System.out.println(userChatBox);
    }

    public void drawSiriChatBox(){
        System.out.println(siriChatBox);
    }

    public void drawStraightLine(){
        System.out.println(straightLine);
    }
    public void greet(){
        System.out.println("Hello from \n" + logo);
        System.out.println(straightLine);
        System.out.println(siriChatBox);
        System.out.println("Hey, I'm Siri\n" + "What can I do for you?");
        System.out.println(straightLine);
    }

    public void sayGoodbye() {
        System.out.println(">o< Goodbye! Hope to see you again soon! >o<");
    }

    public String readCommand(){
        System.out.println(userChatBox);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();
        System.out.println(straightLine);

        return userInput;
    }

    public void showLoadingError(){
        System.out.println("The reason you get this error could be: ");
        System.out.println("    1) Unable to write to files.");
        System.out.println("    2) Unable to create files.");
        System.out.println("    3) Unable to update new task list.");
    }
}
