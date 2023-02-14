package duke;

import duke.exception.InvalidCommandException;
import duke.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    static final String TASK_REMOVED_PREFIX = "Noted! I've removed this task" + System.lineSeparator() + "\t";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDuke();
        String input;
        String outputMessage;
        while (true) {
            input = scanner.nextLine();
            Command command = new Command(input);
            String[] payloadData = command.getPayload().getData();
            try {
                switch (command.getType()) {
                    case "list":
                        outputMessage = getTasksList();
                        break;
                    case "mark":
                        outputMessage = handleMarkTask(command);
                        break;
                    case "unmark":
                        outputMessage = handleUnmarkTask(command);
                        break;
                    case "task":
                        outputMessage = handleAddTask(payloadData);
                        break;
                    case "todo":
                        outputMessage = handleAddTodo(payloadData);
                        break;
                    case "deadline":
                        outputMessage = handleAddDeadline(payloadData);
                        break;
                    case "event":
                        outputMessage = handleAddEvent(payloadData);
                        break;
                    case "delete":
                        outputMessage = handleDeleteTask(payloadData);
                        break;
                    case "bye":
                        outputMessage = "Bye. Hope to see you again soon!";
                        System.exit(0);
                    default:
                        throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                outputMessage = e.getMessage();
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
        }

    }

    public static String handleDeleteTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        int removedIndex = Integer.parseInt(payloadData[0]);
        Task removedTask = tasks.get(removedIndex - 1);
        tasks.remove(removedIndex - 1);
        outputMessage = TASK_REMOVED_PREFIX + removedTask.toString() + System.lineSeparator() + "\t" + getTasksInformation();
        return outputMessage;
    }

    public static String handleAddEvent(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Task newEvent = new Event(payloadData);
        tasks.add(newEvent);
        outputMessage = TASK_ADDED_PREFIX + newEvent.toString() + System.lineSeparator() + "\t"
                + getTasksInformation();
        return outputMessage;
    }

    public static String handleAddDeadline(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Deadline newDeadline = new Deadline(payloadData);
        tasks.add(newDeadline);
        outputMessage = TASK_ADDED_PREFIX + newDeadline.toString() + System.lineSeparator() + "\t"
                + getTasksInformation();
        return outputMessage;
    }

    public static String handleAddTodo(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Task newTodo = new ToDo(payloadData);
        tasks.add(newTodo);
        outputMessage = TASK_ADDED_PREFIX + newTodo.toString() + System.lineSeparator() + "\t"
                + getTasksInformation();
        return outputMessage;
    }

    public static String handleAddTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Task newTask = new Task(payloadData);
        tasks.add(newTask);
        outputMessage = TASK_ADDED_PREFIX + newTask.toString() + System.lineSeparator() + "\t"
                + getTasksInformation();
        return outputMessage;
    }

    public static String handleMarkTask(Command command) {
        int taskIndex;
        String outputMessage;
        taskIndex = Integer.parseInt(command.getPayload().getData()[0]) - 1;
        tasks.get(taskIndex).markAsDone();
        outputMessage = "Nice! I've marked this task as done:" + System.lineSeparator() + "\t"
                + tasks.get(taskIndex).toString();
        return outputMessage;
    }

    public static String handleUnmarkTask(Command command) {
        String outputMessage;
        int taskIndex;
        taskIndex = Integer.parseInt(command.getPayload().getData()[0]) - 1;
        tasks.get(taskIndex).unmarkAsDone();
        outputMessage = "Ok, I've marked this task as not done:" + System.lineSeparator() + "\t"
                + tasks.get(taskIndex).toString();
        return outputMessage;
    }

    public static void printDuke() {
        String logo = " ____        _" + System.lineSeparator()
                + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "| | | | | | | |/ / _ \\" + System.lineSeparator()
                + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
        System.out.println("____________________________________________________________" + System.lineSeparator()
                + logo
                + "Hello! I'm duke.Duke!" + System.lineSeparator()
                + "What I can do for you?" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator()
        );
    }

    public static String getTasksInformation() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    public static String getTasksList() {
        if (tasks.size() == 0) {
            return "Tasks is empty...";
        }
        String tasksList = "";
        int numberOfTasks = tasks.size();
        for (int i = 0; i < numberOfTasks; i++) {
            tasksList += String.format("%3d. ", (i + 1)) + tasks.get(i).toString();
            if (i < numberOfTasks - 1) {
                tasksList += System.lineSeparator() + "\t";
            }
        }
        return tasksList;
    }

}
