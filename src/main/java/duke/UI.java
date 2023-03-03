package duke;

import duke.task.Task;

import java.util.ArrayList;

public class UI {

    /**
     * Prints all the tasks in the task list.
     *
     * @param tasks The task list to be printed out.
     */
    public void printAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }

    /**
     * Prints all tasks containing a certain word.
     *
     * @param foundTasks The ArrayList of tasks to be printed out
     */
    public void printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : foundTasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }

    /**
     * Prints a task.
     *
     * @param task The task to be printed.
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints message that acknowledges successful adding of task.
     */
    public void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints message that acknowledges successful marking of a task as done.
     */
    public void printMarkDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints message that acknowledges successful marking of a task as not done.
     */
    public void printMarkNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints message that acknowledges successful removal of task from task list.
     */
    public void printDeleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints the number of tasks in the task list.
     *
     * @param size Number of tasks in the task list.
     */
    public void printNoOfTasks(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints error message if user's input cannot be parsed.
     */
    public void printInvalidMessage() {
        System.out.println("I don't know what that means :-(");
    }

    /**
     * Prints error message if reading from saved file is unsuccessful.
     */
    public void printInvalidFileRead() {
        System.out.println("Unable to read from file.");
    }

    /**
     * Prints error message if the task number provided by the user is not an integer.
     */
    public void printTaskNumberWarning() {
        System.out.println("The task number must be an integer between 1 and the total number of tasks in the list.");
    }

    /**
     * Prints warning message if the task number provided by the user does not exist.
     */
    public void printInvalidTaskNumber() {
        System.out.println("The given task number does not exist.");
    }

    /**
     * Prints error message if unable to save task list to the hard disk.
     */
    public void printSavingError() {
        System.out.println("Unable to save.");
    }

    /**
     * Prints error message if JSON file cannot be loaded.
     */
    public void printLoadingError() {
        System.out.println("Unable to load saved file.");
    }

    /**
     * Prints warning message if a completed task is marked as done again.
     */
    public void printTaskAlreadyMarked() {
        System.out.println("The task is already marked as done.");
    }

    /**
     * Prints warning message if an incomplete task is marked as not done again.
     */
    public void printTaskAlreadyUnmarked() {
        System.out.println("The task is already marked as not done.");
    }

    /**
     * Prints error message if the user begins their command with an invalid keyword or
     * does not provide a description of the task.
     */
    public void printEmptyDescriptionError() {
        System.out.println("The command was not a valid word (todo/event/deadline/mark/unmark/delete/list/bye/find) ");
        System.out.println("or the task description was not specified.");
    }

    /**
     * Prints warning message if the user provides a Todo task without any description.
     */
    public void printEmptyTodo() {
        System.out.println("The description of a todo cannot be empty.");
    }

    /**
     * Prints warning message if the user provides a Deadline task with invalid format.
     */
    public void printInvalidDeadline() {
        System.out.println("Invalid format. Specify deadlines in the format 'deadline EVENT /by TIME'.");
    }

    /**
     * Prints warning message if the user provides an Event task with invalid format.
     */
    public void printInvalidEvent() {
        System.out.println("Invalid format. Specify events in the format 'event DESCRIPTION /from A /to B'.");
    }

    /**
     * Prints warning message if the user enters the "find" command without specifying
     * a word to be searched for.
     */
    public void printMissingWord() {
        System.out.println("Please specify the word to be found.");
    }

    /**
     * Prints message if there are no tasks to be loaded from the hard disk.
     */
    public void printNoTasksToLoad() {
        System.out.println("There are no tasks to load.");
    }

    /**
     * Prints error message if the user's input cannot be executed.
     */
    public void printCommandExecutionFailure() {
        System.out.println("Command could not be executed.");
    }

    /**
     * Prints goodbye message before exiting the program.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints separator characters between commands when needed for readability.
     */
    public void printSeparator() {
        System.out.println("============================================");
    }

    /**
     * Prints welcome message when program first starts.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke.");
        System.out.println("What can I do for you?");
    }
}
