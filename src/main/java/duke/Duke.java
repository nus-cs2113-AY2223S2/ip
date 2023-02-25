package duke;

import duke.exceptions.ExcessInputsException;
import duke.exceptions.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     Hello! I'm Duke\n" +
            "     Welcome to Your To-do List!\n" +
            "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"delete 'task-index'\" to delete a task from the list.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n";
    public static final String LOGO =
              "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n";
    public static final String FILEPATH = "duke.txt";

    private static void addEvent(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /from ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] dates = description[1].split(" /to ");
        if (dates.length != 2) {
            throw new MissingDescriptionException();
        }
        Event event = new Event(description[0], "E", dates[0], dates[1]);
        tasks.add(event);
    }

    private static void addDeadline(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /by ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        Deadline deadline = new Deadline(description[0], "D", description[1]);
        tasks.add(deadline);
    }

    private static void addTodo(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        Task todo = new Task(description, "T");
        tasks.add(todo);
    }

    private static void deleteTask(ArrayList<Task> tasks, String[] words) {
        int index = Integer.parseInt(words[1]) - 1;
        System.out.print(LINE + "    Noted. I've removed this task:\n      " +
                tasks.get(index).toString());
        tasks.remove(index);
        System.out.print("\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }

    private static void editMarkStatus(ArrayList<Task> tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks.get(index).setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks.get(index).setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks.get(index).toString() + "\n" + LINE);
    }

    private static void printList(ArrayList<Task> tasks, String[] words) throws ExcessInputsException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i-1).toString());
        }
        System.out.print(LINE);
    }

    private static void processCommands(String line, Scanner in) {
        while (!line.equals("bye")) {
            String[] words = line.split(" ", 2);
            String command = words[0];
            if (command.equals("list")) {
                try {
                    printList(tasks, words);
                } catch (ExcessInputsException e) {
                    System.out.println(LINE + "Too many inputs! Please only enter \"list\"\n" + LINE);
                }
            } else if (command.contains("mark")) {
                try {
                    editMarkStatus(tasks, words, command);
                } catch (NumberFormatException e){
                    System.out.println(LINE + "Mark/Unmark must only be followed by the index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else if (command.equals("delete")) {
                try {
                    deleteTask(tasks, words);
                } catch (NumberFormatException e){
                    System.out.println(LINE + "Delete must only be followed by the index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else {
                try {
                    switch(command) {
                    case "todo":
                        addTodo(tasks, words);
                        break;
                    case "deadline":
                        addDeadline(tasks, words);
                        break;
                    case "event":
                        addEvent(tasks, words);
                        break;
                    default :
                        System.out.print(LINE + ERROR + LINE);
                        line = in.nextLine();
                        continue;
                    }
                    System.out.print(LINE + "    Got it. I've added this task:\n      " +
                            tasks.get(tasks.size()-1).toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                            LINE);
                } catch (MissingDescriptionException e){
                    System.out.println(LINE + "Missing task description details.\n" +
                            "Please check the input format for adding events\n" + LINE);
                }
            }
            line = in.nextLine();
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void readInputs() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        processCommands(line, in);
    }

    private static void writeToFile() throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(FILEPATH));
        for (Task x : tasks) {
            outputWriter.write(x.toString() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }

    private static void readFile() throws IOException {
        String line;
        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);
        int taskCount = 0;
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split("] ", 2);
            String command = words[0];
            if (command.contains("[T]")) {
                String description = words[1];
                Task todo = new Task(description, "T");
                tasks.add(todo);
            } else if (command.contains("[D]")) {
                String[] description = words[1].split(" by: ");
                Deadline deadline = new Deadline(description[0], "D", description[1]);
                tasks.add(deadline);
            } else if (command.contains("[E]")) {
                String[] description = words[1].split(" from: ");
                String[] dates = description[1].split(" to: ");
                Event event = new Event(description[0], "E", dates[0], dates[1]);
                tasks.add(event);
            } else {
                System.out.print(LINE + "There are invalid inputs in you To-do List, " +
                        "please edit it first." + LINE);
            }
            if (command.contains("X")) {
                tasks.get(taskCount).setDone(true);
            }
            taskCount ++;
        }
    }
    private static void createFile() throws IOException {
        File file = new File(FILEPATH);
        if (file.createNewFile()) {
            System.out.println("     Data file has been created. Your list will be saved.");
        } else {
            System.out.println("     Data file already exists. You list will be updated.");
        }
    }

    public static void main(String[] args) {
        System.out.print(LINE + LOGO + GREET + LINE);
        storage = new Storage(FILEPATH);
        try {
            createFile();
            readFile();
        } catch (IOException e) {
            System.out.println(LINE + "Data File Missing! Check if you have accidentally deleted it.\n" + LINE);
        }
        readInputs();
        System.out.print(LINE + BYE + LINE);
    }
}