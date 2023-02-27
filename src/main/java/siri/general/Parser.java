package siri.general;

import siri.exception.AddTaskIndexOutOfBounds;
import siri.exception.MarkerArrayIndexOutOfBoundsException;
import siri.exception.UnknownCommandException;

import java.io.IOException;

import static siri.Duke.storage;
import static siri.Duke.tasks;
import static siri.Duke.indexOfTask;
import static siri.Duke.isExit;

/**
 * Parse the user command into different parts.
 */
public class Parser {
    protected String userInput;
    protected Ui ui;
    public Parser(String command){
        this.userInput = command;
        this.ui = new Ui();
    }

    /**
     * Parse the user input into task command and command description.
     *
     * @throws MarkerArrayIndexOutOfBoundsException
     * @throws AddTaskIndexOutOfBounds
     * @throws UnknownCommandException
     * @throws IOException
     */
    public void parse() throws MarkerArrayIndexOutOfBoundsException, AddTaskIndexOutOfBounds, UnknownCommandException, IOException {
        String[] command = userInput.split(" ", 2);
        switch (command[0]) {
        case "bye":
            isExit = true;
            ui.sayGoodbye();
            break;
        case "list":
            tasks.printTaskList();
            break;
        case "mark":
        case "unmark":
            if (command.length == 1) {
                throw new MarkerArrayIndexOutOfBoundsException();
            }
            int taskNumber_m = Integer.parseInt(command[1]);
            tasks.markTask(command[0], taskNumber_m);
            break;
        case "delete":
            int taskNumber_d = Integer.parseInt(command[1]);
            tasks.deleteTask(taskNumber_d);
            break;
        case "todo":
        case "deadline":
        case "event":
            if (command.length == 1) {
                throw new AddTaskIndexOutOfBounds(command[0]);
            }
            tasks.addTask(command[0], command[1]);
            storage.appendToFile(tasks.getTaskList().get(indexOfTask).toFileString());
            tasks.printNewTask();
            indexOfTask++;
            break;
        case "find":
            tasks.findTask(command[1]);
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}