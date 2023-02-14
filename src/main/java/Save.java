import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

public class Save {

    public static String filePath;

    public static void createSaveFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }
    }

    public static void loadSaveFile(Task[] tasks) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|", -1);
                int currentIndex = 0;
                if (line.startsWith("T")) {
                    ToDo todo = new ToDo(currentIndex, args[2]);
                    if (args[2].equals("X")) {
                        todo.markAsDone();
                    }
                    tasks[currentIndex] = todo;
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(currentIndex, args[2], args[3]);
                    if (args[2].equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks[currentIndex] = deadline;
                } else {
                    Event event = new Event(currentIndex, args[2], args[3], args[4]);
                    if (args[2].equals("X")) {
                        event.markAsDone();
                    }
                    tasks[currentIndex] = event;
                }
                currentIndex++;
            }
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! An error occurred");
        } catch (Exception exception) {
            System.out.println("☹ OOPS!!! An error occurred");
        }
    }

    public void updateSaveFile(Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.length - 1; i++) {
            Task currentTask = tasks[i];
            String type;
            String description = currentTask.getDescription();
            String done = currentTask.isDone() ? "X" : " ";
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
