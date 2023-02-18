package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "duke.txt";

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    public static void loadData() {
        try {
            File file = new File(FILE_PATH);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                loadTasks(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("New file cannot be created");
            }
        }
    }

    public static void loadTasks(String input) {
        String[] splitInput = input.split("->");
        String taskType = splitInput[0].trim();
        String status = splitInput[1].trim();
        String taskDescription = splitInput[2].trim();

        switch (taskType) {
        case "T":
            tasks.add(new Todo(taskDescription));
            break;
        case "D":
            String by = splitInput[3].trim();
            tasks.add(new Deadline(taskDescription, by));
            break;
        case "E":
            String from = splitInput[3].trim();
            String to = splitInput[4].trim();
            tasks.add(new Event(taskDescription, from, to));
            break;
        }
        if (status.equals("1")) {
            tasks.get(tasks.size() - 1).markDone();
        }
    }

    public static void saveTask() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String status;
                String lineToAdd = "";
                if (task.getStatusIcon().equals("X")) {
                    status = "1";
                } else {
                    status = "0";
                }
                if (task instanceof Todo) {
                    lineToAdd = "T" + " -> " + status + " -> " + task.description + "\n";
                }
                if (task instanceof Deadline) {
                    lineToAdd = "D" + " -> " + status + " -> " + task.description + " -> " + ((Deadline) task).by + "\n";
                }
                if (task instanceof Event) {
                    String time = ((Event) task).from + " -> " + ((Event) task).to;
                    lineToAdd = "E" + " -> " + status + " -> " + task.description + " -> " + time + "\n";
                }
                fw.write(lineToAdd);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }
    }


    private static void markAsDone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            printLine();
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Great! I have marked this task as done:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            printLine();
            saveTask();
        }
    }

    private static void markAsUndone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            printLine();
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            printLine();
            saveTask();
        }
    }

    private static void deleteTask(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            printLine();
            Task deletedTask = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.print("\t  ");
            System.out.println(deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
            printLine();
            saveTask();
        }
    }

    private static void printList() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Sorry, there are no tasks in the list currently.");
        } else {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print("\t" + (i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
        printLine();
    }

    private static void addTodo(String input) throws DukeException, IOException {
        String[] words = input.split(" ");
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(input.substring(5));
            tasks.add(todo);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
            saveTask();
        }
    }

    private static void addDeadline(String input) throws DukeException, IOException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        } else {
            String[] wordDeadline = input.substring(9).split("/");
            Deadline deadline = new Deadline(wordDeadline[0].trim(), wordDeadline[1].substring(3));
            tasks.add(deadline);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
            saveTask();
        }
    }

    private static void addEvent(String input) throws DukeException, IOException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or time of an event cannot be empty.");
        } else {
            String[] wordEvent = input.substring(5).split("/", 3);
            Event event = new Event(wordEvent[0].trim(), wordEvent[1].substring(5), wordEvent[2].substring(3));
            tasks.add(event);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
            saveTask();
        }
    }


    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String[] words = input.split(" ");
                switch (words[0]) {
                case "list":
                    printList();
                    break;

                case "mark":
                    markAsDone(input);
                    break;

                case "unmark":
                    markAsUndone(input);
                    break;

                case "todo":
                    addTodo(input);
                    break;

                case "deadline":
                    addDeadline(input);
                    break;

                case "event":
                    addEvent(input);
                    break;

                case "delete":
                    deleteTask(input);
                    break;

                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            } catch (IOException ex) {
                printLine();
                System.out.println("Unable to save data");
                printLine();
            }
            input = in.nextLine();
        }
    }


    public static void main(String[] args) {
        loadData();
        printWelcomeMessage();
        runDuke();
        printByeMessage();
    }
}

