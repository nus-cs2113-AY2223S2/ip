package duke;

import duke.exception.InvalidCommandException;
import duke.model.*;

import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:" + System.lineSeparator() + "\t";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDuke();
        String input;
        String outputMessage;
        while (true) {
            input = scanner.nextLine();
            Command command = new Command(input);
            String[] payloadData = command.getPayload().getData();
            int taskIndex;
            try {
                switch (command.getType()) {
                    case "list":
                        outputMessage = Task.getTasksList(tasks);
                        break;
                    case "mark":
                        taskIndex = getTaskIndexFromInput(input);
                        tasks[taskIndex].markAsDone();
                        outputMessage = "Nice! I've marked this task as done:" + System.lineSeparator() + "\t"
                                + tasks[taskIndex].toString();
                        break;
                    case "unmark":
                        taskIndex = getTaskIndexFromInput(input);
                        tasks[taskIndex].unmarkAsDone();
                        outputMessage = "Ok, I've marked this task as not done:" + System.lineSeparator() + "\t"
                                + tasks[taskIndex].toString();
                        break;
                    case "task":
                        Task newTask = new Task(payloadData);
                        tasks[Task.numberOfTasks - 1] = newTask;
                        outputMessage = TASK_ADDED_PREFIX + newTask.toString() + System.lineSeparator() + "\t"
                                + getTaskAddedPostfix();
                        break;
                    case "todo":
                        Task newTodo = new ToDo(payloadData);
                        tasks[Task.numberOfTasks - 1] = newTodo;
                        outputMessage = TASK_ADDED_PREFIX + newTodo.toString() + System.lineSeparator() + "\t"
                                + getTaskAddedPostfix();
                        break;
                    case "deadline":
                        Deadline newDeadline = new Deadline(payloadData);
                        tasks[Task.numberOfTasks - 1] = newDeadline;
                        outputMessage = TASK_ADDED_PREFIX + newDeadline.toString() + System.lineSeparator() + "\t"
                                + getTaskAddedPostfix();
                        break;
                    case "event":
                        Task newEvent = new Event(payloadData);
                        tasks[Task.numberOfTasks - 1] = newEvent;
                        outputMessage = TASK_ADDED_PREFIX + newEvent.toString() + System.lineSeparator() + "\t"
                                + getTaskAddedPostfix();
                        break;
                    case "bye":
                        outputMessage = "Bye. Hope to see you again soon!";
                        System.exit(0);
                    default:
                        throw new InvalidCommandException("Command is unidentified... Sorry :(");
                }
            } catch (InvalidCommandException e) {
                outputMessage = e.getMessage();
            }



            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
        }

    }

    private static void printDuke() {
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

    public static String getTaskAddedPostfix() {
        return "Now you have " + Task.numberOfTasks + " tasks in the list.";
    }

    public static int getTaskIndexFromInput(String inputText) {
        return Integer.parseInt(inputText.split(" ")[1]) - 1;
    }

}
