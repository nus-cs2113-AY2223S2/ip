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

public class TasksDataRead {
    private static void interpretSaveEntry(String[] savedEntryWords, ArrayList<Task> savedTasks) {
        switch (savedEntryWords[0]) {
        case "todo":
            String[] todoInput = savedEntryWords[1].split("/", 2);
            addTodo(new Todo(todoInput[0], parseBoolean(todoInput[1])), savedTasks);
            break;
        case "deadline":
            String[] deadlineInput = savedEntryWords[1].split("/", 3);
            addDeadline(new Deadline(deadlineInput[0], deadlineInput[1], parseBoolean(deadlineInput[2])), savedTasks);
            break;
        case "event":
            String[] eventInput = savedEntryWords[1].split("/", 4);
            addEvent(new Event(eventInput[0], eventInput[1], eventInput[2], parseBoolean(eventInput[3])), savedTasks);
            break;
        default:
            DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            break;
        }
    }
    private static void addTodo(Todo todo, ArrayList<Task> savedTasks) {
        savedTasks.add(todo);
    }
    private static void addDeadline(Deadline deadline, ArrayList<Task> savedTasks) {
        savedTasks.add(deadline);
    }
    private static void addEvent(Event event, ArrayList<Task> savedTasks) {
        savedTasks.add(event);
    }
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
