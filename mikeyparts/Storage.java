package mikeyparts;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

import static mikeyparts.TaskList.newTodo;
import static mikeyparts.TaskList.newDeadline;
import static mikeyparts.TaskList.newEvent;
import static mikeyparts.UI.printFileCreationErrorMessage;
import static mikeyparts.UI.printFileNotFoundMessage;
import static mikeyparts.UI.printInvalidTaskMessage;


public class Storage {
    /**
     * Saves the data in the "tasks" arraylist to a text file in a certain format, and throws an IO exception
     * if the save fails, then creates the file manually and tries again.
     *
     * @throws java.io.IOException if the home directory and file cannot be found
     */
    public static void saveToFile(ArrayList<Task> tasks) throws java.io.IOException {
        StringJoiner taskFormatter = new StringJoiner(System.lineSeparator());
        for (Task t: tasks) {
            taskFormatter.add(t.taskStringFormat());
        }
        while (true) {
            try {
                FileWriter listWrite = new FileWriter("./data/Mikey.txt");
                listWrite.write(taskFormatter.toString());
                listWrite.close();
                break;
            } catch (FileNotFoundException e) {
                fileNotFound(taskFormatter.toString());
            }
        }
    }

    /**
     * Reads data from a text file and converts it into data to create new tasks to add to the "tasks" arraylist,
     * throws an exception if the file is not found and creates the file
     *
     * @throws java.io.IOException if the home directory and the file cannot be found
     */
    public static void readFromFile() throws java.io.IOException{
        try {
            File readingFile = new File("./data/Mikey.txt");
            Scanner fileScan = new Scanner(readingFile);
            while(fileScan.hasNextLine()) {
                String taskLine = fileScan.nextLine();
                String[] taskDetails = taskLine.split(" \\|\\ ", 0);
                String taskType = taskDetails[0];
                String taskCompletion = taskDetails[1];
                String taskDescription = taskDetails[2];
                if(taskType.equalsIgnoreCase("T")) {
                    try {
                        newTodo(taskDescription, Integer.parseInt(taskCompletion));
                    } catch (Exception e) {
                        printInvalidTaskMessage();
                    }
                } else if (taskType.equalsIgnoreCase("D")) {
                    try {
                        String[] taskDateTemp = taskDetails[3].split("\\(by ", 0);
                        String taskDate = taskDateTemp[1].substring(0);
                        taskDate = taskDate.replace(")", "");
                        newDeadline(taskDescription, taskDate, Integer.parseInt(taskCompletion));
                    } catch (Exception e) {
                        printInvalidTaskMessage();
                    }
                } else if (taskType.equalsIgnoreCase("E")) {
                    try {
                        String[] taskDateTemp = taskDetails[3].split("\\(");
                        String taskDate = taskDateTemp[0].substring(0);
                        newEvent(taskDescription, taskDate, Integer.parseInt(taskCompletion));
                    } catch (StringIndexOutOfBoundsException e) {
                        printInvalidTaskMessage();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            fileNotFound("");
        }

    }

    /**
     * Handler for the FileNotFoundException in the saveToFile and readFromFile methods, creates a new directory
     * and text file
     *
     * @param taskFormatter the formatted string of details of a task to be written to the file, set to empty when a new
     *                      blank file is created
     */
    public static void fileNotFound(String taskFormatter) {
        printFileNotFoundMessage();
        try {
            File newFile = new File("./data/Mikey.txt");
            newFile.getParentFile().mkdirs();
            FileWriter listWrite = new FileWriter("./data/Mikey.txt");
            listWrite.write(taskFormatter.toString());
            listWrite.close();
        } catch (IOException e) {
            printFileCreationErrorMessage();
        }
    }
}
