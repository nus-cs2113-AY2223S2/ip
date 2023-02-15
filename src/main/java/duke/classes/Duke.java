package duke.classes;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static void checkError(String input) throws DukeException {
        if (Objects.equals(input, "event") || Objects.equals(input, "todo") || Objects.equals(input, "deadline")) {
            throw new DukeException("The description of the body cannot be empty! Please enter a proper input.");
        } else if (Objects.equals(input, "")) {
            throw new DukeException("You did not enter any input! Please enter a proper input.");
        } else {
            throw new DukeException("I'm sorry, but i don't know what that means. Please enter a proper input.");
        }
    }

    private static ArrayList<Task> listOfTask = new ArrayList<Task>();

    private static void addTask(Task task) {
        listOfTask.add(task);
    }

    private static void markTask(Task task) {
        task.isDone = true;
    }

    private static void unmarkTask(Task task) {
        task.isDone = false;
    }
    private static void printTasks() {
        int order = 1;
        for (int i = 0; i < listOfTask.size(); i++) {
            System.out.println(order + "." + listOfTask.get(i));
            order++;
        }
    }

    public static void printFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

    public static void foundationList(String filepath, ArrayList<Task> listOfTask) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String temp = scan.nextLine();
            String type = temp.substring(1,2);
            String status = temp.substring(6,7);
            if (type.equals("T")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                addTask(task);
            } else if (type.equals("D")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    markTask(task);
                } else {
                    unmarkTask(task);
                }
                addTask(task);
            } else if (type.equals("E")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    markTask(task);
                } else {
                    unmarkTask(task);
                }
                addTask(task);
            }
        }
    }

    public static void writeFile(String filePath, String textAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textAdd);
        writer.close();
    }

    public static void appendFile(String filepath, String textAppend) throws IOException {
        FileWriter writer = new FileWriter(filepath, true);
        writer.write(textAppend);
        writer.close();
    }

    public static void updateFile(String filePath, ArrayList<Task> listOfTask) throws IOException {
        String newList = "";
        for (int i = 0; i < listOfTask.size(); i++) {
            newList += listOfTask.get(i).toString() + System.lineSeparator();
        }
        writeFile(filePath, newList);
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you\n");

        try {
            File file = new File("src/duke_list.txt");
            if (file.createNewFile()) {
                System.out.println("the file has been created");
            } else {
                System.out.println("the file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        String filePath = "src/duke_list.txt";
        foundationList(filePath, listOfTask);

        for (int i = 0; i < listOfTask.size(); i++) {
            count++;
        }

        try {
            System.out.println("This is the current content of the duke_list file, if any:");
            printFile("src/duke_list.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println("----------------------End of file----------------------");
        System.out.println("Please input your commands here: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        boolean isBye = false;
        while (!isBye) {

            if (Objects.equals(input, "bye")) {
                isBye = true;
                updateFile(filePath, listOfTask);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                printTasks();

            } else if (input.length() > 5 && (input.substring(0,5)).equals("mark ") && input.substring(5, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(5, input.length()));
                    if(order - 1 >= count) {
                        System.out.println("You cannot mark a task that hasn't been made");
                    } else {
                        Task task = listOfTask.get(order - 1);
                        markTask(task);
                        listOfTask.set(order - 1, task);
                        updateFile(filePath, listOfTask);
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    }
            } else if (input.length() > 7 && (input.substring(0,7)).equals("unmark ") && input.substring(7, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(7, input.length()));
                    if(order - 1 >= count) {
                        System.out.println("You cannot unmark a task that hasn't been made");
                    } else {
                        Task task = listOfTask.get(order - 1);
                        unmarkTask(task);
                        listOfTask.set(order - 1, task);
                        updateFile(filePath, listOfTask);
                        System.out.println("OK, I've marked this task as not done yet:\n" + task);
                    }
            } else if (input.length() > 7 && input.substring(0,7).equals("delete ") && input.substring(7, input.length()).matches("[0-9]+")) {
                Integer order = Integer.valueOf(input.substring(7, input.length()));
                if(order - 1 >= count) {
                    System.out.println("You cannot delete a task that hasn't been made");
                }
                else {
                    System.out.println("Noted, I've removed this task\n" + listOfTask.get(order - 1));
                    listOfTask.remove(order - 1);
                    updateFile(filePath, listOfTask);
                    count--;
                    System.out.println("Now you have " + count + " tasks in the list");
                }
            } else {
                if (input.length() > 3 && input.substring(0,4).equals("todo")) {
                    String info = input.substring(5,input.length());
                    Todo task = new Todo(info);
                    task.isDone = false;
                    addTask(task);
                    updateFile(filePath, listOfTask);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else if (input.length() > 7 && input.substring(0,8).equals("deadline")) {
                    String info = input.substring(9,input.indexOf("/"));
                    String timeBy = input.substring(input.indexOf("/")+1, input.length());
                    Deadline task = new Deadline(info, timeBy);
                    task.isDone = false;
                    addTask(task);
                    updateFile(filePath, listOfTask);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else if (input.length() > 4 && input.substring(0,5).equals("event")) {
                    String info = input.substring(6,input.indexOf("/"));
                    String timeFrom = input.substring(input.indexOf("/")+1, input.lastIndexOf("/") - 1);
                    String timeBy = input.substring(input.lastIndexOf("/")+1, input.length());
                    Event task = new Event(info, timeFrom, timeBy);
                    task.isDone = false;
                    addTask(task);
                    updateFile(filePath, listOfTask);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else {
                    try {
                        checkError(input);
                    } catch(DukeException e) {
                        System.out.println("Error: " + e);
                    }
                }
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
