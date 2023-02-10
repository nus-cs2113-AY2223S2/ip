package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.Task;

import static duke.print.Print.*;

import java.util.Scanner;


public class Duke {
    static final int USER_INPUT_EXPECTED_SIZE = 2;

    public static void main(String[] args) {
        showWelcomeMessage();

        Task[] taskList = new Task[100];
        int taskIndex = 0;

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

                case "deadline":
                    addDeadline(taskList, taskIndex, userInput);

                    taskIndex += 1;
                    break;

                case "event":
                    try {
                        if (userInput.length != USER_INPUT_EXPECTED_SIZE) {
                            throw new EventException();
                        } else {
                            addEvent(taskList, taskIndex, userInput);

                            taskIndex += 1;
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
                            addTodo(taskList, taskIndex, userInput);

                            taskIndex += 1;
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

    /**
     * Creates a new task, classified as a todo task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addTodo(Task[] taskList, int taskIndex, String[] userInput) throws TodoException {
        Todo new_todo = new Todo(userInput[1]);
        taskList[taskIndex] = new_todo;

        printAddingOneTask(new_todo, taskIndex);
    }

    /**
     * Creates a new task, classified as an event task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addEvent(Task[] taskList, int taskIndex, String[] userInput) {
        String[] eventDetails = userInput[1].split("/from | /to");

        Event event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

        taskList[taskIndex] = event;

        printAddingOneTask(event, taskIndex);
    }

    /**
     * Creates a new task, classified as a deadline task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addDeadline(Task[] taskList, int taskIndex, String[] userInput) {
        String[] taskDetails = userInput[1].split(" /by", 2);

        String taskName = taskDetails[0];
        String taskDueDate = taskDetails[1];

        Deadline deadline = new Deadline(taskName, taskDueDate);

        taskList[taskIndex] = deadline;

        printAddingOneTask(deadline, taskIndex);
    }

    /**
     * Sets a specified task as not done
     *
     * @param taskList   The list of tasks that contains the task that needs to be marked as not done
     * @param taskNumber The number of the task to mark as not done
     */
    private static void unmarkSelectedTask(Task[] taskList, String taskNumber) {

        int taskNumberToUnmark = Integer.parseInt(taskNumber);
        taskNumberToUnmark -= 1;

        Task selectedTask = taskList[taskNumberToUnmark];
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
    private static void markSelectedTask(Task[] taskList, String taskNumber) {
        int taskNumberToMark = Integer.parseInt(taskNumber);
        taskNumberToMark -= 1;

        Task selectedTask = taskList[taskNumberToMark];
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