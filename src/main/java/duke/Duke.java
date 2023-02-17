package duke;

import duke.command.MainFunctions;
import duke.exception.BlankDescException;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    public static int userTextCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MainFunctions.printWelcome();

        String userCommand = scanner.nextLine();

        Task[] storedUserTasks = new Task[100];

        try {
            MainFunctions.readData(storedUserTasks);
        } catch (FileNotFoundException e) {
           File f = new File("data/tasklist.txt");
        }

        boolean isExit = false;

        while(!isExit){
            switch (userCommand.split(" ")[0]) {
            case "list":
                MainFunctions.listTasks(userTextCount, storedUserTasks);
                break;
            case "bye":
                isExit = true;
                break;
            case "mark":
                MainFunctions.markTask(userCommand, storedUserTasks);
                break;
            case "unmark":
                MainFunctions.unmarkTask(userCommand, storedUserTasks);
                break;
            case "todo":
                try {
                    MainFunctions.createTodo(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("todo");
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            case "deadline":
                try {
                    MainFunctions.createDeadline(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("deadline");
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            case "event":
                try {
                    MainFunctions.createEvent(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("event");
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            default:
                MainFunctions.invalidInput();
                break;
            }
            if(!isExit) {
                userCommand = scanner.nextLine();
            }
        }

        MainFunctions.printGoodbye();

    }
}
