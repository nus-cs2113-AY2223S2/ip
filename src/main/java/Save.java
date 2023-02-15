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
    static final String PATH_NAME = "text files/saved_tasks.txt";
    static final String FILE_NOT_FOUND = "File not found";
    static final String IO_ERROR = "Error in reading/writing saved tasks file";
    static ArrayList getSavedTasks(ArrayList taskList) {
        try {
            taskList = readFile(taskList);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_ERROR);
        }
        return taskList;
    }
    static ArrayList readFile(ArrayList<Task> taskList) throws IOException {
        Scanner scanner = new Scanner(new File(PATH_NAME));
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\|");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].trim();
            }
            String type = line[0];
            switch (type) {
            case "T":
                taskList.add(new ToDo(line[2], Boolean.parseBoolean(line[1])));
                break;
            case "D":
                taskList.add(new Deadline(line[2], Boolean.parseBoolean(line[1]), line[3]));
                break;
            case "E":
                taskList.add(new Event(line[2], Boolean.parseBoolean(line[1]), line[3], line[4]));
                break;
            default:
                throw new IOException();
            }
        }
        return taskList;
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
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
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
