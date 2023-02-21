package duke;

import duke.exception.DeadlineException;
import duke.exception.EventException;
import duke.exception.KeywordException;
import duke.exception.TodoException;
import duke.task.Task;

import static duke.commands.Commands.*;
import static duke.print.Print.*;

import static duke.file.File.loadFileContent;
import static duke.file.File.writeToFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main driver code
 */
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
                    printFullTaskList(taskList);
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

                case "find":
                    String toFind = userInput[1];

                    findTasks(taskList, toFind);

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
}