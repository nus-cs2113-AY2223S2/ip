package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    static void list(ArrayList<Task> tasks) {
        int taskCount = Task.getTaskCount();
        borderLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
        borderLine();
    }

    static void borderLine() {
        System.out.println("\t____________________________________________________________");
    }

    static void emptyCommandMessage() {
        borderLine();
        System.out.println("\t Please enter a non-empty command.");
        borderLine();
    }

    static void addedTaskMessage(Task currentTask) {
        borderLine();
        System.out.println("\t Alright, I have added this task: \n\t\t" + currentTask);
        System.out.println("\t You now have " + (Task.getTaskCount() + 1) + " tasks in your list.");
        borderLine();
    }

    static void deleteTaskMessage(Task taskToDelete) {
        borderLine();
        System.out.println("\t Understood. I have removed this task:");
        System.out.println("\t\t" + taskToDelete);
        System.out.println("\t You now have " + Task.getTaskCount() + " tasks in your list.");
        borderLine();
    }

    static void exceedTaskNumberMessage(int taskNumber) {
        borderLine();
        System.out.println("\t Task " + taskNumber + " does not exist.");
        borderLine();
    }

    static void todoErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description.");
        borderLine();
    }

    static void eventErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description, start time and end time");
        borderLine();
    }

    static void deadlineErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description and deadline.");
        borderLine();
    }

    static void greetingMessage() {
        borderLine();
        System.out.println("\t Hello! I'm Vivy.");
        System.out.println("\t Here are some commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - delete <task_number>: I'll delete that task from your list.");
        System.out.println("\t - bye: I will shut down my program.");
        System.out.println("\t - Anything else will be recorded as a task. \n");
        System.out.println("\t Format for tasks:");
        System.out.println("\t   Deadlines: <description> /by <deadline>");
        System.out.println("\t              (eg. Eat bread /by Thursday)");
        System.out.println("\t      Events: <description> /from <start date/time> /to <end date/time>");
        System.out.println("\t              (eg. Meeting /from March 3 8pm /to 9pm)");
        System.out.println("\t        Todo: <description>");
        System.out.println("\t              (eg. Water the plants) \n");
        System.out.println("\t What can I do for you?");
        borderLine();
    }

    static void exitMessage() {
        borderLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        borderLine();
    }
}