import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:\n\t";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                            + logo
                            + "Hello! I'm Duke!\n"
                            + "What I can do for you?\n"
                            + "____________________________________________________________\n"
                            );
        String inputText;
        String outputMessage;
        while (true) {
            inputText = scanner.nextLine();
            if (inputText.equals("list")) {
                outputMessage = Task.getTasksList(tasks);
            } else if (inputText.startsWith("mark")) {
                int taskIndex = getTaskIndexFromInput(inputText);
                tasks[taskIndex].markAsDone();
                outputMessage = "Nice! I've marked this task as done: " + "\n\t"
                        + tasks[taskIndex].toString();
            } else if (inputText.startsWith("unmark")) {
                int taskIndex = getTaskIndexFromInput(inputText);
                tasks[taskIndex].unmarkAsDone();
                outputMessage = "Ok, I've marked this task as not done: " + "\n\t"
                        + tasks[taskIndex].toString();
            } else if (inputText.startsWith("task")) {
                String taskDescription = inputText.split("/")[0].split("task")[1].trim();
                Task newTask = new Task(taskDescription);
                tasks[Task.numberOfTasks - 1] = newTask;
                outputMessage = TASK_ADDED_PREFIX + newTask.toString() + "\n\t"
                        + getTaskAddedPostfix();
            } else if (inputText.startsWith("todo")) {
                String toDoDescription = inputText.split("/")[0].split("todo")[1].trim();
                Task newTodo = new ToDo(toDoDescription);
                tasks[Task.numberOfTasks - 1] = newTodo;
                outputMessage = TASK_ADDED_PREFIX + newTodo.toString() + "\n\t"
                        + getTaskAddedPostfix();
            } else if (inputText.startsWith("deadline")) {
                String deadlineDescription = inputText.split("/")[0].split("deadline")[1].trim();
                String deadlineBy = inputText.split("/")[1].trim();
                Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
                tasks[Task.numberOfTasks - 1] = newDeadline;
                outputMessage = TASK_ADDED_PREFIX + newDeadline.toString() + "\n\t"
                        + getTaskAddedPostfix();
            } else if (inputText.startsWith("event")) {
                String eventDescription = inputText.split("/")[0].split("event")[1].trim();
                String eventStart = inputText.split("/")[1].trim();
                String eventEnd = inputText.split("/")[2].trim();
                Task newEvent = new Event(eventDescription, eventStart, eventEnd);
                tasks[Task.numberOfTasks - 1] = newEvent;
                outputMessage = TASK_ADDED_PREFIX + newEvent.toString() + "\n\t"
                        + getTaskAddedPostfix();
            } else if (inputText.equals("bye")) {
                outputMessage = "Bye. Hope to see you again soon!";
                break;
            } else {
                outputMessage = "Sorry, I don't understand what you said. Can you say it again?";
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
        }
    }
    public static String getTaskAddedPostfix () {
        return "Now you have " + Task.numberOfTasks + " tasks in the list.";
    }
    public static int getTaskIndexFromInput (String inputText) {
        return Integer.parseInt(inputText.split(" ")[1]) - 1;
    }

}
