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

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static final int USER_INPUT_EXPECTED_SIZE = 2;
    static final String FILE_PATH = "./duke.txt";

    public static void main(String[] args) {
        showWelcomeMessage();

        File F = new File(FILE_PATH);
        if (!F.exists()) {
            F = new File("./duke.txt");
        }

        ArrayList<Task> taskList = new ArrayList<>();

        try {
            loadFileContent(taskList);
        } catch (FileNotFoundException e) {
            println("A save file was not found. I shall create a new one.");
        } catch (IOException e) {
            println("Sorry, I could not read file.");
        }

        Scanner input = new Scanner(System.in);

        while (true) {

            String text = input.nextLine();

            if ("bye".equalsIgnoreCase(text)) {
                try {
                    writeToFile(taskList);
                } catch (IOException e) {
                    System.out.println("Something broke " + e.getMessage());
                }
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

    private static void loadFileContent(ArrayList<Task> taskList) throws IOException{
        File f = new File(FILE_PATH);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] task = line.split("\\|");
            String taskType        = task[0];
            String taskDoneStatus  = task[1];
            String taskDescription = task[2];

            switch (taskType) {
                case "T":
                    Todo new_Todo = new Todo(taskDescription);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_Todo.markDone();
                    }
                    taskList.add(new_Todo);

                    break;

                case "D":
                    String taskBy = task[3];
                    Deadline new_deadline = new Deadline(taskDescription, taskBy);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_deadline.markDone();
                    }

                    taskList.add(new_deadline);
                    break;

                case "E":
                    String[] taskDuration = task[3].split(" ",2);
                    Event new_event = new Event(taskDescription, taskDuration[0], taskDuration[1]);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_event.markDone();
                    }

                    taskList.add(new_event);
                    break;
            }
        }
    }

    /**
     * Writes the task list to a save file
     *
     * @param taskList The taskList to update the save file
     */
    private static void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task taskToAppend : taskList) {
            String toWrite;

            String taskType = taskToAppend.getTypeIcon();
            String taskDone = taskToAppend.getDoneIcon();
            String taskDescription = taskToAppend.getDescription();

            toWrite = taskType + "|" + taskDone + "|" + taskDescription;

            if (taskToAppend.isDeadline()) {
                String taskDeadline = taskToAppend.getBy();
                toWrite = toWrite + "|" + taskDeadline;
            } else if (taskToAppend.isEvent()) {
                String taskFrom = taskToAppend.getFrom();
                String taskTo = taskToAppend.getTo();
                toWrite = toWrite + "|" + taskFrom + " " + taskTo;
            }

            toWrite = toWrite + '\n';
            fw.write(toWrite);
        }
        fw.close();
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
    private static void addTodo(ArrayList<Task> taskList, String[] userInput) throws TodoException {
        Todo new_todo = new Todo(userInput[1]);
        taskList.add(new_todo);

        printAddingOneTask(new_todo, taskList);
    }

    /**
     * Creates a new task, classified as an event task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    private static void addEvent(ArrayList<Task> taskList, String[] userInput) {
        String[] eventDetails = userInput[1].split(" /from | /to ");

        Event new_event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

        taskList.add(new_event);

        printAddingOneTask(new_event, taskList);
    }

    /**
     * Creates a new task, classified as a deadline task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    private static void addDeadline(ArrayList<Task> taskList, String[] userInput) {
        String[] taskDetails = userInput[1].split(" /by ", 2);

        String taskName = taskDetails[0];
        String taskDueDate = taskDetails[1];

        Deadline new_deadline = new Deadline(taskName, taskDueDate);

        taskList.add(new_deadline);

        printAddingOneTask(new_deadline, taskList);
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

        printMarkingOrUnmarkingOneTask(selectedTask);
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

        printMarkingOrUnmarkingOneTask(selectedTask);
    }
}