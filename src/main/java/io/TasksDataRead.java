package io;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Boolean.parseBoolean;

/**
 * This class handles the reading of the list of tasks stored in a persistent txt file written from previous sessions.
 */
public class TasksDataRead {
    /**
     * Reads the txt file storing the tasks between sessions by splitting using delimiters and reading the first word
     *
     * @param savedEntryWords
     * @param savedTasks
     */
    private static void interpretSaveEntry(String[] savedEntryWords, ArrayList<Task> savedTasks) {
        switch (savedEntryWords[0]) {
        case "todo":
            String[] todoInput = savedEntryWords[1].split("/marked", 2);
            addTodo(new Todo(todoInput[0], parseBoolean(todoInput[1])), savedTasks);
            break;
        case "deadline":
            String[] deadlineInput = savedEntryWords[1].split("/by|/marked", 3);
            addDeadline(new Deadline(deadlineInput[0], deadlineInput[1], parseBoolean(deadlineInput[2])), savedTasks);
            break;
        case "event":
            String[] eventInput = savedEntryWords[1].split("/from|/to|/marked", 4);
            addEvent(new Event(eventInput[0], eventInput[1], eventInput[2], parseBoolean(eventInput[3])), savedTasks);
            break;
        default:
            DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            break;
        }
    }

    /**
     * @param todo The saved Todo task.
     * @param savedTasks A new ArrayList created at the start of the session.
     */
    private static void addTodo(Todo todo, ArrayList<Task> savedTasks) {
        savedTasks.add(todo);
    }

    /**
     * @param deadline The saved Deadline task.
     * @param savedTasks A new ArrayList created at the start of the session.
     */
    private static void addDeadline(Deadline deadline, ArrayList<Task> savedTasks) {
        savedTasks.add(deadline);
    }

    /**
     * @param event The saved Event task.
     * @param savedTasks A new ArrayList created at the start of the session.
     */
    private static void addEvent(Event event, ArrayList<Task> savedTasks) {
        savedTasks.add(event);
    }

    /**
     * @param filePath A string representing the relative path towards the data txt file stored between sessions.
     * @return A new ArrayList containing all of the tasks stored in the data txt file from previous sessions.
     * @throws FileNotFoundException The txt file at the filePath does not exist.
     */
    public static ArrayList<Task> readSavedTasks(String filePath) throws FileNotFoundException {
        //TODO: Account for '/' in entries.
        ArrayList<Task> savedTasks = new ArrayList<Task>();
        File tasksDataFile = new File(filePath);
        Scanner tasksDataReader = new Scanner(tasksDataFile);
        String savedEntry;
        String[] savedEntryWords;
        while (tasksDataReader.hasNext()) {
            savedEntry = tasksDataReader.nextLine();
            savedEntryWords = savedEntry.split(" ", 2);
            try {
                interpretSaveEntry(savedEntryWords, savedTasks);
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            }
        }
        return savedTasks;
    }
}
