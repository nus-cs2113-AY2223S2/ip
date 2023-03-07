package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns boolean value of true if input String is an integer,
     * else returns boolean value of false
     *
     * @param word String input to check if it is an integer
     * @return true if input String is an integer, otherwise false
     */
    public static boolean isNumeric(String word) {
        int valueToConvert;
        try {
            valueToConvert = Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input, please enter an integer.");
        }
        return false;
    }

    /**
     * Processes the user input and executes the appropriate command
     *
     * @param tasks The array list of tasks
     * @param line The line of user input
     * @param in The input from scanner
     */
    static void processCommand(ArrayList<Task> tasks, String line, Scanner in) {
        while (!line.equals("bye")) { // Exits the program if input is "bye"
            String[] words = line.split(" ");
            if (line.isBlank()) {
                Ui.emptyCommandMessage();
            } else if (line.equals("list")) {
                // List out all the tasks added
                Ui.list(tasks);
            } else if (words[0].equals("unmark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as not done
                TaskList.unmarkTask(tasks, words);
                Storage.trySave(tasks);
            } else if (words[0].equals("mark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as done
                TaskList.markTask(tasks, words);
                Storage.trySave(tasks);
            } else if (words[0].equals("delete") && (words.length == 2) && (isNumeric(words[1]))) {
                // Delete a task
                TaskList.deleteTask(tasks, words);
                Storage.trySave(tasks);
            } else if (words[0].equals("find") && (words.length > 1)) {
                // Find tasks that contain a keyword
                Ui.find(tasks, words);
            } else {
                // Adding a task to the list
                TaskList.addTask(line, tasks);
                Task.incrementCount();
                Storage.trySave(tasks);
            }
            line = in.nextLine();
        }
    }

    /**
     * Process the array of words from the user input and extracts the
     * keywords into a single string to use for the find function
     *
     * @param words The array of words generated from the user input
     * @return The keywords string to use for the find function
     */
    static String processKeywords(String[] words) {
        String rawKeyword = "";
        for (int i = 1; i < words.length; i++) {
            rawKeyword += (" " + words[i]);
        }
        String keyword = rawKeyword.trim();
        return keyword;
    }
}