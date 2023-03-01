package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private static final String STORAGE_FILE_NAME = "./data/Duke.txt";

    private static ArrayList<Tasks> listOfTasks = new ArrayList<Tasks>();
    private static int counter = 0;

    public static void greet_user() {
        System.out.println("Hello! I'm Duke \n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("How can i help u? \n");
    }

    public static void print_action() {
        System.out.println("\n");
        System.out.println("I have added this task: ");
        System.out.println(listOfTasks.get(counter - 1).toString());

        System.out.println("You now have " + counter + " tasks in the list.");
    }

    public static void Todo(String description) {
        Tasks t = new ToDo(description);
        listOfTasks.add(t);
        counter++;
        print_action();
    }

    public static void Deadline(String description, String by) {
        Deadline d = new Deadline(description, by);
        listOfTasks.add(d);
        counter++;
        print_action();
    }

    public static void Event(String description, String start, String end) {
        Event e = new Event(description, start, end);
        listOfTasks.add(e);
        counter++;
        print_action();
    }

    public static void List() {
        System.out.println("Here are all of your " + counter + " tasks: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + listOfTasks.get(i).toString());
        }
    }

    public static void Delete(int index) {
        Tasks currentTask = listOfTasks.get(index - 1);
        System.out.println("\n");
        System.out.println("Noted. I have deleted this task");
        System.out.println(currentTask.toString());
        listOfTasks.remove(index - 1);
        counter--;
        System.out.println("You now have " + counter + " tasks in the list.");
    }

    public static void findFile() {
        try {
            File file = new File(STORAGE_FILE_NAME);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("Error: File not found. Please try again");
        }
    }

    public static void saveFile() {
        try {
            FileWriter writer = new FileWriter(STORAGE_FILE_NAME);
            for (int i = 0; i < listOfTasks.size(); i++) {
                writer.write(listOfTasks.get(i).toString() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Tasks were not saved. Please try again");
        }
    }

    public static void Find(String keyword) {
        if (listOfTasks.isEmpty()) {
            System.out.println("There are no tasks listed currently");
        } else {
            int index = 1;
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (listOfTasks.get(i).description.contains(keyword)) {
                    if (index == 1) {
                        System.out.println("Here are the matching tasks in your list: ");
                    }
                    System.out.println(index + "." + listOfTasks.get(i).toString());
                    index++;
                }
            }
        }
    }

    public static void main(String[] args) {
        greet_user();
        findFile();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] command = input.split(" ", 2);

        while (!"bye".equals(input)) {
            switch (command[0]) {
                case "todo":
                    try {
                        Todo(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "deadline":
                    try {
                        String[] d = command[1].split("/by", 2);
                        String d_description = d[0];
                        String d_by = d[1];
                        Deadline(d_description, d_by);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "event":
                    try {
                        String[] e = command[1].split("/", 3);
                        String e_description = e[0];
                        String e_start = e[1];
                        String e_end = e[2];
                        Event(e_description, e_start, e_end);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "list":
                    if (listOfTasks.isEmpty()) {
                        System.out.println("There are no tasks listed currently");
                    } else {
                        List();
                    }
                    break;
                case "mark":
                    try {
                        Integer m_index = Integer.valueOf(command[1]);
                        listOfTasks.get(m_index - 1).markAsDone();
                        System.out.println("Nice! This task is completed");
                        System.out.println(listOfTasks.get(m_index - 1).toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "unmark":
                    try {
                        Integer u_index = Integer.valueOf(command[1]);
                        listOfTasks.get(u_index - 1).markAsUnDone();
                        System.out.println("Nice! This task is completed");
                        System.out.println(listOfTasks.get(u_index - 1).toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "delete":
                    try {
                        Integer d_index = Integer.valueOf(command[1]);
                        Delete(d_index);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "find":
                    String keyword = command[1];
                    Find(keyword);
                    break;
                default:
                    System.out.println("There was an error. Please try again");
                    break;
            }
            input = scan.nextLine();
            command = input.split(" ", 2);
        }

        System.out.println("Goodbye. Hope to see u again :) \n");
        scan.close();
        saveFile();
    }

}