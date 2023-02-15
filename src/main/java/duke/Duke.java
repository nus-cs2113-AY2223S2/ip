package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;

import duke.exception.DeadlineException;
import duke.exception.EventException;
import duke.exception.KeywordException;
import duke.exception.TodoException;
import duke.task.Task;

import static duke.print.Print.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static final int USER_INPUT_EXPECTED_SIZE = 2;

    public static void main(String[] args) {
        showWelcomeMessage();

        ArrayList<Task> taskList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        while (true) {

            String text = input.nextLine();

            if ("bye".equalsIgnoreCase(text)) {
                break;
            }

            String[] userInput = text.split(" ", 2);

            String keyword = userInput[0];

            switch (keyword) {
                case "list":
                    printTaskList(taskList);
                    break;

                case "mark":
                    String taskNumberToMark = userInput[1];
                    markSelectedTask(taskList, taskNumberToMark);
                    break;

                case "unmark":
                    String taskNumberToUnmark = userInput[1];
                    unmarkSelectedTask(taskList, taskNumberToUnmark);
                    break;

                case "delete":
                    String taskNumber = userInput[1];

                    deleteOneTask(taskList, taskNumber);

                    break;

                case "deadline":
                    try {
                        if (userInput.length != USER_INPUT_EXPECTED_SIZE) {
                            throw new DeadlineException();
                        } else {
                            addDeadline(taskList, userInput);
                        }
                    } catch (DeadlineException e) {
                        continue;
                    }

                    break;

                case "event":
                    try {
                        if (userInput.length != USER_INPUT_EXPECTED_SIZE) {
                            throw new EventException();
                        } else {
                            addEvent(taskList, userInput);
                        }
                    } catch (EventException e) {
                        continue;
                    }

                    break;

                case "todo":
                    try {
                        if (userInput.length != USER_INPUT_EXPECTED_SIZE) {
                            throw new TodoException();
                        } else {
                            addTodo(taskList, userInput);
                        }
                    } catch (TodoException e) {
                        continue;
                    }
                    break;

                default:
                    try {
                        throw new KeywordException();
                    } catch (KeywordException e) {
                        break;
                    }
            }
        }

        showExitMessage();
    }

    private static void deleteOneTask(ArrayList<Task> taskList, String taskNumber) {
        int taskNumberToDelete = Integer.parseInt(taskNumber);
        taskNumberToDelete -= 1;

        Task selectedTask = taskList.get(taskNumberToDelete);
        taskList.remove(taskNumberToDelete);

        printDeletingOneTask(taskList, selectedTask);
    }

    /**
     * Creates a new task, classified as a todo task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    private static void addTodo(ArrayList<Task> taskList, String[] userInput) {
        Todo new_todo = new Todo(userInput[1]);
        taskList.add(new_todo);

        printAddingOneTask(taskList, new_todo);
    }

    /**
     * Creates a new task, classified as an event task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    private static void addEvent(ArrayList<Task> taskList, String[] userInput) {
        String[] eventDetails = userInput[1].split("/from | /to");

        Event new_event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

        taskList.add(new_event);

        printAddingOneTask(taskList, new_event);
    }

    /**
     * Creates a new task, classified as a deadline task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    private static void addDeadline(ArrayList<Task> taskList, String[] userInput) {
        String[] taskDetails = userInput[1].split(" /by", 2);

        String taskName = taskDetails[0];
        String taskDueDate = taskDetails[1];

        Deadline new_deadline = new Deadline(taskName, taskDueDate);

        taskList.add(new_deadline);

        printAddingOneTask(taskList, new_deadline);
    }

    /**
     * Sets a specified task as not done
     *
     * @param taskList   The list of tasks that contains the task that needs to be marked as not done
     * @param taskNumber The number of the task to mark as not done
     */
    private static void unmarkSelectedTask(ArrayList<Task> taskList, String taskNumber) {

        int taskNumberToUnmark = Integer.parseInt(taskNumber);
        taskNumberToUnmark -= 1;

        Task selectedTask = taskList.get(taskNumberToUnmark);
        selectedTask.markNotDone();

        printOneLine();
        println("     OK, I've marked this task as not done yet:");

        printTypeAndStatus(selectedTask);
        print(selectedTask.getDescription());

        switch (selectedTask.getTypeIcon()) {
            case "D":
                println(" (by:" + selectedTask.getBy() + ")");
                break;

            case "E":
                println("(from: " + selectedTask.getFrom() + " to:" + selectedTask.getTo() + ")");
                break;

            default:
                println("");
        }

        printOneLine();
    }

    /**
     * Sets a specified task as done
     *
     * @param taskList   The list of tasks that contains the task that needs to be marked as done
     * @param taskNumber The number of the task to mark as done
     */
    private static void markSelectedTask(ArrayList<Task> taskList, String taskNumber) {
        int taskNumberToMark = Integer.parseInt(taskNumber);
        taskNumberToMark -= 1;

        Task selectedTask = taskList.get(taskNumberToMark);
        selectedTask.markDone();

        printOneLine();
        println("     Nice! I've marked this task as done:");

        printTypeAndStatus(selectedTask);
        print(selectedTask.getDescription());

        switch (selectedTask.getTypeIcon()) {
            case "D":
                println(" (by:" + selectedTask.getBy() + ")");
                break;

            case "E":
                println("(from: " + selectedTask.getFrom() + " to:" + selectedTask.getTo() + ")");
                break;

            default:
                println("");
        }

        printOneLine();
    }
}