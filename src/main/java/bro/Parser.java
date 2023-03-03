package bro;

import bro.exceptions.invalidInputFormat;
import bro.exceptions.invalidTaskIndexException;

import static bro.Ui.printException;
import static bro.Ui.printReply;
import static bro.Ui.printTaskList;
import static bro.Messages.FAREWELL;
import static bro.Messages.TASK_DOES_NOT_EXIST;
import static bro.Messages.INVALID_COMMAND;

public class Parser {
    private boolean haveInput;
    public Parser() {
        this.haveInput = true;
    }

    public boolean haveInput() {
        return haveInput;
    }

    public void parseInput(TaskList taskList, String[] arrayOfInputs) {
        switch (arrayOfInputs[0]) {
        case "bye":
            printReply(FAREWELL);
            haveInput = false;
            break;
        case "list":
            printTaskList(taskList);
            break;
        case "mark": // Fallthrough
        case "unmark":
            boolean markAsComplete = arrayOfInputs[0].equals("mark");   // this boolean decides if the following `markComplete()` marks the task as Completed or Uncompleted
            try {
                taskList.markComplete(markAsComplete, taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            } catch (invalidTaskIndexException e) {
                printException(TASK_DOES_NOT_EXIST);
            }
            Storage.save(taskList);
            break;
        case "todo":
            try {
                taskList.createToDo(taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            }
            Storage.save(taskList);
            break;
        case "deadline":
            try {
                taskList.createDeadline(taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            }
            Storage.save(taskList);
            break;
        case "event":
            try {
                taskList.createEvent(taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            }
            Storage.save(taskList);
            break;
        case "delete":
            try {
                taskList.deleteTask(taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            } catch (invalidTaskIndexException e) {
                printException(TASK_DOES_NOT_EXIST);
            }
            Storage.save(taskList);
            break;
        case "find":
            try {
                taskList.find(taskList, arrayOfInputs);
            } catch (invalidInputFormat e) {
                printException(e);
            }
            break;
        default:
            printReply(INVALID_COMMAND);
        }
    }
}
