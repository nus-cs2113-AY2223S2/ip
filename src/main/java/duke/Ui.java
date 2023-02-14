package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    static final String divider = "____________________________________________________________";

    public void showLine() {
        System.out.println(divider);
    }
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = divider
                + "\nHello! I'm Duke\n"
                + "What can I do for you?\n"
                + divider;
        System.out.println("Hello from\n" + logo + greeting);
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

    public void printList(ArrayList<Task> taskList) {
        for (int x = 0; x < taskList.size(); x += 1) {
            System.out.print((x + 1) + ". ");
            taskList.get(x).print();
        }
    }
}
