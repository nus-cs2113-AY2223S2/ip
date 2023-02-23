package siri.general;

import siri.exception.AddTaskIndexOutOfBounds;
import siri.exception.MarkerArrayIndexOutOfBoundsException;
import siri.exception.UnknownCommandException;

import java.io.IOException;

import static siri.Duke.storage;
import static siri.Duke.tasks;
import static siri.Duke.indexOfTask;
import static siri.Duke.TASK_FILE;
import static siri.Duke.isExit;

public class Parser {

    protected String userInput;
    protected Ui ui;
    public Parser(String command){
        this.userInput = command;
        this.ui = new Ui();
    }

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
            tasks.markTask(command[0], command[1]);
            break;
        case "delete":
            tasks.deleteTask(command[1]);
            break;
        case "todo":
        case "deadline":
        case "event":
            if (command.length == 1) {
                throw new AddTaskIndexOutOfBounds(command[0]);
            }
            tasks.addTask(command[0], command[1]);
            storage.appendToFile(TASK_FILE, tasks.getTaskList().get(indexOfTask).toFileString());
            tasks.printNewTask();
            indexOfTask++;
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
