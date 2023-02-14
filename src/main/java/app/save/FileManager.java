package app.save;

import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File folder = new File("data");
        File f = new File(filePath);
        ArrayList<Task> tasks;
        if (folder.exists() && f.exists()) {
            tasks = readTasks(filePath);
        } else {
            folder.mkdir();
            f.createNewFile();
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    private static ArrayList<Task> readTasks(String filePath) {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            ArrayList<Task> savedTasks = new ArrayList<>();

            while (s.hasNext()){
                Task currentTask = parseTask(s.nextLine());
                if (currentTask != null){
                    savedTasks.add(currentTask);
                }
            }
            s.close();
            return savedTasks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) throws Exception {
        File file = new File("data/duke.txt");
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            if (task == null) break;
            fw.write(stringifyTask(task));
        }
        fw.close();
    }

    private static Task parseTask(String line) throws Exception {
        Task task;
        String delimiter = "\\|";
        String[] taskDetails = line.split(delimiter);
        String taskType = taskDetails[0].trim();
        boolean isDone = taskDetails[1].trim().equals("1");
        String taskDescription = taskDetails[2].trim();
        String startTime, endTime;

        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription, isDone);
            break;
        case "D":
            endTime = taskDetails[3].trim();
            task = new Deadline(taskDescription, isDone, endTime);
            break;
        case "E":
            startTime = taskDetails[3].trim();
            endTime = taskDetails[4].trim();
            task = new Event(taskDescription, isDone, startTime, endTime);
            break;
        default:
            throw new Exception();
        }
        return task;
    }

    private static String stringifyTask(Task task) throws Exception {
        String outputString;
        String delimiter = " | ";
        String taskStatus = task.isDone() ? "1" : "0";
        String taskDescription = task.getTaskDescription();

        if (task instanceof ToDo) {
            String taskType = "T";
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String taskType = "D";
            String endTime = deadline.getBy();
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription, endTime);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            String taskType = "E";
            String startTime = event.getStartTime();
            String endTime = event.getEndTime();
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription, startTime, endTime);
        } else {
            throw new Exception();
        }
        return outputString + "\n";
    }
}

