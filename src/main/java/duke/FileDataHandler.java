package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDataHandler {
    public static boolean createFile (String filePath, String directoryName) throws IOException {
        File dataFile = new File(filePath);
        File dataDirectory = new File (directoryName);
        boolean isDirectoryCreated;
        boolean isFileCreated = dataFile.exists();
        if (dataDirectory.exists()) {
            isFileCreated = dataFile.createNewFile();
        } else {
            isDirectoryCreated = dataDirectory.mkdir();
            if (isDirectoryCreated) {
                isFileCreated = dataFile.createNewFile();
            }
        }
        return isFileCreated;
    }

    public static void loadFile(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File dataFile = new File(filePath);
        Scanner input = new Scanner(dataFile);
        while (input.hasNextLine()) {
            String[] nextInput = input.nextLine().split(" \\| ", 2);
            if (nextInput[0].equals("T")) {
                loadTodoTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("D")) {
                loadDeadlineTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("E")) {
                loadEventTask(nextInput[1], taskList);
            }
        }
    }

    public static void saveFile (String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Todo) {
                saveTodoTask((Todo) taskList.get(i), fileWriter);
            }
            if (taskList.get(i) instanceof Deadline) {
                saveDeadlineTask((Deadline) taskList.get(i), fileWriter);
            }
            if (taskList.get(i) instanceof Event) {
                saveEventTask((Event) taskList.get(i), fileWriter);
            }
            if (i == taskList.size() - 1) {
                break;
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public static void loadTodoTask (String description, ArrayList<Task> taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        Todo savedTodoTask = new Todo(details);
        if (inputs[0].equals("1")) {
            savedTodoTask.markAsDone();
        }
        taskList.add(savedTodoTask);
    }

    public static void loadDeadlineTask (String description, ArrayList<Task> taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        String by = inputs[2];
        Deadline savedDeadlineTask = new Deadline(details, by);
        if (inputs[0].equals("1")) {
            savedDeadlineTask.markAsDone();
        }
        taskList.add(savedDeadlineTask);
    }

    public static void loadEventTask (String description, ArrayList<Task> taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        String range = inputs[2];
        String[] ranges = range.split("-");
        String from = ranges[0];
        String to = ranges[1];
        Event savedEventTask = new Event(details, from, to);
        if (inputs[0].equals("1")) {
            savedEventTask.markAsDone();
        }
        taskList.add(savedEventTask);
    }

    public static void saveTodoTask (Todo newTodoTask, FileWriter todoWriter) throws IOException {
        String details = newTodoTask.getDetails();
        String mark = "0";
        if (newTodoTask.isDone) {
            mark = "1";
        }
        String combined = "T | " + mark + " | " + details;
        todoWriter.write(combined);
    }

    public static void saveDeadlineTask (Deadline newDeadlineTask, FileWriter deadlineWriter) throws IOException {
        String details = newDeadlineTask.getDetails();
        String mark = "0";
        if (newDeadlineTask.isDone) {
            mark = "1";
        }
        String by = newDeadlineTask.getBy();
        String combined = "D | " + mark + " | " + details + " | " + by;
        deadlineWriter.write(combined);
    }

    public static void saveEventTask (Event newEventTask, FileWriter eventWriter) throws IOException {
        String details = newEventTask.getDetails();
        String mark = "0";
        if (newEventTask.isDone) {
            mark = "1";
        }
        String from = newEventTask.getFrom();
        String to = newEventTask.getTo();
        String combined = "E | " + mark + " | " + details + " | " + from + "-" + to;
        eventWriter.write(combined);
    }
}

