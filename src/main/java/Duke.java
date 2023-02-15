import task.Deadline;
import task.ToDo;
import task.DukeException;
import task.Event;
import task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String WRONG_INPUTS_GIVEN = "Wrong inputs given";
    private static final String LINE = "____________________________________________________________";
    private static final String UNRECOGNISED_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String UNRECOGNISED_ITEM_INDEX = "☹ OOPS!!! unrecognised item index!";
    public static final String EMPTY_DESCRIPTION = "☹ OOPS!!! The description cannot be empty.";
    private static ArrayList<Task> list = new ArrayList<Task>();
    public static void main(String[] args) {
        greet();
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            try {
                processInput(input);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }

        }
    }

    public static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void processInput(String input) throws Exception {
        String inst = input.split("\\s+")[0];
        if (input.equalsIgnoreCase("list")) {
            printList();
        } else if (inst.equalsIgnoreCase("mark")) {
            markTask(input);
        } else if (inst.equalsIgnoreCase("unmark")) {
            unmarkTask(input);
        } else if (inst.equalsIgnoreCase("todo")) {
            todoTask(input);
        } else if(inst.equalsIgnoreCase("deadline")) {
            deadlineTask(input);
        } else if(inst.equalsIgnoreCase("event")) {
            eventTask(input);
        } else if(inst.equalsIgnoreCase("delete")) {
            deleteTask(input);
        } else {
            throw new DukeException(UNRECOGNISED_INPUT);
        }
    }

    private static void printList() {
        if(list.size() == 0) {
            System.out.println(LINE);
            System.out.println("Nothing on your list yet!");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i += 1) {
                System.out.println(Integer.toString(i+1) + "." + list.get(i));
            }
            System.out.println(LINE);
        }
    }

    private static void markTask(String input) throws Exception {
        try {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            list.get(index).markDone();
            markResponse(index);
        } catch (Exception e) {
            throw new Exception(UNRECOGNISED_ITEM_INDEX);
        }
    }

    private static void markResponse(int index) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
        System.out.println(LINE);
    }

    private static void unmarkTask(String input) throws Exception {
        try {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            list.get(index).unmarkDone();
            unmarkResponse(index);
        } catch (Exception e) {
            throw new Exception(UNRECOGNISED_ITEM_INDEX);
        }
    }
    private static void unmarkResponse(int index) {
        System.out.println(LINE);
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(list.get(index));
        System.out.println(LINE);
    }

    private static void todoTask(String input) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        if(tokens.length < 2) {
            throw new DukeException(EMPTY_DESCRIPTION);
        }
        String task = tokens[1];
        ToDo newTodoTask = new ToDo(task);
        list.add(newTodoTask);
        printConfirmation(newTodoTask, "add");
    }

    private static void deadlineTask(String input) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        String[] instruction = tokens[1].split("/");
        if(tokens.length < 2) {
            throw new DukeException(EMPTY_DESCRIPTION);
        }
        if(instruction.length < 2) {
            throw new DukeException(WRONG_INPUTS_GIVEN);
        }
        String task = instruction[0];
        String deadline = instruction[1];
        Deadline newDeadline = new Deadline(task, deadline);
        list.add(newDeadline);
        printConfirmation(newDeadline, "add");
    }

    private static void eventTask(String input) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        String[] instruction = tokens[1].split("/");
        if(tokens.length < 2) {
            throw new DukeException(EMPTY_DESCRIPTION);
        }
        if(instruction.length < 3) {
            throw new DukeException(WRONG_INPUTS_GIVEN);
        }
        String task = instruction[0];
        String dateFrom = instruction[1];
        String dateTo = instruction[2];
        Event newEvent = new Event(task, dateFrom, dateTo);
        list.add(newEvent);
        printConfirmation(newEvent, "add");
    }

    private static void deleteTask(String input) throws Exception {
        String[] tokens = input.split("\\s+", 2);
        if(tokens.length < 2) {
            throw new DukeException(WRONG_INPUTS_GIVEN);
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            Task taskToRemove = list.get(index);
            list.remove(index);
            printConfirmation(taskToRemove, "delete");
        } catch (Exception e) {
            throw new Exception(UNRECOGNISED_ITEM_INDEX);
        }
    }

    private static void printConfirmation(Task newTask, String action) {
        System.out.println(LINE);
        switch(action) {
        case "add":
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            break;

        case "delete":
            System.out.println("Noted. I've removed this task:");
            System.out.println(newTask);
            break;
        }
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(LINE);
    }
}
