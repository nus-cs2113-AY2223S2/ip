import java.nio.file.Path;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
public class Ui {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int OFFSET_ONE_FOR_ZERO_INDEXING = 1;
    final static String DOUBLE_SPACING = "  ";
    final static String FILE_PATH = "data/duke.txt";
    final static String DIRECTORY_PATH = "data";

    public boolean executeUserCommand(TaskList taskList, String userInput) {
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
        String userCommandKeyword = userInputWords[ZERO_INDEX];
        boolean toContinue = true;
        try {
            switch (userCommandKeyword) {
            case "list":
                userCommandList(taskList);
                break;
            case "bye":
                userCommandBye(taskList);
                toContinue = false;
                break;
            case "mark":
                userCommandMark(taskList, userInputWords);
                break;
            case "unmark":
                userCommandUnmark(taskList, userInputWords);
                break;
            case "todo":
                userCommandTodo(taskList, userCommandKeyword, userInput);
                break;
            case "deadline":
                userCommandDeadline(taskList, userCommandKeyword, userInput);
                break;
            case "event":
                userCommandEvent(taskList, userCommandKeyword, userInput);
                break;
            case "delete":
                userCommandDelete(taskList, userInputWords);
                break;
            default:
                userCommandDefault();
                break;
            }
        } catch (DukeException e) {
            System.out.println("Error Found see message above!!!");
        } catch (IOException e) {
            System.out.println("IO EXCEPTION, Data might be lost");
        }
        return toContinue;
    }

    private static void userCommandEvent(TaskList taskList, String userCommand, String userInput) throws DukeException {
        Event newEventTask = getNewEventTask(userInput, userCommand);
        taskList.addUserTask(newEventTask);
        printAddedNewTask(taskList);
    }

    private static Event getNewEventTask(String userInput, String userCommand) throws DukeException {
        String taskString = Parser.getTaskString(userInput, userCommand);
        String taskName = Parser.getEventTaskName(taskString);
        String eventFromDate = Parser.getEventFromDate(taskString);
        String eventToDate = Parser.getEventToDate(taskString);
        Event newEventTask = new Event(taskName, eventFromDate, eventToDate);
        return newEventTask;
    }

    private static void userCommandDeadline(TaskList taskList, String userCommand, String userInput) throws DukeException {
        Deadline newDeadlineTask = getNewDeadlineTask(userInput, userCommand);
        taskList.addUserTask(newDeadlineTask);
        printAddedNewTask(taskList);
    }

    private static Deadline getNewDeadlineTask(String userInput, String userCommand) throws DukeException {
        String taskString = Parser.getTaskString(userInput, userCommand);
        String taskName = Parser.getDeadlineTaskName(taskString);
        String deadlineDueDate = Parser.getDeadlineDueDateString(taskString);
        Deadline newDeadlineTask = new Deadline(taskName, deadlineDueDate);
        return newDeadlineTask;
    }

    private static void userCommandTodo(TaskList taskList, String userCommand, String userInput) throws DukeException {
        Todo newToDoTask = getNewTodoTask(userInput, userCommand);
        taskList.addUserTask(newToDoTask);
        printAddedNewTask(taskList);
    }

    private static Todo getNewTodoTask (String userInput, String userCommand) throws DukeException{
        String taskString = Parser.getTaskString(userInput, userCommand);
        String taskName = Parser.getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    private static void userCommandUnmark(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        taskList.setTaskNotDone(taskIndex);
        System.out.println(taskList.getUserTask(taskIndex));
        System.out.println("OK, I've marked this task as not done yet:");
    }

    private static void userCommandMark(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        taskList.setTaskDone(taskIndex);
        System.out.println(taskList.getUserTask(taskIndex));
        System.out.println("Nice! I've marked this task as done:");
    }

    private static void userCommandList(TaskList taskList) {
        ArrayList<Task> userTasks = taskList.getUserTasksArrayList();
        for(int i = 0; i < userTasks.size(); i++) {
            if (userTasks.get(i).getisDone()) {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            } else {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            }
        }
    }

    private static void userCommandBye(TaskList taskList) throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        Storage.saveData(DIRECTORY_PATH, FILE_PATH, taskList);
    }

    private static void userCommandDefault() throws DukeException {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        throw new DukeException();
    }

    private static void userCommandDelete(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(DOUBLE_SPACING + taskList.getUserTask(taskIndex));
        taskList.removeUserTask(taskIndex);
        System.out.println("Now you have " + taskList.getNumberOfUserTasks() + " in the list.");
    }

    private static void printAddedNewTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
        int numOfUserTasks = taskList.getNumberOfUserTasks();
        System.out.println(taskList.getUserTask(numOfUserTasks- OFFSET_ONE_FOR_ZERO_INDEXING));
        System.out.println("Now you have " + numOfUserTasks + " in the list.");
    }

    public static void executeListCommand(TaskList taskList) {
        userCommandList(taskList);
    }
}
