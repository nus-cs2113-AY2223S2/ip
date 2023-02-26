package duke.tasklist;

import duke.parser.Parser;
import duke.storage.FileManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.DukeMessages;
import duke.util.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Represents all the data in the application.
 * Works with all the functions including <code>find</code> and <code>date</code> as those functions only calls a
 * reference number id which is within this object.
 * All tasks have a number id depending on the order of insertion.
 * Call messages from text UI depending on function.
 */
public class TaskData {
    private final HashMap<Integer, Task> tasks;
    private final DukeMessages ui;
    private final Parser parser;
    private final String path;
    private int taskCount = 0;

    /**
     * Initializes data for application.
     * Contains parser and text UI used for user interaction.
     *
     * @param ui The text UI for the application.
     * @param parser Parses user input and returns application readable code.
     * @param path Path to datafile which contains the local database for the application.
     */
    public TaskData(DukeMessages ui, Parser parser, String path) {
        this.tasks = new HashMap<>();
        this.ui = ui;
        this.parser = parser;
        this.path = path;
    }

    /**
     * For finding the number of task objects contained inside this object.
     * Corresponds to the number id of the last inserted task object.
     *
     * @return Returns the number of task objects contained in this object.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Prints all task objects found when function <code>find</code> or <code>date</code> is called.
     *
     * @param list List of number id of task objects to print.
     */
    public void printFromList(LinkedHashSet<Integer> list) {
        if (list.size() == 0) {
            ui.printNotFound();
        }
        for (int i : list) {
            System.out.println(i + "." + tasks.get(i));
        }
        ui.printFound(list.size());
    }

    /**
     * Prints all task objects.
     * Prints a message depending on the size of number of task objects in total.
     */
    public void listOut() {
        if (taskCount == 0) {
            ui.printEmpty();
            return;
        } else {
            ui.printList();
        }
        for (int i = 1; i <= taskCount; ++i) {
            System.out.println(i + "." + tasks.get(i));
        }
        ui.printListSize(taskCount);
    }

    /**
     * Overwrites the entire datafile with the task objects currently in this object.
     *
     * @param path Path to the datafile.
     */
    public void rewriteFile(String path) {
        try {
            FileManager.writeFile(path, tasks);
        } catch (IOException e) {
            ui.printWriteFileError();
        }
    }

    /**
     * Checks if the task object needs change mark/unmark status or not.
     *
     * @param index The number id of the concerned object task.
     * @param changeTo Whether mark/unmark is called.
     * @param type Mark/Unmark.
     * @throws DukeException Throws exception when the status of the task involved is already the same as the called
     * mark/unmark function.
     */
    public void checkMarkStatus(int index, boolean changeTo, String type) throws DukeException{
        if (tasks.get(index).getIsDone() == changeTo) {
            ui.printMarkError(type);
            throw new DukeException();
        }
    }

    /**
     * Adds task to datafile and prints a message to indicate the task is added.
     */
    public void echo() {
        System.out.println(tasks.get(taskCount));
        try {
            FileManager.writeTask(path, tasks.get(taskCount));
        } catch (IOException e) {
            ui.printWriteFileError();
        }
    }

    /**
     * Function when <code>mark</code> user input is called for a certain task object.
     * Change status of task object to mark if the task object is unmarked.
     *
     * @param parsedCommand User input command after parsed by parser.
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     */
    public void handleMark(String parsedCommand, String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), taskCount);
            checkMarkStatus(parser.getNum(), true, parsedCommand);
        } catch (DukeException e) {
            return;
        }
        tasks.get(parser.getNum()).setDone(true);
        if (isFromCommand) {
            ui.printMark();
            System.out.println(tasks.get(parser.getNum()));
            rewriteFile(path);
        }
    }

    /**
     * Function when <code>unmark</code> user input is called for a certain task object.
     * Change status of task object to unmark if the task object is unmarked.
     *
     * @param parsedCommand User input command after parsed by parser.
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     */
    public void handleUnmark (String parsedCommand, String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), tasks.size());
            checkMarkStatus(parser.getNum(), false, parsedCommand);
        } catch (DukeException e) {
            return;
        }
        tasks.get(parser.getNum()).setDone(false);
        if (isFromCommand) {
            ui.printUnmark();
            System.out.println(tasks.get(parser.getNum()));
            rewriteFile(path);
        }
    }

    /**
     * Function to add a <code>ToDo</code> task object to this object.
     * Updates taskCount after successful add.
     *
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     * @return Returns the <code>ToDo</code> task object after it is successfully added.
     */
    public ToDo handleTodo(String next, boolean isFromCommand) {
        ToDo task = new ToDo(next.stripLeading(), false);
        tasks.put(++taskCount, task);
        if (isFromCommand) {
            ui.printTodo();
            echo();
        }
        return task;
    }

    /**
     * Function to add a <code>Deadline</code> task object to this object.
     * Updates taskCount after successful add.
     *
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     * @return Returns the <code>Deadline</code> task object after it is successfully added.
     */
    public Deadline handleDeadline(String next, boolean isFromCommand) throws DukeException {
        String[] deadline = next.split("/by", 2);
        LocalDate localByDate;
        try {
            localByDate = parser.processDate(deadline[1].trim());
            deadline[1] = localByDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        } catch (DukeException f) {
            localByDate = null;
        }
        Deadline task = new Deadline(deadline[0].trim(), false, deadline[1].trim(), localByDate);
        tasks.put(++taskCount ,task);
        if (isFromCommand) {
            ui.printDeadline();
            echo();
        }
        return task;
    }

    /**
     * Function to add an <code>Event</code> task object to this object.
     * Updates taskCount after successful add.
     *
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     * @return Returns the <code>Event</code> task object after it is successfully added.
     */
    public Event handleEvent(String next, boolean isFromCommand) throws DukeException {
        String[] eventName = next.split("/from", 2);
        LocalDate localByDate;
        LocalDate localFromDate;
        try {
            String[] eventTime = eventName[1].split("/to", 2);
            try {
                localFromDate = parser.processDate(eventTime[0].trim());
                eventTime[0] = localFromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DukeException e) {
                localFromDate = null;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException();
            }
            try {
                localByDate = parser.processDate(eventTime[1].trim());
                eventTime[1] = localByDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DukeException e) {
                localByDate = null;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException();
            }
            Event task = new Event(eventName[0].trim(), false, eventTime[0].trim(), eventTime[1].trim()
                    , localByDate, localFromDate);
            tasks.put(++taskCount, task);
            if (isFromCommand) {
                ui.printEvent();
                echo();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Function to delete a task object after <code>delete</code> is called in user input.
     * Decrements taskCount to update the number of task objects in this object after deletion.
     *
     * @param next User input line after command after parsed by parser.
     * @param isFromCommand Check for whether this function is called via user input.
     */
    public void handleDelete(String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), tasks.size());
        } catch (DukeException e) {
            return;
        }
        int num = parser.getNum();
        Task task = tasks.get(num);
        if (isFromCommand) {
            ui.printDeleted(task.toString());
        }
        while(tasks.get(num + 1) != null) {
            tasks.put(num, tasks.get(num + 1));
            ++num;
        }
        tasks.remove(num);
        rewriteFile(path);
        --taskCount;
    }
}
