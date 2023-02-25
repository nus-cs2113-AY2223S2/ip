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
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createSaveFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }
    }

    public void loadSaveFile(TaskList tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|", -1);
                if (line.startsWith("T")) {
                    ToDo todo = new ToDo(args[2]);
                    if (args[2].equals("X")) {
                        todo.setAsDone();
                    }
                    tasks.addTask(todo);
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(args[2], args[3]);
                    if (args[2].equals("X")) {
                        deadline.setAsDone();
                    }
                    tasks.addTask(deadline);
                } else {
                    Event event = new Event(args[2], args[3], args[4]);
                    if (args[2].equals("X")) {
                        event.setAsDone();
                    }
                    tasks.addTask(event);
                }
            }
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! The file is corrupted");
        } catch (Exception exception) {
            System.out.println("☹ OOPS!!! An error occurred while loading the file");
        }
    }

    public void updateSaveFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.sizeOfTaskList(); i++) {
            Task currentTask = tasks.getTask(i);
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
