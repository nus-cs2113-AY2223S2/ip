package duke;

import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static final String MARGIN = "*----------------------------*" ;
    public static ArrayList<Task> tasksArray = new ArrayList<>();
    public static int tasksCount = 0;
    // Run program here:
    public static void main(String[] args) {
        try {
            Storage.checkFileAccess();
            List<Task> tasksList = Storage.textFileToProgram();
            tasksArray.addAll(tasksList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        String userInput;
        Scanner in = new Scanner(System.in);
        UI.welcomeMessage();
        do {
            userInput = in.nextLine();
            enterCommand(userInput);
        } while (!userInput.equals("bye"));
    }

    // Executes when error is encountered
    public static void errorReport(String errorDescription){
        System.out.println("Error: " + errorDescription);
    }
    // How the program runs based on user's input commands
    public static void enterCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String keyword = inputArray[0];
        switch (keyword) {
        case "todo":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                userInput = userInput.replaceFirst("todo", "").trim();
                UI.addNewTodo(userInput);
                Todo todoTask = new Todo(userInput, "T");
                tasksArray.add(todoTask);
                UI.printListLength(tasksArray.size());
                tasksCount++;
                updateFile();
            } catch (DukeException e) {
                e.todoError();
            }
            break;

        case "deadline":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                userInput = userInput.replaceFirst("deadline", "").trim();
                int separatorIdx = userInput.indexOf("/");
                String taskName = userInput.substring(0, separatorIdx - 1);
                String by = userInput.substring(separatorIdx + 4);
                UI.addNewDeadline(taskName, by);
                Deadline deadlineTask = new Deadline(taskName, "D", by);
                tasksArray.add(deadlineTask);
                tasksCount++;
                UI.printListLength(tasksArray.size());
                updateFile();
            } catch (DukeException e) {
                e.deadlineError();
            }
            break;

        case "event":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                userInput = userInput.replaceFirst("event", "").trim();
                int separatorIdx = userInput.indexOf("/");
                int lastSeparatorIdx = userInput.lastIndexOf("/");
                String taskName = userInput.substring(0, separatorIdx - 1);
                String startTime = userInput.substring(separatorIdx + 6, lastSeparatorIdx - 1);
                String endTime = userInput.substring(lastSeparatorIdx + 4);
                UI.addNewEvent(taskName, startTime, endTime);
                Event eventTask = new Event(taskName, "E", startTime, endTime);
                tasksArray.add(eventTask);
                tasksCount++;
                UI.printListLength(tasksArray.size());
                updateFile();
            } catch (DukeException e) {
                e.eventError();
            }
            break;

        case "bye":
            UI.endProgram();
            updateFile();
            break;

        case "list":
            accessList();
            break;

        case "mark":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                int taskNum = Integer.parseInt(inputArray[1]);
                markTask(taskNum);

            } catch (IndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a valid task number to mark!");
                System.out.println(MARGIN);

            } catch (DukeException e) {
                e.emptyListError();
            }
            break;

        case "unmark":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                int taskNum = Integer.parseInt(inputArray[1]);
                unmarkTask(taskNum);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a valid task number to unmark!");
                System.out.println(MARGIN);

            } catch (DukeException e) {
              e.emptyListError();
            }
            break;

        case "delete":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                int taskNum = Integer.parseInt(inputArray[1]);
                deleteTask(taskNum);

            } catch (DukeException e){
                System.out.println(MARGIN);
                System.out.println("There is nothing to delete as list is empty! O_O");
                System.out.println(MARGIN);

            } catch (IndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a valid task number to delete!");
                System.out.println(MARGIN);

            }
            break;

        default:
            System.out.println(MARGIN);
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(MARGIN);
            break;
        }
    }
    // For 'list' command
    public static void accessList(){

        if (tasksArray.size() == 0) {
            System.out.println("Your current list is empty. Why not add some tasks in?");

        }else {
            System.out.println(MARGIN);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksArray.size(); i++) {
                System.out.println((i + 1) + ". " + tasksArray.get(i).toString());
            }
            System.out.println(MARGIN);
        }
    }
    // For 'mark' command
    public static void markTask(int taskIdx){
        tasksArray.get(taskIdx-1).markAsDone();
        System.out.println(MARGIN);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" +  tasksArray.get(taskIdx-1).getStatusIcon() + "] " +
                tasksArray.get(taskIdx-1).description);
        System.out.println(MARGIN);
    }
    // For 'unmark' command
    public static void unmarkTask(int taskIdx){
        tasksArray.get(taskIdx-1).markAsUndone();
        System.out.println(MARGIN);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [" +  tasksArray.get(taskIdx-1).getStatusIcon()  + "] " +
                tasksArray.get(taskIdx-1).description);
        System.out.println(MARGIN);
    }
    // For 'delete' command
    public static void deleteTask(int taskIdx){
        System.out.println(MARGIN);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasksArray.get(taskIdx-1).toString());
        System.out.println(MARGIN);
        tasksArray.remove(taskIdx-1);
        tasksCount--;
        UI.printListLength(tasksArray.size());

    }

    // Save changes from program to txt file
    private static void updateFile() {
        try {
            Storage.writeToFile("");
            for (Task task : tasksArray) {
                Storage.appendTextToFile(task.saveText() + System.lineSeparator());
            }
        } catch (IOException e) {
            errorReport(e.getMessage());

        }
    }

}

