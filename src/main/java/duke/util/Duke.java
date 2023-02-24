package duke.util;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exception.DukeException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String FILE_DIRECTORY = "data";
    public static final String FILE_NAME = "duke.txt";
    public static final String LINE_BREAK = "---------------------------------------------";

    public static void DeleteTask (ArrayList<Task> lists, int itemNumber) {
        int index = itemNumber - 1;
        Task item = lists.get(index);
        System.out.println("Got it. I've removed this task: " + item.getTypeIcon()
                + item.getStatusIcon() + item.description);
        lists.remove(index);
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }


    public static void registerTodo (ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLine = line.split(" ", 2);
            Task item = new Todo(inputLine[1]);
            lists.add(item);
            printAddTask(item, lists.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static void registerDeadline (ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /by ");
            String description = inputLines[0];
            String deadline = inputLines[1];
            Task item = new Deadline(description, deadline);
            lists.add(item);
            printAddTask(item, lists.size());
        } catch (IndexOutOfBoundsException e) {
        System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
    }
    }

    public static void registerEvent (ArrayList<Task> lists, String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /from ");
            String description = inputLines[0];
            inputLines = inputLines[1].split(" /to ");
            String start = inputLines[0];
            String end = inputLines[1];
            Task item = new Event(description, start, end);
            lists.add(item);
            printAddTask(item, lists.size());
        } catch (IndexOutOfBoundsException e) {
        System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static void printAddTask (Task item, int size) {
        System.out.println("Got it. I've added this task: " + item.getTypeIcon()
                + item.getStatusIcon() + item.description);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    public static void printIntro () {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public static void printExiting() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    public static void printError() {
        System.out.println("You chose a task that does not exist!");
        System.out.println(LINE_BREAK);
    }

    public static String getInput() throws NoSuchElementException {
        Scanner in = new Scanner(System.in);
        String line = "";
        try {
            line = in.nextLine();
        } catch (NoSuchElementException e){
            printExiting();
        }
        return line;
    }

    public static void identifyInput(String line, ArrayList<Task> lists) throws DukeException {
        try {
            if (line.startsWith("todo")) {
                registerTodo(lists, line);
            } else if (line.startsWith("deadline")) {
                registerDeadline(lists, line);
            } else if (line.startsWith("event")) {
                registerEvent(lists, line);
            } else {
                throw new DukeException();
            }
        } catch (DukeException e){
            System.out.println("I am not a chat bot, please do not chat to me.");
        }
    }

    public static void main (String[] args) throws DukeException, FileNotFoundException {
        ArrayList<Task> lists = new ArrayList<>();
        Storage.readDukeFile(lists, FILE_DIRECTORY, FILE_NAME);
        printIntro();
        while (true) {
            String line = getInput();
            String[] words = line.split(" ");
            System.out.println(LINE_BREAK);
            if (line.contains("list")) {
                System.out.println("Here are the tasks in your list:");
                int itemNumber = 1;
                for (Task item : lists) {
                    if ((itemNumber - 1) == lists.size()) {
                        break;
                    }
                    item.printTask(itemNumber);
                    itemNumber++;
                }
            }
            else if (line.startsWith("mark") || line.startsWith("unmark") || line.startsWith("delete")) {
                try {
                    int itemNumber = Integer.parseInt(words[1]);
                    if (line.startsWith("mark")) {
                        lists.get(itemNumber - 1).markAsDone();
                    } else if (line.startsWith("unmark")) {
                        lists.get(itemNumber - 1).markAsUndone();
                    } else {
                        DeleteTask(lists, itemNumber);
                    }
                    Storage.saveDataFromInput(FILE_DIRECTORY, FILE_NAME, lists);
                } catch (IndexOutOfBoundsException e) {
                    printError();
                }
            }
            else if (line.equals("bye")) {
                break;
            }
            else {
                identifyInput(line, lists);
                Storage.saveDataFromInput(FILE_DIRECTORY, FILE_NAME, lists);
            }
        }
        printExiting();
    }
}
