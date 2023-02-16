import bro.tasks.Task;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    static ArrayList<Task> getSavedTasks(ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(Bro.PATH_NAME));
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
                throw new IllegalArgumentException(); // throw an unchecked exception
            }
        }
        return taskList;
    }
    static void saveToFile(ArrayList<Task> taskList) {
        try {
            updateTasks(taskList);
        } catch (IOException e){
            System.out.println(Bro.IO_ERROR);
        }
    }
    static void updateTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(Bro.PATH_NAME);
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
