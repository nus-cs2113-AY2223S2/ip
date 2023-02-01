import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:" + System.lineSeparator() + "\t";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDuke();
        String input;
        String command;
        String outputMessage;
        while (true) {
            input = scanner.nextLine();
            command = input.split(" ")[0];
            int taskIndex;
            switch (command) {
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
                    String taskDescription = input.split("/")[0].split("task")[1].trim();
                    Task newTask = new Task(taskDescription);
                    tasks[Task.numberOfTasks - 1] = newTask;
                    outputMessage = TASK_ADDED_PREFIX + newTask.toString() + System.lineSeparator() + "\t"
                            + getTaskAddedPostfix();
                    break;
                case "todo":
                    String toDoDescription = input.split("todo")[1].trim();
                    Task newTodo = new ToDo(toDoDescription);
                    tasks[Task.numberOfTasks - 1] = newTodo;
                    outputMessage = TASK_ADDED_PREFIX + newTodo.toString() + System.lineSeparator() + "\t"
                            + getTaskAddedPostfix();
                    break;
                case "deadline":
                    String deadlineDescription = input.split("/")[0].split("deadline")[1].trim();
                    String deadlineBy = input.split("/")[1].trim();
                    Deadline newDeadline = new Deadline(deadlineDescription, deadlineBy);
                    tasks[Task.numberOfTasks - 1] = newDeadline;
                    outputMessage = TASK_ADDED_PREFIX + newDeadline.toString() + System.lineSeparator() + "\t"
                            + getTaskAddedPostfix();
                    break;
                case "event":
                    String eventDescription = input.split("/")[0].split("event")[1].trim();
                    String eventStart = input.split("/")[1].trim();
                    String eventEnd = input.split("/")[2].trim();
                    Task newEvent = new Event(eventDescription, eventStart, eventEnd);
                    tasks[Task.numberOfTasks - 1] = newEvent;
                    outputMessage = TASK_ADDED_PREFIX + newEvent.toString() + System.lineSeparator() + "\t"
                            + getTaskAddedPostfix();
                    break;
                case "bye":
                    outputMessage = "Bye. Hope to see you again soon!";
                    System.exit(0);
                default:
                    outputMessage = "Sorry, I don't understand what you said. Can you say it again";
                    break;
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
                + "Hello! I'm Duke!" + System.lineSeparator()
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
