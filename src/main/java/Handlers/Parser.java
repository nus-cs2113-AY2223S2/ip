package Handlers;

import Exceptions.InvalidTypeException;
import Exceptions.TaskManagerException;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public class Parser {

    public static String getFirstWord(String s) {
        String[] words = s.split(" ");
        return words[0];
    }

    public static String getSecondWord(String s) throws TaskManagerException {
        String[] words = s.split("\\s+");
        if (words.length < 2) {
            throw new TaskManagerException();
        }
        int index = s.indexOf(" ");
        String sub = s.substring(index + 1);
        return sub;
    }

    public static int getTaskNumber(String line) {
        int index = 0;
        try {
            // is this the right way to check if the task number is valid?
            index = Integer.parseInt(getSecondWord(line));
            isTaskNumberValid(index);
        } catch (TaskManagerException e) {
            System.out.println("task number must be stated.");
        } catch (NumberFormatException e) {
            System.out.println("task number must be a number.");
        } catch (InvalidTypeException e) {
            System.out.println("task number does not exist.");
        }
        return index;
    }

    public static void isTaskNumberValid(int taskNumber) throws InvalidTypeException {
        if (taskNumber < 1 || taskNumber > TaskManager.getTaskCount()) {
            throw new InvalidTypeException();
        }
    }

    public static void getTodoDetails(String line) {
        String todoLine = "";
        try {
            todoLine = getSecondWord(line);
            Todo todoTask = new Todo(todoLine);
            TaskManager.addTask(todoTask);
            System.out.println("Got it. I've added this task:\n" + todoTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for todo cannot be empty.");
        }
    }

    public static void getDeadlineDetails(String line) {
        String deadlineLine = "";
        try {
            deadlineLine = getSecondWord(line);
            int index = deadlineLine.indexOf("/");
            String deadlineDescription = deadlineLine.substring(0, index - 1);
            String deadlineDate = deadlineLine.substring(index + 4);
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
            TaskManager.addTask(deadlineTask);
            System.out.println("Got it. I've added this task:\n" + deadlineTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for deadline cannot be empty.");
        }
    }

    public static void getEventDetails(String line) {
        String eventLine = "";
        try {
            eventLine = getSecondWord(line);
            int index2 = eventLine.indexOf("/");
            String eventDescription = eventLine.substring(0, index2 - 1);
            String eventDate = eventLine.substring(index2 + 4);
            int index3 = eventDate.indexOf("/");
            String eventFrom = eventDate.substring(2, index3 - 1);
            String eventTo = eventDate.substring(index3 + 4);
            Event eventTask = new Event(eventDescription, eventFrom, eventTo);
            TaskManager.addTask(eventTask);
            System.out.println("Got it. I've added this task:\n" + eventTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for event cannot be empty.");
        }
    }

}
