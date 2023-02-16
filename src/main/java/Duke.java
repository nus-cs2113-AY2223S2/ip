import task.Deadline;
import task.ToDo;
import task.DukeException;
import task.Event;
import task.Task;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static final String WRONG_INPUTS_GIVEN = "Wrong inputs given";
    private static final String LINE = "____________________________________________________________";
    private static final String UNRECOGNISED_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String UNRECOGNISED_ITEM_INDEX = "☹ OOPS!!! unrecognised item index!";
    public static final String EMPTY_DESCRIPTION = "☹ OOPS!!! The description cannot be empty.";
    public static final String FILE_PATH = "data.txt";
    public static final String FILE_ACCESS_ERROR = "File access failed!";
    public static final String FILE_UPDATING_ERROR = "File update failed!";
    private static ArrayList<Task> list = new ArrayList<Task>();
    public static void main(String[] args) {
        populateSavedTasks();
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
                processError(e);
            }
        }
    }

    private static void processError(Exception e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
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

    public static void populateSavedTasks() {
        try {
            File savedFile = new File(FILE_PATH);
            if(savedFile.createNewFile()) {
                return;
            }
            Scanner data = new Scanner(savedFile);
            int itemIndex = 0;
            while(data.hasNext()) {
                String input = data.nextLine();
                String[] tokens = input.split("-");
                String typeOfTask = tokens[0];
                String status = tokens[1];
                String task = tokens[2];
                switch(typeOfTask) {
                case "T":
                    ToDo existingTodo = new ToDo(task);
                    existingTodo.setType("T");
                    list.add(existingTodo);
                    break;
                case "D":
                    String deadline = tokens[3];
                    Deadline existingDeadline = new Deadline(task, deadline);
                    existingDeadline.setType("D");
                    list.add(existingDeadline);
                    break;
                case "E":
                    String from;
                    String to;
                    if(tokens[3].contains(" ")) {
                        from = tokens[3].split("\\s+", 2)[1];
                    } else {
                        int idxFrom = tokens[3].indexOf("from") + 4;
                        from = tokens[3].substring(idxFrom);
                    }
                    if(tokens[4].contains(" ")) {
                        to = tokens[4].split("\\s+", 2)[1];
                    } else {
                        int idxTo = tokens[4].indexOf("to") + 2;
                        to = tokens[4].substring(idxTo);
                    }
                    Event existingEvent = new Event(task, from, to);
                    existingEvent.setType("E");
                    list.add(existingEvent);
                    break;
                }
                if(status.equals("X")) {
                    list.get(itemIndex).markDone();
                }
                itemIndex++;
            }
        } catch (IOException e) {
            System.out.println(FILE_ACCESS_ERROR);
        }

    }

    public static void processInput(String input) throws Exception {
        String inst = input.split("\\s+")[0];
        if (input.equalsIgnoreCase("list")) {
            printList();
            return;
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
        updateFileData();
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
        newTodoTask.setType("T");
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
        newDeadline.setType("D");
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
        String from = instruction[1];
        String to = instruction[2];
        String dateFrom = from.split("\\s+", 2)[1];
        String dateTo = to.substring(to.lastIndexOf("to") + 2);
        Event newEvent = new Event(task, dateFrom, dateTo);
        newEvent.setType("E");
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


    private static void updateFileData() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : list) {
                String typeOfTask = t.getType();
                String status = t.getStatus();
                String task = t.getTask();
                String taskDescription;
                switch (typeOfTask) {
                case "T":
                    taskDescription = String.format("%s-%s-%s", typeOfTask, status, task);
                    break;
                case "D":
                    Deadline deadlineTask = (Deadline) t;
                    String deadline = deadlineTask.getDeadline();
                    taskDescription = String.format("%s-%s-%s-%s", typeOfTask, status, task, deadline);
                    break;
                case "E":
                    Event eventTask = (Event) t;
                    String from = eventTask.getFrom();
                    String to = eventTask.getTo();
                    taskDescription = String.format("%s-%s-%s-from %s-to%s", typeOfTask, status, task, from, to);
                    break;
                default:
                    taskDescription = "";
                    System.out.println(FILE_UPDATING_ERROR);
                }
                fw.write(taskDescription + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(FILE_UPDATING_ERROR);
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
