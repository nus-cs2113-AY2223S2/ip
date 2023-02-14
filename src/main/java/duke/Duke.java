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

public class Duke {
    
    static Task[] tasks = new Task[100];
    private static int count = 0;
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     Hello! I'm Duke\n" +
            "     Welcome to Your To-do List!\n" +
            "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n";
    public static final String FILEPATH = "src/main/java/duke/data/duke.txt";

    public static void printAddedTask(Task task, int total) {
        System.out.print(LINE + "    Got it. I've added this task:\n      " +
                task.toString() + "\n    Now you have " + total + " tasks in the list.\n" +
                LINE);
    }

    private static void addEvent(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
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
        tasks[count] = new Event(description[0], "E", dates[0], dates[1]);
    }

    private static void addDeadline(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /by ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        tasks[count] = new Deadline(description[0], "D", description[1]);
    }

    private static void addTodo(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        tasks[count] = new Task(description, "T");
    }

    private static void editMarkStatus(Task[] tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks[index].setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks[index].setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks[index].toString() + "\n" + LINE);
    }

    private static void printList(int count, Task[] tasks, String[] words) throws ExcessInputsException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= count; i++) {
            System.out.println("    " + i + "." + tasks[i-1].toString());
        }
        System.out.print(LINE);
    }

    private static void processCommands(String line, int count, Scanner in) {
        while (!line.equals("bye")) {
            String[] words = line.split(" ", 2);
            String command = words[0];
            if (command.equals("list")) {
                try {
                    printList(count, tasks, words);
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
                    System.out.println(LINE + "Missing task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else {
                try {
                    if (command.equals("todo")) {
                        addTodo(count, tasks, words);
                    } else if (command.equals("deadline")) {
                        addDeadline(count, tasks, words);
                    } else if (command.equals("event")) {
                        addEvent(count, tasks, words);
                    } else {
                        System.out.print(LINE + ERROR + LINE);
                        line = in.nextLine();
                        continue;
                    }
                    count++;
                    printAddedTask(tasks[count -1], count);
                } catch (MissingDescriptionException e){
                    System.out.println(LINE + "Missing task description details.\n" +
                            "Please check the input format for adding events\n" + LINE);
                }
            }
            line = in.nextLine();
        }
    }

    private static void readInputs() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        processCommands(line, count, in);
    }

    private static void writeToFile() throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(FILEPATH));
        for (int i = 0; i < count; i += 1) {
            outputWriter.write(tasks[i].toString() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }

    private static void readFile() throws IOException {
        String line;
        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split("] ", 2);
            String command = words[0];
            if (command.contains("[T]")) {
                String description = words[1];
                tasks[count] = new Task(description, "T");
            } else if (command.contains("[D]")) {
                String[] description = words[1].split(" by: ");
                tasks[count] = new Deadline(description[0], "D", description[1]);
            } else if (command.contains("[E]")) {
                String[] description = words[1].split(" from: ");
                String[] dates = description[1].split(" to: ");
                tasks[count] = new Event(description[0], "E", dates[0], dates[1]);
            } else {
                System.out.print(LINE + "There are invalid inputs in you To-do List, " +
                        "please edit it first." + LINE);
            }
            if (command.contains("X")) {
                tasks[count].setDone(true);
            }
            count ++;
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
        System.out.print(LINE + GREET + LINE);
        try {
            createFile();
            readFile();
        } catch (IOException e) {
            System.out.println(LINE + "Data File Missing! Check if you have accidentally deleted it.\n" + LINE);
        }
        readInputs();
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.print(LINE + BYE + LINE);
    }
}