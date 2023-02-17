package duke.command;

import duke.Duke;
import duke.exception.BlankDescException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainFunctions {
    public static final String LOGO =
            "\t __   __\n"
                    + "\t|  | |  |   ____     _  _   _    _\n"
                    + "\t|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                    + "\t|   _   | | |_|  \\  |  /   | \\_/  |\n"
                    + "\t|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    public static final String FILEPATH = "data/tasklist.txt";

    public static void printWelcome() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(SPACER + "Hello! I'm Haru");
        System.out.println(SPACER + "What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void printGoodbye() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void readData(Task[] storedUserTasks) throws FileNotFoundException {
        String originalString, description, by, from, to;
        int indexOfBy, indexOfFrom, indexOfTo;
        boolean isDone;

        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            originalString = s.nextLine();
            isDone = originalString.charAt(4) == 'X';
            switch (originalString.charAt(1)) {
            case 'T':
                description = originalString.substring(7);
                storedUserTasks[Duke.userTextCount] = new Todo(description);
                break;
            case 'D':
                originalString = originalString.substring(7).replace("(by:", "/by");
                originalString = originalString.substring(0, originalString.length() - 1);
                indexOfBy = originalString.indexOf("/by");
                description = originalString.substring( 0, indexOfBy-1);
                by = originalString.substring(indexOfBy+4);
                storedUserTasks[Duke.userTextCount] = new Deadline(description,by);
                break;
            case 'E':
                originalString = originalString.substring(7).replace("(from:", "/from");
                originalString = originalString.replace("to:", "/to");
                originalString = originalString.substring(0, originalString.length() - 1);
                indexOfFrom = originalString.indexOf("/from");
                indexOfTo = originalString.indexOf("/to");
                description = originalString.substring(0,indexOfFrom-1);
                from = originalString.substring(indexOfFrom+6,indexOfTo-1);
                to = originalString.substring(indexOfTo+4);
                storedUserTasks[Duke.userTextCount] = new Event(description,from,to);
                break;
            }
            storedUserTasks[Duke.userTextCount].isDone = isDone;
            Duke.userTextCount++;
        }
    }

    public static void writeData(Task[] storedUserTasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for(int i=0; i<Duke.userTextCount; i++) {
            fw.write(storedUserTasks[i].toString()+'\n');
        }
        fw.close();
    }

    public static void invalidInput() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(DIVIDER);
    }

    public static void listTasks(int userTextCount, Task[] storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Here are the tasks in your lists:");
        for(int i=0;i<userTextCount;i++){
            System.out.println(SPACER+(i+1)+"."+storedUserTasks[i].toString());
        }
        System.out.println(DIVIDER);
    }

    public static void markTask(String userCommand, Task[] storedUserTasks) {
        int taskIndex = Integer.parseInt(userCommand.split(" ")[1])-1;
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Nice! I've marked this task as done:");
        storedUserTasks[taskIndex].isDone = true;
        System.out.println(SPACER+storedUserTasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static void unmarkTask(String userCommand, Task[] storedUserTasks) {
        int taskIndex = Integer.parseInt(userCommand.split(" ")[1])-1;
        System.out.println(DIVIDER);
        System.out.println(SPACER+"OK, I've marked this task as not done yet:");
        storedUserTasks[taskIndex].isDone = false;
        System.out.println(SPACER+storedUserTasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static void createTodo(String userCommand, Task[] storedUserTasks) throws DukeException {
        if (userCommand.length()<=4) {
            throw new BlankDescException();
        }
        String description = userCommand.substring(5);
        storedUserTasks[Duke.userTextCount] = new Todo(description);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void createDeadline(String userCommand, Task[] storedUserTasks) throws DukeException {
        if (userCommand.length()<=8) {
            throw new BlankDescException();
        }
        int indexOfBy = userCommand.indexOf("/by");
        String description = userCommand.substring( 9, indexOfBy-1);
        String by = userCommand.substring(indexOfBy+4);
        storedUserTasks[Duke.userTextCount] = new Deadline(description,by);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void createEvent(String userCommand, Task[] storedUserTasks) throws DukeException {
        if (userCommand.length()<=5) {
            throw new BlankDescException();
        }
        int indexOfFrom = userCommand.indexOf("/from");
        int indexOfTo = userCommand.indexOf("/to");
        String description = userCommand.substring(6,indexOfFrom-1);
        String from = userCommand.substring(indexOfFrom+6,indexOfTo-1);
        String to = userCommand.substring(indexOfTo+4);
        storedUserTasks[Duke.userTextCount] = new Event(description,from,to);
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+storedUserTasks[Duke.userTextCount].toString());
        Duke.userTextCount++;
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

}
