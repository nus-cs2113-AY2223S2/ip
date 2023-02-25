import exceptions.EventTimingException;
import exceptions.TaskTypeException;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

//TODO: specify error: empty description vs event/ddl timing not found
//TODO: check print new line for printtask

public class Duke extends TaskList {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;


    public Duke(String filePath) {
        super();
        try {
            ui = new Ui();
            initialiseStorage(filePath);
            taskList = new TaskList(storage.initialiseTaskList());
            parser = new Parser();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private void initialiseStorage(String filePath) throws FileNotFoundException {
        storage = new Storage(filePath);
        storage.createFile();
        storage.readTasksFromFile();
    }

    private void saveTasks() {
        try {
            storage.saveTasksToFile();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        } catch (NullPointerException e) {
            System.out.println("No task found.");
        }
    }

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
                default:
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                e.printError();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } catch (EventTimingException e) {
                e.printError();
            } catch (IndexOutOfBoundsException e) {
                ui.showInsufficientInputError();
            }
            saveTasks();
        }
    }


    public static void main(String[] args) {
        new Duke("./task_list.txt").run();
    }

}

