import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    private static String filePath;

    public void createSaveFile() throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        file.mkdirs();
    }

    public static void loadSaveFile(Task[] tasks) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] args = line.split("\\|", -1);
        int currentIndex = 0;
        while(line != null) {
            if (line.startsWith("T")) {
                ToDo todo = new ToDo(currentIndex, args[1]);
                tasks[currentIndex] = todo;
            } else if (line.startsWith("D")){
                Deadline deadline = new Deadline(currentIndex, args[1], args[2]);
                tasks[currentIndex] = deadline;
            } else {
                Event event = new Event(currentIndex, args[1], args[2], args[3]);
                tasks[currentIndex] = event;
            }
            currentIndex++;
        }

    }
    public static void updateSaveFile(Task[] tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.length - 1; i++) {
            Task currentTask = tasks[i];
            String type;
            String done = currentTask.getStatusIcon();
            String description = currentTask.getDescription();
            if (currentTask instanceof ToDo) {
                type = "T";
                fw.write(type + "|" + done + "|" + description);
            } else if (currentTask instanceof Deadline) {
                type = "D";
                String by = ((Deadline) currentTask).getBy();
                fw.write(type + "|" + done + "|" + description + "|" + by);
            } else {
                type = "E";
                String from = ((Event) currentTask).getFrom();
                String to = ((Event) currentTask).getTo();
                fw.write(type + "|" + done + "|" + description + "|" + from + "|" + to);
            }
        }
        fw.close();
    }
}
