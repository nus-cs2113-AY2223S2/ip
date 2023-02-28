package orca;

import java.util.ArrayList;

public class Ui {
    static final String LOGO = "     _______  ______    _______  _______ \n"
            + "    |       ||    _ |  |       ||   _   | \n"
            + "    |   _   ||   | ||  |       ||  |_|  | \n"
            + "    |  | |  ||   |_||_ |       ||       | \n"
            + "    |  |_|  ||    __  ||      _||       | \n"
            + "    |       ||   |  | ||     |_ |   _   | \n"
            + "    |_______||___|  |_||_______||__| |__| \n";

    public void printGreetingMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println(LOGO);
        System.out.println("    Hello! I'm Orca, your assistant chatbot.");
        System.out.println("    What can I do for you?");
        System.out.println("    --------------------------------------------------\n");
    }

    public void printByeMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------------------\n");
    }

    public static void printUnknownCommandMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Sorry, I don't understand what you mean.");
        System.out.println("    --------------------------------------------------\n");
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    --------------------------------------------------\n");
    }

    public void printFoundTasks(ArrayList<Task> tasks) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    --------------------------------------------------\n");
    }

    public void printRemovedTask(Task removedTask, int taskSize) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + removedTask);
        System.out.println("    Now you have " + taskSize + " tasks in the list.");
        System.out.println("    --------------------------------------------------\n");
    }

    public void printMarkedTask(Task markedTask) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + markedTask);
        System.out.println("    --------------------------------------------------\n");
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Nice! I've marked this task as not done yet:");
        System.out.println("      " + unmarkedTask);
        System.out.println("    --------------------------------------------------\n");
    }

    public void printAddedTask(Task task, int taskSize) {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskSize + " tasks in the list.");
        System.out.println("    --------------------------------------------------\n");
    }
}
