package duke;//import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import static java.util.stream.Collectors.toList;

public class UI {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void UI() {

        //loading up contents into program
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
        String TaskDescOrTaskNo = Parser.parseTaskDescOrTaskNo(readInput);

        while (!(command.equals("Bye") || command.equals("bye"))) {
            if (command.equals("list")) {
                TaskList.list_Input(tasks);
            } else if (command.equals("unmark")) {
                try {
                    TaskList.unmark_Input(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to unmark?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("mark")) {
                try {
                    TaskList.mark_Input(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to mark?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("delete")) {
                try {
                    TaskList.delete(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to delete?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("todo")) {
                try {
                    TaskList.todo_Input(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("deadline")) {
                try {
                    TaskList.deadline_Input(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("event")) {
                try {
                    TaskList.event_Input(tasks, TaskDescOrTaskNo);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("find")) {
                try {
                    ArrayList<Task> tasksFound = TaskList.find_Input(tasks, TaskDescOrTaskNo);
                    TaskList.list_Input(tasksFound);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!!  What are you trying to find?");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            readInput = in.nextLine();
            command = Parser.parseType(readInput);
            TaskDescOrTaskNo = Parser.parseTaskDescOrTaskNo(readInput);
        }

        System.out.println("Bye. Hope to see you again!");
    }
}

