package app.ui;

import app.exceptions.DukeException;
import app.parser.Parser;
import app.tasks.Task;
import app.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    public static Scanner sc = new Scanner(System.in);

    public String readCommand() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    public void showWelcome() {
        Constants.printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Constants.printLine();
    }

    public void showLine() {
        Constants.printLine();
    }

    public void showLoadingError() {
        System.out.println("No saved tasks detected! Creating a new list.");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void newTaskAddedMessage(Task newTask, int tasksCount) {
        System.out.println("Got it. I've added this task:\n" + newTask.toString() +
                "\nYou now have " + tasksCount + " tasks in your list.");
    }

    public void printTasks(TaskList tasks) throws DukeException {
        for (int i = 0; i < tasks.getTasksCount(); i++) {
            String taskDescription = tasks.getTask(i).toString();
            System.out.println((i + 1) + ". " + taskDescription);
        }
    }

    public void taskDeletedMessage(Task deletedTask, int tasksCount) {
        System.out.println("Noted, I've removed this task.\n" + deletedTask.toString() +
                "\nYou now have " + tasksCount + " tasks left in your list.");
    }

    public void taskMarkedMessage(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:\n" +
                markedTask.toString());
    }

    public void taskUnmarkedMessage(Task unmarkedTask) {
        System.out.println("OK! I've marked this task as not done yet:\n" +
                unmarkedTask.toString());
    }
}
