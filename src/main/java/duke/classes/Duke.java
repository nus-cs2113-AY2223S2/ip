package duke.classes;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents the main class of the Duke program, which is a simple task manager.
 * Duke stores a list of tasks that can be added, marked as done, and deleted.
 * Tasks can be of three types: to-dos, deadlines, and events.
 */
public class Duke {

    /**
     * Checks if the given input is valid, and throws a DukeException if it is not.
     * The input is invalid if it is an empty string, or if it is not one of the three keywords:
     * "event", "todo", or "deadline".
     *
     * @param input The user input to be checked.
     * @throws DukeException If the input is not valid.
     */
    private static void checkError(String input) throws DukeException {
        if (Objects.equals(input, "event") || Objects.equals(input, "todo") || Objects.equals(input, "deadline")) {
            throw new DukeException("The description of the body cannot be empty! Please enter a proper input.");
        } else if (Objects.equals(input, "")) {
            throw new DukeException("You did not enter any input! Please enter a proper input.");
        } else {
            throw new DukeException("I'm sorry, but i don't know what that means. Please enter a proper input.");
        }
    }

    /** A list of tasks that the user has added to the program */
    private static ArrayList<Task> listOfTask = new ArrayList<Task>();

    /** A Tasklist object that contains the list of tasks */
    static Tasklist tasklist = new Tasklist(listOfTask);

    /**
     * Reads the list of tasks from a file and initializes the listOfTask and tasklist objects.
     *
     * @param filepath The path to the file to be read.
     * @param listOfTask An empty list of tasks to be populated with tasks from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public static void foundationList(String filepath, ArrayList<Task> listOfTask) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String temp = scan.nextLine();
            String type = temp.substring(1,2);
            String status = temp.substring(6,7);
            if (type.equals("T")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                tasklist.addTask(task);
            } else if (type.equals("D")) {
                String info = temp.substring(7, temp.indexOf("("));
                String timeBy = temp.substring(temp.indexOf("(")+1, temp.length() - 1);
                Deadline task = new Deadline(info, timeBy);
                if (status.equals("X")) {
                    tasklist.markTask(task);
                } else {
                    tasklist.unmarkTask(task);
                }
                tasklist.addTask(task);
            } else if (type.equals("E")) {
                String info = temp.substring(7,temp.indexOf("("));
                String timeFrom = temp.substring(temp.indexOf("(")+1, temp.lastIndexOf(","));
                String timeBy = temp.substring(temp.lastIndexOf(",")+1, temp.length() - 1);
                Event task = new Event(info, timeFrom, timeBy);
                if (status.equals("X")) {
                    tasklist.markTask(task);
                } else {
                    tasklist.unmarkTask(task);
                }
                tasklist.addTask(task);
            }
        }
    }

    /**
     * The main method of the Duke program.
     *
     * @param args The command line arguments.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {

        Ui ui = new Ui();
        ui.showWelcome();

        try {
            File file = new File("duke_list.txt");
            if (file.createNewFile()) {
                ui.showFileCreated();
            } else {
                ui.showFileExists();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        String filePath = "duke_list.txt";
        foundationList(filePath, listOfTask);
        Storage storage = new Storage(filePath, listOfTask);

        for (int i = 0; i < listOfTask.size(); i++) {
            count++;
        }

        try {
            ui.showFileContent();
            storage.printFile("duke_list.txt");
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
        ui.showWelcomeEnd();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        boolean isBye = false;
        while (!isBye) {
            Parser parser = new Parser(input);

            if (Objects.equals(parser.getInputType(), "bye")) {
                isBye = true;
                storage.updateFile(filePath, listOfTask);
                break;
            } else if (Objects.equals(parser.getInputType(), "list")) {
                ui.showTasksMessage();
                tasklist.printTasks();

            } else if (Objects.equals(parser.getInputType(), "mark")) {
                    if (parser.getOrderMark(input) - 1 >= count) {
                        ui.showMarkTaskWarning();
                    } else {
                        Task task = listOfTask.get(parser.getOrderMark(input) - 1);
                        InputUi inputUi = new InputUi(task, count);
                        tasklist.markTask(task);
                        listOfTask.set(parser.getOrderMark(input) - 1, task);
                        storage.updateFile(filePath, listOfTask);
                        inputUi.showMarkedTask();
                    }
            } else if (Objects.equals(parser.getInputType(), "unmark")) {
                    if (parser.getOrderUnmark(input) - 1 >= count) {
                        ui.showUnmarkTaskWarning();
                    } else {
                        Task task = listOfTask.get(parser.getOrderUnmark(input) - 1);
                        InputUi inputUi = new InputUi(task, count);
                        tasklist.unmarkTask(task);
                        listOfTask.set(parser.getOrderUnmark(input) - 1, task);
                        storage.updateFile(filePath, listOfTask);
                        inputUi.showUnmarkedTask();
                    }
            } else if (Objects.equals(parser.getInputType(), "delete")) {
                if (parser.getOrderDelete(input) - 1 >= count) {
                    ui.showDeleteTaskWarning();
                } else {
                    InputUi inputUi = new InputUi(listOfTask.get(parser.getOrderDelete(input) - 1), count - 1);
                    inputUi.showDeletedTask();
                    listOfTask.remove(parser.getOrderDelete(input) - 1);
                    storage.updateFile(filePath, listOfTask);
                    count--;
                    inputUi.showRemainingTasks();
                }
            } else if (Objects.equals(parser.getInputType(), "find")) {
                String findInfo = parser.getFindKeyWord(input);
                tasklist.printFoundTasks(findInfo);
            } else {
                if (Objects.equals(parser.getInputType(), "todo")) {
                    Todo task = new Todo(parser.getTodoInfo(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    storage.updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else if (Objects.equals(parser.getInputType(), "deadline")) {
                    Deadline task = new Deadline(parser.getDeadlineInfo(input), parser.getDeadlineTimeBy(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    storage.updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else if (Objects.equals(parser.getInputType(), "event")) {
                    Event task = new Event(parser.getEventInfo(input), parser.getEventTimeFrom(input), parser.getEventTimeBy(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    storage.updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else {
                    try {
                        checkError(input);
                    } catch(DukeException e) {
                        System.out.println("Error: " + e);
                    }
                }
            }
            input = scan.nextLine();
        }
        ui.showFarewell();
    }
}
