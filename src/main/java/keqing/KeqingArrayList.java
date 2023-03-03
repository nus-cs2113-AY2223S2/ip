package keqing;

import keqing.exceptions.IllegalInputException;
import keqing.tasks.Deadline;
import keqing.tasks.Event;
import keqing.tasks.Task;
import keqing.tasks.ToDo;

import java.util.ArrayList;

import static keqing.Keqing.LINE;
import static keqing.KeqingParser.isNumeric;
import static keqing.tasks.Task.getTaskCount;

public class KeqingArrayList {
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void findTask(String keyword) {
        System.out.println(LINE);
        if (getTaskCount() == 0) {
            System.out.println("The list is empty...!");
        }
        System.out.println("Keqing tried very hard! Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < getTaskCount(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.print((i + 1) + ".");
                System.out.println(tasks.get(i).toString());
                count += 1;
            }
        }
        if (count == 0) {
            System.out.println("Wait...There is no matching task in the list...!");
        }
        System.out.println(LINE);
    }

    /**
     * Prints the current tasks list.
     */
    public static void printTaskList() {
        System.out.println(LINE);
        if (getTaskCount() == 0) {
            System.out.println("The list is empty...!");
        }
        for (int i = 0; i < getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    /**
     * To mark a task as done or unfinished.
     *
     * @param currentID the ID of the task that is currently on
     * @param isDone the checker for the task status
     */
    public static void markTask(int currentID, boolean isDone) {
        System.out.println(LINE);
        if (currentID < 0 || currentID >= getTaskCount()) {
            System.out.println("Cannot find this task!");
        }
        else if (isDone) {
            tasks.get(currentID).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(currentID).toString());
        }
        else {
            tasks.get(currentID).setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(currentID).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Reads the toTo tasks using respective format and adds to the tasks list.
     *
     * @param content Content from the user input
     * @throws IllegalInputException
     */
    public static void addToDo(String content) throws IllegalInputException {
        KeqingUI ui = new KeqingUI();
        if (content.equals("todo")) {
            throw new IllegalInputException("Keqing doesn't understand what you actually want to do...");
        }
        else {
            ToDo toDoTask = new ToDo(content);
            tasks.add(toDoTask);
            ui.echoAdd();
        }
    }

    /**
     * Reads the deadline tasks using respective format and adds to the tasks list.
     *
     * @param content Content from the user input
     * @throws IllegalInputException
     */
    public static void addDeadline(String content) throws IllegalInputException {
        KeqingUI ui = new KeqingUI();
        if (content.contains("/by")) {
            int indexOfBy = content.indexOf("/by");
            if (indexOfBy + 3 < content.length()) {
                String description = content.substring(0, indexOfBy).trim();
                String by = content.substring(indexOfBy + 3).trim();    // 3 is the length of "/by"
                Deadline deadlineTask = new Deadline(description, by);
                tasks.add(deadlineTask);
                ui.echoAdd();
            }
            else {
                throw new IllegalInputException("Keqing doesn't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in a valid deadline.");
        }
    }

    /**
     * Reads the event tasks using respective format and adds to the tasks list.
     *
     * @param content Content from the user input
     * @throws IllegalInputException
     */
    public static void addEvent(String content) throws IllegalInputException {
        KeqingUI ui = new KeqingUI();
        if (content.contains("/from") && content.contains("/to")) {
            int indexOfFrom = content.indexOf("/from");
            int indexOfTo = content.indexOf("/to");
            if (indexOfFrom < indexOfTo) {
                String description = content.substring(0, indexOfFrom).trim();
                String from = content.substring(indexOfFrom + 5, indexOfTo).trim();    // 5 is the length of "/from"
                String to = content.substring(indexOfTo + 3).trim();    // 3 is the length of "/to"
                Event eventTask = new Event(description, from, to);
                tasks.add(eventTask);
                ui.echoAdd();
            }
            else {
                throw new IllegalInputException("Keqing doens't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in the event duration in a valid form...");
        }
    }

    /**
     * To delete a certain task.
     *
     * @param content Content from the user input, would be a number if the user is not trolling
     * @throws IllegalInputException
     */
    public static void deleteTask(String content) throws IllegalInputException {
        KeqingUI ui = new KeqingUI();
        if (content.trim().equals("all")) {
            for (int i = 0; i < tasks.size(); i++) {
                tasks.remove(i);
            }
            Task.setTaskCount(0);
            ui.echoDeleteAll();
        }
        if (isNumeric(content)) {
            int index = Integer.parseInt(content) - 1;    //switch to 0-based.
            if (index <= getTaskCount()) {
                Task.setTaskCount(getTaskCount() - 1);    //decrease the task count
                ui.echoDelete(index);
                tasks.remove(index);
            }
            else {
                throw new IllegalInputException("It's out of bound!!!");
            }
        }
    }
}