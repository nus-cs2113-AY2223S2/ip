import duke.DukeException;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
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

    public Duke(String filePath, String directoryName) {
        taskList = new TaskList();
        fileDataHandler = new FileDataHandler(filePath, directoryName);
        try {
            if(!fileDataHandler.createFile()) {
                fileDataHandler.loadFile(taskList);
            }
        } catch (IOException exception) {
            printErrorMessage("     ☹ OOPS!!! As an error has occurred, the current task list will not be saved in a file!");
        }
    }

    public void run() {
        startDuke();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            Parser parseInput = new Parser(this);
            String[] nextInput = input.nextLine().split(" ", 2);
            boolean isExit;
            try {
                isExit = parseInput.parse(nextInput);
                if(isExit) {
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
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("data/duke.txt", "data").run();
    }

}

