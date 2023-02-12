package duke.data;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileData {
    public static void makeDirectory() {
        File dataDirectory = new File("data");
        dataDirectory.mkdirs();
    }

    public static File openDataFile() {
        File dataFile = new File("data\\duke.txt");
        try {
            dataFile.createNewFile();
        } catch (Exception e) {
        }
        return dataFile;
    }

    public static void uploadData(File dataFile, ArrayList<Task> tasks) {
        try {
            Scanner s = new Scanner(dataFile);
            while (s. hasNext()) {
                String task = s.nextLine();
                determineData(task, tasks);
            }
        } catch (FileNotFoundException e) {
        }
    }

    public static void determineData(String task, ArrayList<Task> tasks) {
        String[] taskInfo = task.split(" / "); // what if user task name includes "|"...
        if (taskInfo[0].equals("T")) {
            tasks.add(Task.totalTasks, new Todo(taskInfo[2].trim()));
        } else if (taskInfo[0].equals("D")) {
            // "deadline"
            tasks.add(Task.totalTasks, new Deadline(taskInfo[2], taskInfo[3]));
        } else {
            // "event"
            String[] timeInterval = taskInfo[3].split(" to ");
            tasks.add(Task.totalTasks, new Event(taskInfo[2].trim(), timeInterval[0].trim(), timeInterval[1].trim()));
        }
        if (taskInfo[1].equals("1")) {
        tasks.get(Task.totalTasks).markAsDone();
        }
        Task.incrementTotalTasks();
    }

    public static void writeToFile(File dataFile, ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data\\duke.txt");

            for (int i = 0; i < Task.totalTasks; i += 1) {
                String taskDetails = tasks.get(i).getSavedData();
                fw.write(taskDetails + System.lineSeparator());
                // add taskdetails to file
            }
            fw.close();
        } catch (IOException e) {

        }
    }
}
