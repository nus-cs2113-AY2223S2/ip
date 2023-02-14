import duke.DukeException;
import duke.FileDataHandler;
import duke.Parser;
import duke.TaskList;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static final String FILE_PATH = "data/duke.txt";
    public static void startDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void endDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println(errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startDuke();
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();
        FileDataHandler fileDataHandler = new FileDataHandler(FILE_PATH, "data");
        try {
            if(!fileDataHandler.createFile()) {
                fileDataHandler.loadFile(taskList);
            }
        } catch (IOException exception) {
            printErrorMessage("     ☹ OOPS!!! As an error has occurred, the current task list will not be saved in a file!");
        }
        while (input.hasNextLine()) {
            Parser parseInput = new Parser(taskList, fileDataHandler);
            String[] nextInput = input.nextLine().split(" ", 2);
            try {
                if(parseInput.parse(nextInput)) {
                    break;
                }
            } catch (DukeException exception) {
                printErrorMessage(exception.getMessage());
            } catch (ArrayIndexOutOfBoundsException exception) {
                //for the case of no space after calling a command
                printErrorMessage("     ☹ OOPS!!! The description of a " + nextInput[0] + " cannot be empty.");
            } catch (IOException exception) {
                printErrorMessage("     ☹ OOPS!!! There is an error writing to the file :-(");
            }
        }
        endDuke();
    }

}

