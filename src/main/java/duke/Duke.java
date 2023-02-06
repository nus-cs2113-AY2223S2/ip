package duke;

import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalTodoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    /**
     * Returns boolean value of true if input String is an integer,
     * else returns boolean value of false
     *
     * @param word String input to check if it is an integer
     * @return true if input String is an integer, otherwise false
     */
    public static boolean isNumeric(String word) {
        int valueToConvert;
        try {
            valueToConvert = Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input, please enter an integer.");
        }
        return false;
    }

    public static void main(String[] args) {
        greetingMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (!line.equals("bye")) { // Exits the program if input is "bye"
            String[] words = line.split(" ");
            if (line.equals("list")) {
                // List out all the tasks added
                list(tasks, taskCount);
            } else if (words[0].equals("unmark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as not done
                unmarkTask(tasks, taskCount, words);
            } else if (words[0].equals("mark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as done
                markTask(tasks, taskCount, words);
            } else {
                // Adding a task to the list
                try {
                    addTask(line, tasks, taskCount);
                    taskCount++;
                } catch (IllegalTaskException e) {
                    // Ignoring empty command inputs
                }
            }
            line = in.nextLine();
        }

        // Exiting the program
        exitMessage();
    }

    private static void addTask(String line, Task[] tasks, int taskCount) throws IllegalTaskException {
        if (line.contains("/by")) {
            // Adding a Deadline
            try {
                addDeadline(line, tasks, taskCount);
            } catch (IllegalDeadlineException e) {
                deadlineErrorMessage();
                throw new IllegalTaskException();
            }
        } else if (line.contains("/from") || line.contains("/to")) {
            // Adding an Event
            try {
                addEvent(line, tasks, taskCount);
            } catch (IllegalEventException e) {
                eventErrorMessage();
                throw new IllegalTaskException();
            } catch (IndexOutOfBoundsException e) {
                eventErrorMessage();
                throw new IllegalTaskException();
            }
        } else if (!line.isBlank()){
            // Adding a _Todo_
            try {
                addTodo(line, tasks, taskCount);
            } catch (IllegalTodoException e) {
                todoErrorMessage();
                throw new IllegalTaskException();
            }
        } else {
            throw new IllegalTaskException();
        }
    }

    private static void todoErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description.");
        borderLine();
    }

    private static void eventErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description, start time and end time");
        borderLine();
    }

    private static void deadlineErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description and deadline.");
        borderLine();
    }

    private static void addTodo(String line, Task[] tasks, int taskCount) throws IllegalTodoException {
        if (line.isBlank()) {
            throw new IllegalTodoException();
        } else {
            tasks[taskCount] = new Todo(line);
            addedTaskMessage(tasks[taskCount], taskCount);
        }
    }

    private static void addEvent(String line, Task[] tasks, int taskCount) throws IllegalEventException {
        String description = line.substring(0, line.indexOf("/from")).trim();
        String start = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String end = line.substring(line.indexOf("/to") + 3).trim();
        if (description.isBlank() || start.isBlank() || end.isBlank()) {
            throw new IllegalEventException();
        } else {
            tasks[taskCount] = new Event(description, start, end);
            addedTaskMessage(tasks[taskCount], taskCount);
        }
    }

    private static void addDeadline(String line, Task[] tasks, int taskCount) throws IllegalDeadlineException {
        String description = line.substring(0, line.indexOf("/by")).trim();
        String deadline = line.substring(line.indexOf("/by") + 3).trim();
        //System.out.println(description.isBlank());
        if (description.isBlank() || deadline.isBlank()) {
            throw new IllegalDeadlineException();
        } else {
            tasks[taskCount] = new Deadline(description, deadline);
            addedTaskMessage(tasks[taskCount], taskCount);
        }
    }

    private static void borderLine() {
        System.out.println("\t____________________________________________________________");
    }

    private static void exitMessage() {
        borderLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        borderLine();
    }

    private static void addedTaskMessage(Task currentTask, int taskCount) {
        borderLine();
        System.out.println("\t Alright, I have added this task: \n\t\t" + currentTask);
        System.out.println("\t You now have " + (taskCount + 1) + " tasks in your list.");
        borderLine();
    }

    private static void markTask(Task[] tasks, int taskCount, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            borderLine();
            System.out.println("\t Task " + taskNumber + " does not exist.");
            borderLine();
        } else {
            tasks[taskNumber - 1].markAsDone();
            // Printing out marked as done message
            borderLine();
            System.out.println("\t Understood. I've marked this task as done:");
            System.out.println("\t " + tasks[taskNumber - 1]);
            borderLine();
        }
    }

    private static void unmarkTask(Task[] tasks, int taskCount, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            borderLine();
            System.out.println("\t Task " + taskNumber + " does not exist.");
            borderLine();
        } else {
            tasks[taskNumber - 1].markAsNotDone();
            // Printing out marked as not done message
            borderLine();
            System.out.println("\t Understood. I've marked this task as not done yet:");
            System.out.println("\t " + tasks[taskNumber - 1]);
            borderLine();
        }
    }

    private static void list(Task[] tasks, int taskCount) {
        borderLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i]);
        }
        borderLine();
    }

    private static void greetingMessage() {
        borderLine();
        System.out.println("\t Hello! I'm Vivy.");
        System.out.println("\t Here are some commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - bye: I will shut down my program.");
        System.out.println("\t - Anything else will be recorded as a task. \n");
        System.out.println("\t Format for tasks:");
        System.out.println("\t   Deadlines: <description> /by <deadline>");
        System.out.println("\t              (eg. Eat bread /by Thursday)");
        System.out.println("\t      Events: <description> /from <start date/time> /to <end date/time>");
        System.out.println("\t              (eg. Meeting /from March 3 8pm /to 9pm)");
        System.out.println("\t        Todo: <description>");
        System.out.println("\t              (eg. Water the plants)");
        System.out.println("\t What can I do for you?");
        borderLine();
    }
}
