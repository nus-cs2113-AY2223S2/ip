/**
 * Command class that executes the command dictated by the user
 */
package duke;
import duke.Tasks.*;

import java.util.ArrayList;

public class Command {

    protected String command;
    protected String details;
    UI ui = new UI();

    public Command(String command, String details) {
        this.command = command;
        this.details = details;
    }

    public String getCommand(String command) {
        return command;
    }

    public String getDetails(String details) {
        return details;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Changes the status of the Task to [X]
     * If Task is not in the taskList, notifies user
     */
    public void marking(TaskList taskList) {
        try {
            int indexToMark = Integer.parseInt(details) - 1;
            if (indexToMark >= 0 && indexToMark < taskList.getList().size()) {
                taskList.getTask(indexToMark).setStatus("[X]");
                ui.printMarked(taskList, indexToMark);
            } else {
                ui.printNotInList();
            }
        } catch (NumberFormatException e) {
            ui.printInputError();
        }
    }

    /**
     * Changes the status of the Task to [ ]
     * If Task is not in the taskList, notifies user
     */
    public void unmarking(TaskList taskList) {
        try {
            int indexToUnmark = Integer.parseInt(details) - 1;
            if (indexToUnmark >= 0 && indexToUnmark < taskList.getList().size()) {
                taskList.getTask(indexToUnmark).setStatus("[ ]");
                ui.printUnmarked(taskList, indexToUnmark);
            } else {
                ui.printNotInList();
            }
        } catch (NumberFormatException e) {
            ui.printInputError();
        }
    }

    /**
     * Deletes the Task based on its index from the list
     * If Task is not in the taskList, notifies user
     */
    public void deleting(TaskList taskList) {
        try {
            int indexToDelete = Integer.parseInt(details) - 1;
            if (indexToDelete >= 0 && indexToDelete < taskList.getList().size()) {
                taskList.getList().remove(indexToDelete);
                ui.printDeleted();
                ui.printEntireList(taskList);
            } else {
                ui.printNotInList();
            }
        } catch (NumberFormatException e) {
            ui.printInputError();
        }
    }

    /**
     * Iterates through list to find all the Tasks with a word named wordToFind
     * If no Tasks contain wordToFind, notifies user
     */
    public void finding(TaskList taskList) {
        String wordToFind = details;
        boolean inList = false;
        for (int i = 0; i < taskList.getList().size(); i++) {
            if (taskList.getTask(i).getDescription().contains(wordToFind)) {
                ui.printTaskWithIndex(taskList, i);
                inList = true;
            }
        }
        if (!inList) {
            ui.printNotInList();
        }
    }

    /**
     * Executes code based on what the command is for the desired output
     */
    public void executeCommand(TaskList taskList) {
        switch (command) {
        case "Todo":
            ToDo toDo = new ToDo("[T]", "[ ]", details);
            taskList.adder(toDo);
            ui.printAdded(taskList, taskList.getIndex(toDo));
            break;
        case "Deadline":
            try {
                Deadline deadline = new Deadline("[D]", "[ ]", details);
                taskList.adder(deadline);
                ui.printAdded(taskList, taskList.getIndex(deadline));
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInputError();
            }
            break;
        case "Event":
            try {
                Event event = new Event("[E]", "[ ]", details);
                taskList.adder(event);
                ui.printAdded(taskList, taskList.getIndex(event));
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInputError();
            }
            break;
        case "List":
            if (taskList.getList().size() == 0) {
                ui.printNoList();
            } else {
                ui.printEntireList(taskList);
            }
            break;
        case "Mark":
            marking(taskList);
            break;
        case "Unmark":
            unmarking(taskList);
            break;
        case "Delete":
            deleting(taskList);
            break;
        case "Find":
            finding(taskList);
            break;
        default:
            break;
        }
    }

}
