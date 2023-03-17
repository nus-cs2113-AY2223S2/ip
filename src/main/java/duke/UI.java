package duke;//import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class UI {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void UI() {

        /**loading up contents into program*/
        FileHandling.loadContents();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + '\n');

        System.out.println("Hello I'm Duke!\nWhat can I do for you?\n");


        Scanner in = new Scanner(System.in);
        String readInput = in.nextLine();
        String command = Parser.parseType(readInput);
        String taskDescOrTaskNo = Parser.parseTaskDescOrTaskNo(readInput);

        while (!(command.equals("Bye") || command.equals("bye"))) {
            if (command.equals("list")) {
                listTasks();
            } else if (command.equals("unmark")) {
                unmarkTask(taskDescOrTaskNo);
            } else if (command.equals("mark")) {
                markTask(taskDescOrTaskNo);
            } else if (command.equals("delete")) {
                deleteTask(taskDescOrTaskNo);
            } else if (command.equals("todo")) {
                addTodo(taskDescOrTaskNo);
            } else if (command.equals("deadline")) {
                addDeadline(taskDescOrTaskNo);
            } else if (command.equals("event")) {
                addEvent(taskDescOrTaskNo);
            } else if (command.equals("find")) {
                findTask(taskDescOrTaskNo);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            readInput = in.nextLine();
            command = Parser.parseType(readInput);
            taskDescOrTaskNo = Parser.parseTaskDescOrTaskNo(readInput);
        }

        System.out.println("Bye. Hope to see you again!");
    }

    private static void findTask(String taskDescOrTaskNo) {
        try {
            ArrayList<Task> tasksFound = TaskList.find_Input(tasks, taskDescOrTaskNo);
            TaskList.list_Input(tasksFound);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!!  What are you trying to find?");
        }
    }

    private static void addEvent(String taskDescOrTaskNo) {
        try {
            TaskList.event_Input(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Please ensure all fields are present and are correct! See User Guide for eg.");
        }
    }

    private static void addDeadline(String taskDescOrTaskNo) {
        try {
            TaskList.deadline_Input(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("What is the task you are trying to tell me?");
        }
    }

    private static void addTodo(String taskDescOrTaskNo) {
        try {
            TaskList.todo_Input(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        }
    }

    private static void deleteTask(String taskDescOrTaskNo) {
        try {
            TaskList.delete(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! Which task do you want to delete?");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("This is not within the number of tasks you have. Type list to see how many.");
        } catch (NumberFormatException n) {
            System.out.println("This is not a number.");
        }
    }

    private static void markTask(String taskDescOrTaskNo) {
        try {
            TaskList.mark_Input(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! Which task do you want to mark?");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("This is not within the number of tasks you have. Type list to see how many.");
        } catch (NumberFormatException n) {
            System.out.println("This is not a number.");
        }
    }

    private static void unmarkTask(String taskDescOrTaskNo) {
        try {
            TaskList.unmark_Input(tasks, taskDescOrTaskNo);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! Which task do you want to unmark?");
        } catch (IOException i) {
            System.out.println("An error occurred with txt file");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("This is not within the number of tasks you have. Type list to see how many.");
        } catch (NumberFormatException n) {
            System.out.println("This is not a number.");
        }
    }

    private static void listTasks() {
        try {
            TaskList.list_Input(tasks);
        } catch (DukeException d) {
            System.out.println("Your list is empty!");
        }
    }
}

