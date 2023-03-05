package Duke;

import Duke.Task.Task;

import java.io.File;
import java.io.FileNotFoundException;
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
        try {
            printFileContents("dukeData.txt");
        } catch (FileNotFoundException e) {
            printErrorMessage("File not found");
        }
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

    /**
     * Prints out the number of elements in the list.
     *
     * @param list arraylist whose number of elements is to be printed out.
     */
    public static void printListSize(ArrayList<Task> list) {
        System.out.println("You now have " + list.size() + " items left");
    }

    /**
     * Prints out all tasks currently in the list.
     *
     * @param list arraylist whose elements are to be printed out.
     */
    public static void printCurrentList(ArrayList<Task> list) {
        System.out.println("Your current list of items as requested, sir.");
        for (int i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Prints out all tasks saved in the data file.
     *
     * @param filePath file path of the data file whose content is to be printed out.
     * @throws FileNotFoundException If file path is invalid.
     */
    public void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) { // for first log in, there is no file
            return;
        }
        System.out.println("Good day sire, I have listed down your current plan below for you:");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String taskStored = s.nextLine();
            System.out.println(taskStored);
        }
    }
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}

