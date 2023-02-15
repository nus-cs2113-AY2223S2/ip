package io;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksDataRead {
    private static void interpretSaveEntry(String[] savedEntryWords, ArrayList<Task> savedTasks) {
        switch (savedEntryWords[0]) {
        case "todo":
            try {
                addTodo(new Todo(savedEntryWords[1]), savedTasks);
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            }
            break;
        case "deadline":
            try {
                String[] deadlineInput = savedEntryWords[1].split("/", 2);
                addDeadline(new Deadline(deadlineInput[0], deadlineInput[1]), savedTasks);
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            }
            break;
        case "event":
            try {
                String[] eventInput = savedEntryWords[1].split("/", 3);
                addEvent(new Event(eventInput[0], eventInput[1], eventInput[2]), savedTasks);
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The saved file is corrupted");
            }
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
    private static ArrayList<Task> readSavedTasks(String filePath) throws FileNotFoundException {
        ArrayList<Task> savedTasks = new ArrayList<Task>();
        File tasksDataFile = new File(filePath);
        Scanner tasksDataReader = new Scanner(tasksDataFile);
        String savedEntry = tasksDataReader.nextLine();
        String[] savedEntryWords = savedEntry.split(" ", 2);
        while (tasksDataReader.hasNext()) {
            interpretSaveEntry(savedEntryWords, savedTasks);
            savedEntry = tasksDataReader.nextLine();
            savedEntryWords = savedEntry.split(" ", 2);
        }
        return savedTasks;
    }
}
