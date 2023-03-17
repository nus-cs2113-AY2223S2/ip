package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public Parser() {
    }

    /**
     * Loads file and parses file contents.
     * @param fileReader Scanner scanning file contents
     */
    public static void parseFile(Scanner fileReader) {
        String[] savedDataLine;
        savedDataLine = fileReader.nextLine().split("/");
        if (savedDataLine[0].equals("[T]")) {
            Duke.taskList.setStoredTasks(Duke.parser.parseTodo(savedDataLine, Duke.taskList.getStoredTasks()));
        } else if (savedDataLine[0].equals("[D]")) {
            Duke.taskList.setStoredTasks(Duke.parser.parseDeadline(savedDataLine, Duke.taskList.getStoredTasks()));
        } else if (savedDataLine[0].equals("[E]")) {
            Duke.taskList.setStoredTasks(Duke.parser.parseEvent(savedDataLine, Duke.taskList.getStoredTasks()));
        }
    }

    public String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    /**
     * Parses deadline from the save file
     * @param savedDataLine line of file to be parsed
     * @param storedTasks List of all tasks user has stored
     * @return
     */
    public ArrayList<Task> parseDeadline(String[] savedDataLine, ArrayList<Task>storedTasks) {
        Deadline deadline = new Deadline(savedDataLine[2], savedDataLine[3]);
        if (savedDataLine[1].equals("[X]")) {
            deadline.setDone(true);
        }
        storedTasks.add(deadline);
        return storedTasks;
    }
    /**
     * Parses todo from the save file
     * @param savedDataLine line of file to be parsed
     * @param storedTasks List of all tasks user has stored
     * @return
     */
    public ArrayList<Task> parseTodo(String[] savedDataLine, ArrayList<Task>storedTasks){
        Todo todo = new Todo(savedDataLine[2]);
        if (savedDataLine[1].equals("[X]")) {
            todo.setDone(true);
        }
        storedTasks.add(todo);
        return storedTasks;
    }
    /**
     * Parses event from the save file
     * @param savedDataLine line of file to be parsed
     * @param storedTasks List of all tasks user has stored
     * @return List of stored tasks
     */
    public ArrayList<Task> parseEvent(String[] savedDataLine, ArrayList<Task>storedTasks){
        String[] period = savedDataLine[3].split(",");
        Event event = new Event(savedDataLine[2], period[0], period[1]);
        if (savedDataLine[1].equals("[X]")) {
            event.setDone(true);
        }
        storedTasks.add(event);
        return storedTasks;
    }

}
