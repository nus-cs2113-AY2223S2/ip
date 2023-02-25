package tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import exceptions.EventTimingException;
import common.Messages;
import parser.Parser;


import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Parser parser = new Parser();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public void printTaskList() {
        System.out.println(Messages.PRINT_TASK_HEADING);
        for (int i = 0; i < tasks.size(); i++) {
            int taskIndex = i + 1;
            System.out.println(taskIndex + ". " + tasks.get(i).printTask());
        }
        System.out.println(Messages.LINE);
    }

    public void markTask(String input) throws NumberFormatException {
        int taskIndex = parser.markTaskIndex(input);
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(Messages.LINE + "Task " + taskIndex + " marked as done:\n" +
                    tasks.get(taskIndex - 1).printTask() + '\n' + Messages.LINE);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + Messages.LINE);
        }
    }

    public void unmarkTask(String input) throws NumberFormatException {
        int taskIndex = parser.unmarkTaskIndex(input);
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            tasks.get(taskIndex - 1).markAsUndone();
            System.out.println(Messages.LINE + "Task " + taskIndex + " marked as not done yet:\n" +
                    tasks.get(taskIndex - 1).printTask() + '\n' + Messages.LINE);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + Messages.LINE);
        }
    }

    public void createTodo(String input) throws IndexOutOfBoundsException {
        ToDo todoTask = new ToDo(input.substring(5));
        tasks.add(todoTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + todoTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    public void createDeadline(String input) throws IndexOutOfBoundsException {
        if (!input.contains("/by") || input.indexOf("/by") + 4 > input.length()) {
            throw new StringIndexOutOfBoundsException();
        }
        String deadlineDate = parser.deadlineDate(input);
        Deadline deadlineTask = new Deadline(input.substring(9, input.indexOf("/by")), deadlineDate);
        tasks.add(deadlineTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + deadlineTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    public void createEvent(String input) throws IndexOutOfBoundsException, EventTimingException {
        if (!input.contains("/from") || !input.contains("/to") || input.indexOf("/from") + 6 > input.length()
                || input.indexOf("/to") + 4 > input.length()) {
            throw new StringIndexOutOfBoundsException();
        } else if (input.indexOf("/from") > input.indexOf("/to")) {
            throw new EventTimingException();
        }
        String startTime = parser.eventStartTime(input);
        String endTime = parser.eventEndTime(input);
        Event eventTask = new Event(input.substring(6, input.indexOf("/from")), startTime, endTime);
        tasks.add(eventTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + eventTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    public void deleteTask(String input) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(input.substring(6).trim());
        if (index >= 1 && index <= tasks.size()) {
            System.out.println(Messages.LINE + "I've removed task " + index + ":\n" + "   " + tasks.get(index - 1).printTask());
            tasks.remove(index - 1); // change to 0-base indexing
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
        } else {
            System.out.println("Task " + index + " not found. Please try again.\n" + Messages.LINE);
        }
    }
}
