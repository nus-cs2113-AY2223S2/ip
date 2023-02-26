package duke;

import duke.exceptions.FormatException;
import duke.exceptions.NoDescriptionException;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static final String DEFAULT_STORAGE_FILEPATH = "data.txt";
    private static Command command = new Command();
    public void initializeStorage(ArrayList<Task> tasks, String filepath) {
        try {
            printFileContents(tasks, filepath);
        } catch (FileNotFoundException e) {
            System.out.println("No previous file, Duke will try to create a file to store your data.");
            try {
                new File(filepath).createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException("Creation of file failed.", ioe);
            }
        }
    }
    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < tasks.size(); i += 1) {
            outputWriter.write(tasks.get(i).toStorage() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }
    public void storeChanges(String filepath, ArrayList<Task> tasks) {
        try {
            writeToFile(filepath, tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    private static void printFileContents(ArrayList<Task> tasks, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println("These are the task from your file: ");
        while (s.hasNext()) {
            String task = s.nextLine();
            System.out.println(task);
            String type = task.substring(1,2);
            final int descriptionId = task.lastIndexOf("]");
            String taskDescription = task.substring(descriptionId+1).trim();
            final int doneId = task.indexOf("[X]");
            switch(type) {
            case "T":
                try {
                    command.addTodo(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            case "E":
                try {
                    command.addEvent(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException | ParseException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            case "D":
                try {
                    command.addDeadline(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException | ParseException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            }
        }
        printLine();
    }

}
