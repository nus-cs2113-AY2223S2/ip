package Storage;
import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Storage {
    ArrayList<Task> list;
    Path path;
    public Storage(Path path) {
        this.list = new ArrayList<Task>();
        this.path = path;
    }
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Reads the file from the path from Duke class. If path cannot be read, creates a new file in that path and creates
     * a new file with an empty list.
     */
    public ArrayList<Task> readFromFile(Path filePath) throws DukeException {
        //Frome https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
        byte[] tasksInByteForm = new byte[1000000];
        ArrayList<Task> list = new ArrayList<Task>();
        if (Files.exists(path)) {
            try {
                tasksInByteForm = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new DukeException("File unreadable! Unable to call readAllBytes() on the path given");
            }
            String tasksInStringForm = new String(tasksInByteForm);
            String[] arrayOfTasks = tasksInStringForm.split("\n");
            for (String task : arrayOfTasks) {
                Task recreatedTask = createTaskFromString(task);
                if (recreatedTask == null) {
                    System.out.println("Unable to create task " + task);
                    continue;
                }
                list.add(recreatedTask);
            }
        }
        return list;
    }

    private static Task createTaskFromString(String string){
        String[] attributes = string.split("\\|");
        if (attributes.length < 1) {
            System.out.println("Wrong format for task");
        }
        String taskType = attributes[0];
        boolean bool;
        String taskName;
        String boolString;
        String deadline;
        String start;
        switch(taskType) {
            case ("class Tasks.Task"):
                taskName = attributes[1];
                boolString = attributes[2];
                bool = boolString.equals("true");
                return new Task(taskName, bool, 0);
            case ("class Tasks.Deadline"):
                taskName = attributes[1];
                boolString = attributes[2];
                deadline = attributes[3];
                bool = boolString.equals("true");
                return new Deadline(taskName, bool, 0, deadline);
            case ("class Tasks.Event"):
                taskName = attributes[1];
                boolString = attributes[2];
                deadline = attributes[3];
                start = attributes[4];
                bool = boolString.equals("true");
                return new Event(taskName, bool, 0, start, deadline);
            default:
                System.out.println(taskType + " is not an accepted task type");
                return null;
        }
    }

    /**
     * Saves list of tasks to the file path given. If fails, nothing is saved.
     */
    public void save(ArrayList<Task> list, Path path) throws DukeException {
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            // from https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new DukeException("Unable to create filepath");
            }
        }
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new DukeException("Unable to delete and then recreate file with given filepath");
        }
        for (Task task: list) {
            String taskType = String.valueOf(task.getClass());
            ArrayList<String> attributesToStore = new ArrayList<String>();
            String taskInLineForm = "";

            switch (taskType) {
                case ("class Tasks.Task"):
                    attributesToStore.add(taskType);
                    attributesToStore.add(task.getTaskName());
                    attributesToStore.add(String.valueOf(task.isDone()));
                    taskInLineForm = deliminterAdder(attributesToStore);
                    break;
                case ("class Tasks.Deadline"):
                    attributesToStore.add(taskType);
                    attributesToStore.add(task.getTaskName());
                    attributesToStore.add(String.valueOf(task.isDone()));
                    attributesToStore.add(((Deadline) task).getDeadline());
                    taskInLineForm = deliminterAdder(attributesToStore);
                    break;
                case ("class Tasks.Event"):
                    attributesToStore.add(taskType);
                    attributesToStore.add(task.getTaskName());
                    attributesToStore.add(String.valueOf(task.isDone()));
                    attributesToStore.add(((Event) task).getDeadline());
                    attributesToStore.add(((Event) task).getStart());
                    taskInLineForm = deliminterAdder(attributesToStore);
                default:
                    break;
            }
            taskInLineForm = taskInLineForm.concat("\n");
            try {
                Files.write(path, taskInLineForm.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new DukeException("Unable to write the task to file, given the filepath");
            }
        }
    }

    /**
     * Creates a string based on the list of items. Combines them into one big string with the delimeter "|"
     */
    private static String deliminterAdder(ArrayList<String> items) {
        String taskInStringForm = "";
        for (String item : items) {
            taskInStringForm = taskInStringForm.concat(item + "|");
        }
        return taskInStringForm;
    }
}
