
// import java.util.Arrays;
import java.util.Scanner;

import Exceptions.InvalidTypeException;
import Exceptions.TaskManagerException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.TaskManager;
import Tasks.Todo;

public class DukeRunner {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        boolean isComplete = false;

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = getFirstWord(line);
            Task[] taskList = TaskManager.getTaskList();

            switch (firstWord) {
            case "mark":
                int taskNumberMark = getTaskNumber(line);
                TaskManager.markTask(taskList, taskNumberMark);
                break;
            case "unmark":
                int taskNumberUnmark = getTaskNumber(line);
                TaskManager.unmarkTask(taskList, taskNumberUnmark);
                break;
            case "list":
                TaskManager.listTask();
                break;
            case "todo":
                getTodoDetails(line);
                break;
            case "deadline":
                getDeadlineDetails(line);
                break;
            case "event":
                getEventDetails(line);
                break;
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

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

    private static int getTaskNumber(String line) {
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

    private static void isTaskNumberValid(int taskNumber) throws InvalidTypeException {
        if (taskNumber < 1 || taskNumber > TaskManager.getTaskCount()) {
            throw new InvalidTypeException();
        }
    }

    private static void getTodoDetails(String line) {
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

    private static void getDeadlineDetails(String line) {
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

    private static void getEventDetails(String line) {
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
