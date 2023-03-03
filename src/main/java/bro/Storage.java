package bro;

import bro.tasks.Task;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.ToDo;
import static bro.Messages.IO_ERROR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final static String PATH_NAME = "tasks.txt";

    /**
     * Loads an Instance of TaskList with all tasks saved in the storage file.
     *
     * @param taskListObject Instance of empty TaskList
     * @return Instance of TaskList with all tasks loaded
     * @throws FileNotFoundException Missing storage file
     */
    public static TaskList load(TaskList taskListObject) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH_NAME));
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\|");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].trim();
            }
            String type = line[0];
            switch (type) {
            case "T":
                taskListObject.add(new ToDo(line[2], Boolean.parseBoolean(line[1])));
                break;
            case "D":
                taskListObject.add(new Deadline(line[2], Boolean.parseBoolean(line[1]), line[3]));
                break;
            case "E":
                taskListObject.add(new Event(line[2], Boolean.parseBoolean(line[1]), line[3], line[4]));
                break;
            default:
                throw new IllegalArgumentException(); // throw an unchecked exception
            }
        }
        return taskListObject;
    }

    /**
     * Updates all tasks in the storage file.
     *
     * @param taskListObject Instance of TaskList that contains all tasks
     */
    public static void save(TaskList taskListObject) {
        try {
            ArrayList<Task> taskList = taskListObject.getTaskList();
            updateTasks(taskList);
        } catch (IOException e){
            System.out.println(IO_ERROR);
        }
    }
    private static void updateTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH_NAME);
        StringBuilder output = new StringBuilder();
        for (Task currentTask : taskList) {
            String type = currentTask.getType();
            String isCompleted = currentTask.isCompleted() ? "true" : "false";
            String name = currentTask.getName();
            String line;
            switch (type) {
            case "T":
                line = String.format("%s | %s | %s", type, isCompleted, name);
                output.append(line).append(System.lineSeparator());
                break;
            case "D":
                Deadline currentDeadline = (Deadline) currentTask; // casting to use subclass methods
                line = String.format("%s | %s | %s | %s", type, isCompleted, name, currentDeadline.getDeadline());
                output.append(line).append(System.lineSeparator());
                break;
            case "E":
                Event currentEvent = (Event) currentTask;
                line = String.format("%s | %s | %s | %s | %s", type, isCompleted, name, currentEvent.getStartTime(), currentEvent.getEndTime());
                output.append(line).append(System.lineSeparator());
                break;
            default:
                throw new IOException();
            }
        }
        fileWriter.write(String.valueOf(output));
        fileWriter.close();
    }
}
