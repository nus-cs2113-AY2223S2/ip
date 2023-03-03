import bro.tasks.Task;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    static final String PATH_NAME = "saved_tasks.txt";
    static final String FILE_NOT_FOUND = "File not found";
    static final String IO_ERROR = "Error in reading/writing saved tasks file";
    static ArrayList<Task> getSavedTasks() {
        ArrayList<Task> savedTaskList = new ArrayList<>();
        try {
            savedTaskList = readFile();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_ERROR);
        }
        return savedTaskList;
    }
    static ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> readTaskList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(PATH_NAME));
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\|");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].trim();
            }
            String type = line[0];
            switch (type) {
            case "T" -> readTaskList.add(new ToDo(line[2], Boolean.parseBoolean(line[1])));
            case "D" -> readTaskList.add(new Deadline(line[2], Boolean.parseBoolean(line[1]), line[3]));
            case "E" -> readTaskList.add(new Event(line[2], Boolean.parseBoolean(line[1]), line[3], line[4]));
            default -> throw new IOException();
            }
        }
        return readTaskList;
    }
    static void saveToFile() {
        try {
            updateTasks(Bro.taskList);
        } catch (IOException e){
            System.out.println(IO_ERROR);
        }
    }
    static void updateTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH_NAME);
        StringBuilder output = new StringBuilder();
        for (Task currentTask : taskList) {
            String type = currentTask.getType();
            String isCompleted = currentTask.isCompleted() ? "true" : "false";
            String name = currentTask.getName();
            String line;
            switch (type) {
            case "T" -> {
                line = String.format("%s | %s | %s", type, isCompleted, name);
                output.append(line).append(System.lineSeparator());
            }
            case "D" -> {
                Deadline currentDeadline = (Deadline) currentTask; // casting to use subclass methods
                line = String.format("%s | %s | %s | %s", type, isCompleted, name, currentDeadline.getDeadline());
                output.append(line).append(System.lineSeparator());
            }
            case "E" -> {
                Event currentEvent = (Event) currentTask;
                line = String.format("%s | %s | %s | %s | %s", type, isCompleted, name, currentEvent.getStartTime(), currentEvent.getEndTime());
                output.append(line).append(System.lineSeparator());
            }
            default -> throw new IOException();
            }
        }
        fileWriter.write(String.valueOf(output));
        fileWriter.close();
    }
}
