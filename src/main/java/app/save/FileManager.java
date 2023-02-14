package app.save;

import app.exceptions.InvalidCommandException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void loadTasks() throws IOException, InvalidCommandException {
        File folder = new File("data");
        if (!(folder.exists() && folder.isDirectory())){
            new File("data").mkdir();
        }
        File file = new File ("data/tasklist.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String nextLine = s.nextLine();
            System.out.println(nextLine);
        }
    }

    public static void saveTasks(Task[] tasks) throws Exception {
        File file = new File("data/tasklist.txt");
        FileWriter fw = new FileWriter(file);
        for (Task task: tasks){
            if (task == null) break;
            fw.write(stringifyTask(task));
        }
        fw.close();
    }

    public static String stringifyTask(Task task) throws Exception {
        String outputString;
        String delimiter = " | ";
        String isDone = task.isDone() ? "1" : "0";
        String taskDescription = task.getTaskDescription();

        if (task instanceof ToDo){
            String taskType = "T";
            outputString = String.join(delimiter, taskType, isDone, taskDescription);
        } else if (task instanceof Deadline){
            Deadline deadline = (Deadline) task;
            String taskType = "D";
            String endTime = deadline.getBy();
            outputString = String.join(delimiter, taskType, isDone, taskDescription, endTime);
        } else if (task instanceof Event){
            Event event = (Event) task;
            String taskType = "E";
            String startTime = event.getStartTime();
            String endTime = event.getEndTime();
            outputString = String.join(delimiter, taskType, isDone, taskDescription, startTime, endTime);
        } else {
            throw new Exception();
        }
        return outputString + "\n";
    }
}

