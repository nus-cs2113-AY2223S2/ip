package Duke;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I be of service today?");
    }

    public void bye() {
        System.out.println("Glad I could be of help!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static void addTaskSuccess() {
        System.out.println("As per requested Sire, this task has been added to your calendar.");
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }

    public void showLine(String line) {
        System.out.println(line);
    }

    public static void markSuccess(ArrayList<Task> list, String indexOfTask) {
        System.out.println("Sir, your task has been marked as completed.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void unmarkSuccess(ArrayList<Task> list, String indexOfTask) {
        System.out.println("Sir, your task has been unmarked as requested.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void deleteSuccess(ArrayList<Task> list, String indexOfTask) {
        System.out.println("Sire, I have removed this task from your schedule");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void findSuccess() {
        System.out.println("Here are the matching items Sire:");
    }

    public static void printListSize(ArrayList<Task> list) {
        System.out.println("You now have " + list.size() + " items left");
    }

    public static void printCurrentList(ArrayList<Task> list) {
        System.out.println("Your current list of items as requested, sir.");
        for (int i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}

