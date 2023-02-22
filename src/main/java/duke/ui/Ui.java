package duke.ui;

import duke.tasks.TaskList;

public class Ui {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void printLine(){
        System.out.println(LONG_LINE);
    }

    public static void printGreetings (){
        printLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printListMessage(int listCount) {
        System.out.println("Now you have " + Integer.toString(listCount) + " tasks in the list.");
    }
    public static void deleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    public static void printAddedTaskCommand(TaskList taskList) {
        int lastElement = taskList.listCount() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.getTask(lastElement).toString());
        System.out.println("Now you have " + Integer.toString(taskList.listCount()) + " tasks in the list.");
    }

    public static void markDoneMessage(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(index).toString());
    }

    public static void markUndoneMessage(TaskList taskList, int index){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(index).toString());
    }

    public static void printOpeningListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void printFindMessage(){
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printInvalidMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printEmptyCommandMessage(String command) {
        System.out.println("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }

    public static void printInvalidIndexMessage() {
        System.out.println("No such task exist! Try again.");
    }
}
