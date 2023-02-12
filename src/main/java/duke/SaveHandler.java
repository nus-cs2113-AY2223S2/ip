package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SaveHandler {
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new java.io.File( "./duke.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                tasks.add(stringToTask(data));
            }
        } catch (FileNotFoundException fe) {
            File file = new java.io.File( "./duke.txt");
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error creating new data file.\n"+ioe);
                System.exit(-1);
            }
        } catch (StreamCorruptedException e) {
            System.out.println("Corrupted data file.");
            System.exit(-1);
        }

        return tasks;
    }

    public void save(ArrayList<Task> list) {
        StringBuilder data = new StringBuilder();
        for (Task task : list) {
            data.append(taskToString(task));
        }

        try {
            FileWriter writer = new FileWriter("./duke.txt");
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data file.");
            System.exit(-1);
        }
    }

    private Task stringToTask(String taskString) throws StreamCorruptedException {
        String[] parts = taskString.split("/");
        if (Objects.equals(parts[0], "T")) {
            Todo task = new Todo(parts[2]);
            if (Objects.equals(parts[1], "1")) {
                task.setIsDone(true);
            }
            return task;
        } else if (Objects.equals(parts[0], "D")) {
            Deadline task = new Deadline(parts[2], parts[3]);
            if (Objects.equals(parts[1], "1")) {
                task.setIsDone(true);
            }
            return task;
        } else if (Objects.equals(parts[0], "E")) {
            Event task = new Event(parts[2], parts[3], parts[4]);
            if (Objects.equals(parts[1], "1")) {
                task.setIsDone(true);
            }
            return task;
        } else {
            throw new StreamCorruptedException();
        }
    }

    private String taskToString(Task task) {
        String output = "";
        switch (task.getClass().getName()) {
            case "duke.Task":
            case "duke.Todo":
                output = "T/" + (task.isDone ? "1/" : "0/") + task.description + "\n";
                break;
            case "duke.Deadline":
                output = "D/" + (task.isDone ? "1/" : "0/") + task.description + "/"
                        + ((Deadline) task).by + "\n";
                break;
            case "duke.Event":
                output = "E/" + (task.isDone ? "1/" : "0/") + task.description + "/"
                        + ((Event) task).by + "/" + ((Event) task).from + "\n";
                break;
        }
        return output;
    }
}
