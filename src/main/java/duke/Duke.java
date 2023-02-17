package duke;

import duke.command.MainFunctions;
import duke.exception.BlankDescException;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static int userTextCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MainFunctions.printWelcome();

        String userCommand = scanner.nextLine();


        ArrayList<Task> storedUserTasks = new ArrayList<>();
        // Task[] storedUserTasks = new Task[100];
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
            case "delete":
                MainFunctions.deleteTask(userCommand, storedUserTasks);
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

        try {
            MainFunctions.writeData(storedUserTasks);
        } catch (IOException e) {
            System.out.println("Failed to save data!");
        }

        MainFunctions.printGoodbye();

    }
}
