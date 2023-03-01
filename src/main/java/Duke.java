import exceptions.EventTimingException;
import exceptions.TaskTypeException;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;
import ui.Ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Duke extends TaskList {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;


    /**
     * Constructor for Duke with file path specified. Initialise tasks array list from storage.
     *
     * @param filePath path for the txt file that stores the task list
     */
    public Duke(String filePath) {
        super();
        try {
            ui = new Ui();
            initialiseStorage(filePath);
            taskList = new TaskList(storage.getTasks());
            parser = new Parser();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Create file if not found before reading in existing tasks to store in an array list.
     *
     * @param filePath path for the txt file that stores the task list
     * @throws FileNotFoundException is no file containing the task list is found
     */
    private void initialiseStorage(String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        storage.createFile();
        storage.readTasksFromFile();
    }

    /**
     * Save modified task list to the task_list txt file.
     */
    private void saveTasks() {
        try {
            storage.saveTasksToFile();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        } catch (NullPointerException e) {
            System.out.println("No task found.");
        }
    }

    /**
     * Launch and run Duke.
     */
    private void run() {
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        loop:
        while (true) {
            String userInput = "";
            if (in.hasNextLine()) {
                userInput = in.nextLine().trim();
            } else {
                break;
            }
            try {
                switch (parser.keyWord(userInput)) {
                case "bye":
                    ui.showByeMessage();
                    break loop;
                case "list":
                    taskList.printTaskList();
                    break;
                case "mark":
                    taskList.markTask(userInput);
                    break;
                case "unmark":
                    taskList.unmarkTask(userInput);
                    break;
                case "todo":
                    taskList.createTodo(userInput);
                    break;
                case "deadline":
                    taskList.createDeadline(userInput);
                    break;
                case "event":
                    taskList.createEvent(userInput);
                    break;
                case "delete":
                    taskList.deleteTask(userInput);
                    break;
                case "find":
                    taskList.findTask(userInput);
                    break;
                case "help":
                    try (BufferedReader br = new BufferedReader(new FileReader("./foo.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                e.printError();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (EventTimingException e) {
                e.printError();
            } catch (IndexOutOfBoundsException | IOException e) {
                ui.showInsufficientInputError();
            }
            saveTasks();
        }
    }

    public static void main(String[] args) {
        new Duke("./task_list.txt").run();
    }
}

