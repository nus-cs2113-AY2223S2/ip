package Duke;
import Duke.*;

import java.util.ArrayList;

public class Ui {


    static void printHorizontalBar() {
        String horizontalBar = "---------------------------------------------------\n";
        System.out.println(horizontalBar);
    }

    static void greetUser() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;


        System.out.println(logo);
        printHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        printHorizontalBar();
    }

    static void sayBye(){
        System.out.println("Bye! Hope to see you again soon!\n");
        printHorizontalBar();
    }

    static void showNumberOfTasks(ArrayList<Task> tasks){
        System.out.println("There are currently " + tasks.size() + " task(s) in the list");
    }

    static void invalidInputReponse(){
        System.out.println("Sorry I didn't get that!");
    }

    public void showLoadingError() {
        System.out.println("ERROR loading file!");
    }
}
