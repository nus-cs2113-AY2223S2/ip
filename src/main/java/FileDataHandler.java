import duke.Deadline;
import duke.Event;
import duke.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileDataHandler {
    protected String filePath;
    protected String directoryName;

    public FileDataHandler(String filePath, String directoryName){
        this.filePath = filePath;
        this.directoryName = directoryName;
    }
    public boolean createFile () throws IOException {
        File dataFile = new File(this.filePath);
        File dataDirectory = new File (this.directoryName);
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

    public void loadFile(TaskList taskList) throws FileNotFoundException {
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

    public void saveFile (TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) instanceof Todo) {
                saveTodoTask((Todo) taskList.getTask(i), fileWriter);
            }
            if (taskList.getTask(i) instanceof Deadline) {
                saveDeadlineTask((Deadline) taskList.getTask(i), fileWriter);
            }
            if (taskList.getTask(i) instanceof Event) {
                saveEventTask((Event) taskList.getTask(i), fileWriter);
            }
            if (i == taskList.getSize() - 1) {
                break;
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public void loadTodoTask (String description, TaskList taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        Todo savedTodoTask = new Todo(details);
        if (inputs[0].equals("1")) {
            savedTodoTask.markAsDone();
        }
        taskList.addTask(savedTodoTask);
    }

    public static void loadDeadlineTask (String description, TaskList taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        String by = inputs[2];
        Deadline savedDeadlineTask = new Deadline(details, by);
        if (inputs[0].equals("1")) {
            savedDeadlineTask.markAsDone();
        }
        taskList.addTask(savedDeadlineTask);
    }

    public static void loadEventTask (String description, TaskList taskList) {
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
        taskList.addTask(savedEventTask);
    }

    public static void saveTodoTask (Todo newTodoTask, FileWriter todoWriter) throws IOException {
        String details = newTodoTask.getDetails();
        String mark = "0";
        if (newTodoTask.getIsDone()) {
            mark = "1";
        }
        String combined = "T | " + mark + " | " + details;
        todoWriter.write(combined);
    }

    public static void saveDeadlineTask (Deadline newDeadlineTask, FileWriter deadlineWriter) throws IOException {
        String details = newDeadlineTask.getDetails();
        String mark = "0";
        if (newDeadlineTask.getIsDone()) {
            mark = "1";
        }
        String by = newDeadlineTask.getBy();
        String combined = "D | " + mark + " | " + details + " | " + by;
        deadlineWriter.write(combined);
    }

    public static void saveEventTask (Event newEventTask, FileWriter eventWriter) throws IOException {
        String details = newEventTask.getDetails();
        String mark = "0";
        if (newEventTask.getIsDone()) {
            mark = "1";
        }
        String from = newEventTask.getFrom();
        String to = newEventTask.getTo();
        String combined = "E | " + mark + " | " + details + " | " + from + "-" + to;
        eventWriter.write(combined);
    }
}

