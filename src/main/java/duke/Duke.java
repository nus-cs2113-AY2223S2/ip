package duke;

import duke.storage.Storage;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

<<<<<<< HEAD
import java.io.File;
=======
>>>>>>> branch-Level-7
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.UI;

import static javax.swing.text.html.CSS.Attribute.MARGIN;


<<<<<<< HEAD
public class Duke {
    // Definition lists and methods here:

    public static ArrayList<Task> tasksArray = new ArrayList<>(100);
    public static int taskCount = 0;

    // Execute error if missing file
    public static void errorReport(String errorDescription) {
        System.out.println(MARGIN);
        System.out.println("ERROR: " + errorDescription);
        System.out.println(MARGIN);
    }

    // Run program here:
    public static void main(String[] args) {
        try {
            Storage.checkFileAccess();
            List<Task> listOfTasks = Storage.textFileToProgram();
            tasksArray.addAll(listOfTasks);
        } catch (FileNotFoundException e) {
            errorReport("File not found.");
        } catch (IOException e) {
            errorReport("Something went wrong: " + e.getMessage());
        }

        String userInput;
        Scanner in = new Scanner(System.in);
        UI.welcomeMessage();
        do {
            userInput = in.nextLine();
            enterCommand(userInput);
        } while (!userInput.equals("bye"));
    }

    // How the program runs based on user's input commands
    public static void enterCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String KEYWORD = inputArray[0];
        switch (KEYWORD) {
        case "bye":
            UI.endProgram();
            updateFile();
            break;

        case "list":
            accessList();
            break;

        case "mark":
            try {
                taskIdx = Integer.parseInt(inputArray[1]);
                markTask(taskIdx);
                System.out.println(MARGIN);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasksArray.get(taskIdx - 1).toString());
                System.out.println(MARGIN);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a number to mark!");
                System.out.println(MARGIN);

            } catch (NullPointerException e) {
                System.out.println(MARGIN);
                System.out.println("This task does not exist!");
                System.out.println(MARGIN);
            }


        case "unmark":
            try {
                taskIdx = Integer.parseInt(inputArray[1]);
                unmarkTask(taskIdx);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a number to unmark!");
                System.out.println(MARGIN);

            } catch (NullPointerException e) {
                System.out.println(MARGIN);
                System.out.println("This task does not exist!");
                System.out.println(MARGIN);
            }
            break;

        case "todo":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                tasksArray.add(taskCount, new Todo(details));
                taskAdded(taskCount);
                taskCount++;
            } catch (DukeException e) {
                e.todoError();
            }
            break;


        case "deadline":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                int separatorIdx = details.indexOf(BY);
                // Get task from user's input
                String deadlineTask = details.substring(0, separatorIdx - 1);
                // Get due date from user's input
                String dueBy = details.substring(separatorIdx);
                tasksArray.add(taskCount, new Deadline(deadlineTask, dueBy));
                taskAdded(taskCount);
                taskCount++;
            } catch (DukeException e) {
                e.deadlineError();
            }
            break;

        case "event":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException();
                }
                int fromIdx = details.indexOf(FROM);
                String eveTask = details.substring(0, fromIdx - 1);
                String eventPeriod = details.substring(fromIdx);
                //Further split the event duration to get start and end times
                int toIdx = details.indexOf(TO);
                // Get event start time
                String startTime = eventPeriod.substring(fromIdx, toIdx - 1);
                // Get event end time
                String endTime = eventPeriod.substring(toIdx);
                tasksArray.add(taskCount, new Event(eveTask, startTime, endTime));
                taskAdded(taskCount);
                taskCount++;
            } catch (DukeException e) {
                e.eventError();
            }
            break;
        case "delete":
            taskCount = Integer.parseInt(inputArray[1]) - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasksArray.get(taskNum).toString());
            tasksList.remove(taskNum);
            UI.printTaskList(tasksList.size());
            updateFile();
            break;

        default:
            System.out.println(MARGIN);
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(MARGIN);
            break;
        }
    }
    // For 'list' command
    public static void accessList() {
        if (tasksArray.isEmpty()) {
            System.out.println("Your current list is empty. Why not add some tasks in?");
        }
        else {
            System.out.println(MARGIN);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasksArray.get(i).toString());
            }
            System.out.println(MARGIN);
        }
    }

    // Marking task as done
    public static void markTask(int taskIdx) {
        tasksArray.get(taskIdx - 1).setDone(true);
    }

    // Unmarking task
    public static void unmarkTask(int taskIdx) {
        tasksArray.get(taskIdx - 1).setDone(false);
        System.out.println(MARGIN);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasksArray.get(taskIdx - 1).toString());
        System.out.println(MARGIN);
    }

    // Update task addition sections[in-program]
    public static void taskAdded(int taskCount) {
        System.out.println(MARGIN);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasksArray.get(taskCount).toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(MARGIN);
    }
    private static void updateFile() {
        try {
            Storage.writeToFile("");
            for (Task task : tasksArray) {
                Storage.appendTextToFile(task.saveText() + System.lineSeparator());
=======

public class Duke {
    private static final String MARGIN = "*----------------------------*" ;
    public static ArrayList<Task> tasksArray = new ArrayList<>();
    public static int tasksCount = 0;
    // Run program here:
    public static void main(String[] args) {
        try {
            Storage.checkFile();
            List<Task> tasksList = Storage.textFileToArray();
            tasksArray.addAll(tasksList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
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
                String start = userInput.substring(separatorIdx + 6, lastSeparatorIdx - 1);
                String end = userInput.substring(lastSeparatorIdx + 4);
                UI.addNewEvent(taskName, start, end);
                Event eventTask = new Event(taskName, "E", start, end);
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
                int taskNum = Integer.parseInt(inputArray[1]);
                markTask(taskNum);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a valid task number to mark!");
                System.out.println(MARGIN);

            } catch (NullPointerException e) {
                System.out.println(MARGIN);
                System.out.println("This task does not exist!");
                System.out.println(MARGIN);
            }
            break;

        case "unmark":
            try {
                int taskNum = Integer.parseInt(inputArray[1]);
                unmarkTask(taskNum);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MARGIN);
                System.out.println("Please key in a valid task number to unmark!");
                System.out.println(MARGIN);

            } catch (NullPointerException e) {
                System.out.println(MARGIN);
                System.out.println("This task does not exist!");
                System.out.println(MARGIN);
            }
            break;

        default:
            System.out.println(MARGIN);
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(MARGIN);
            break;
        }
    }
    // For 'list' command
    public static void accessList(){
        if (tasksCount == 0) {
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

    // Update task addition into list
    public static void taskAdded(int taskCount){
        System.out.println(MARGIN);
        System.out.println("Got it. I've added this task:");
        System.out.println("  "+ tasksArray.get(taskCount).toString());
        taskCount++ ;
        System.out.println("Now you have "+ taskCount + " tasks in the list.");
        System.out.println(MARGIN);
    }
    // Save changes from program to txt file
    private static void updateFile() {
            try {
                Storage.writeToFile(" ");
                for (Task task : tasksArray) {
                    Storage.appendTextToFile(task.saveText() + System.lineSeparator());
                }
            } catch (IOException e) {
                errorReport(e.getMessage());
>>>>>>> branch-Level-7
            }
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }
    }

<<<<<<< HEAD
}


=======
>>>>>>> branch-Level-7


