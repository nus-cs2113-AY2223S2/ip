import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    public static String filePath;

    public static void createSaveFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }
    }

    public static void loadSaveFile(ArrayList<Task> tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|", -1);
                if (line.startsWith("T")) {
                    ToDo todo = new ToDo(args[2]);
                    if (args[2].equals("X")) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(args[2], args[3]);
                    if (args[2].equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                } else {
                    Event event = new Event(args[2], args[3], args[4]);
                    if (args[2].equals("X")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                }
            }
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! An error occurred");
        } catch (Exception exception) {
            System.out.println("☹ OOPS!!! An error occurred 2");
        }
    }

    public static void updateSaveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String type;
            String description = currentTask.getDescription();
            String done = currentTask.isDone() ? "X" : " ";
            if (currentTask instanceof ToDo) {
                type = "T";
                fw.write(type + "|" + done + "|" + description + "\n");
            } else if (currentTask instanceof Deadline) {
                type = "D";
                String by = ((Deadline) currentTask).getBy();
                fw.write(type + "|" + done + "|" + description + "|" + by + "\n");
            } else {
                type = "E";
                String from = ((Event) currentTask).getFrom();
                String to = ((Event) currentTask).getTo();
                fw.write(type + "|" + done + "|" + description + "|" + from + "|" + to + "\n");
            }
        }
        fw.close();
    }
}
