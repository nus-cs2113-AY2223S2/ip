package io;

import task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * This class manages Input and Output for Duke.<br>
 * Includes Input validation, processing arguments, and also file writing I/O.<br>
 * Credits to Contacts in Week 4 for input processing/handling methods.
 * @author Choong Zhan Hong
 */
public final class IO {

    /**
     * Split input into 1st arg and subsequent line.
     * @param inputLine A line of input direct from user
     */
    public static String[] splitCommandAndArgs(String inputLine) {
        // Trim trailing and beginning whitespace, then split once. Output is [command, args].
        final String[] split = inputLine.trim().split("\\s+", 2);
        // Else case: no parameters
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Input validation for Todo Task. Check if input empty.
     * @param commandArgs Line input, should simply be the task name
     * @return the same input, unless blank
     * @throws DukeException only if blank
     */
    public static String processTaskTodo(String commandArgs) throws DukeException {
        if (commandArgs.isBlank()) {
            throw new DukeException();
        }
        return commandArgs;
    }

    public static String[] processTaskDeadline(String commandArgs) throws DukeException {
        String[] deadlineArgs = commandArgs.split(Ui.COMMAND_TASK_DEADLINE_DELIMITER_REGEX);

        // Needs to be specifically 2, i.e. [task_name, deadline]
        // Is this a magic number?
        if (deadlineArgs.length != 2) {
            throw new DukeException();
        }
        return deadlineArgs;
    }

    public static String[] processTaskEvent(String commandArgs) throws DukeException {
        // ensure it contains both.
        if (!commandArgs.contains(Ui.COMMAND_TASK_EVENT_DELIMITER1) ||
                !commandArgs.contains(Ui.COMMAND_TASK_EVENT_DELIMITER2)) {
            throw new DukeException();
        }

        // TODO check for inputs like /from abc /from abc /to
        String[] eventArgs = commandArgs.split(Ui.COMMAND_TASK_EVENT_DELIMITER_REGEX);

        // Needs to be specifically 3, i.e. [task_name, from, to]
        if (eventArgs.length != 3) {
            throw new DukeException();
        }
        return eventArgs;
    }

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/papatask.txt";
    // Using this in Task.java, hence public. Consider possibility of only using in IO?
    public static final String FILE_DELIMITER = "|";

    /**
     * Appends input text to the save file.
     * @param textToAdd The String to append to the file.
     * @throws IOException Unable to write successfully.
     */
    public static void writeToFile(String textToAdd) {
        try {
            // 2nd argument true: indicates to append instead of overwrite.
            // I want to overwrite.
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(textToAdd);
            // Add newline
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong: " + e.getMessage());
        }
    }

    /**
     * Read the file at the file save data path
     */
    public static void readFile(String path) {
        File f = new File(path);
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                readLineAsTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found >_<: " + e.getMessage());
        }
    }

    /**
     * Reads the line in the save file and invokes TaskList to add task, if any.
     * @param line String line in the text file.
     */
    private static void readLineAsTask(String line) {
        if (line.isBlank()) {
            return;
        }
        // Delimiter and any amount of whitespace on left/right. Note, need to escape regex \\.
        String[] linesSplit = line.split("\\s+" + "\\" + FILE_DELIMITER + "\\s+");


        switch (linesSplit[0]) {
        case "T":
            // Has to contain T, isdone, and Description
            if (linesSplit.length == 3) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        case "D":
            // Has to contain D, isdone, description, by
            if (linesSplit.length == 4) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        case "E":
            // Has to contain E, isdone, description, from, to
            if (linesSplit.length == 5) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        default:
            System.out.println("I think there's an error with the file.");
        }
    }
}
