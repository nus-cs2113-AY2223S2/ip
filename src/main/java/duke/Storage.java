package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    private void createFile() throws IOException {
        if (file.exists()) {
            return;
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    private ArrayList<String> readFile() throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (file.length() == 0) {
            throw new IOException();
        }

        ArrayList<String> data = (ArrayList) Files.readAllLines(file.toPath(), Charset.defaultCharset());
        return data;
    }

    public void writeFile(ArrayList<Task> tasks) throws IOException {
        createFile();
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            String textToAdd = task.toFile() + "\n";
            fw.write(textToAdd);
        }

        fw.close();
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<String> data = readFile();
        ArrayList<Task> tasks = parse(data);

        return tasks;
    }

    private ArrayList<Task> parse(ArrayList<String> data) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : data) {
            String taskType = getTaskType(line);
            String taskStatus = getTaskStatus(line);

            switch (taskType) {
            case "T":
                String toDoTaskName = getToDoTaskName(line);
                tasks.add(new Todo(toDoTaskName));
                break;
            case "D":
                String[] deadlineDetails = getDeadlineDetails(line);
                String deadlineTaskName = deadlineDetails[0];
                String by = deadlineDetails[1];
                tasks.add(new Deadline(deadlineTaskName, by));
                break;
            case "E":
                String[] eventDetails = getEventDetails(line);
                String eventTaskName = eventDetails[0];
                String from = eventDetails[1];
                String to = eventDetails[2];
                tasks.add(new Event(eventTaskName, from, to));
            default:
                break;
            }

            if (taskStatus.startsWith("1")) {
                tasks.get(tasks.size() - 1).setCompleted();
            }
        }

        return tasks;
    }
    private String getTaskType(String line) {
        String[] words = line.split(" \\| ", 2);
        String taskType = words[0];
        return taskType;
    }

    private String getTaskStatus(String line) {
        String[] words = line.split(" \\| ", 3);
        String taskStatus = words[1];
        return taskStatus;
    }

    private String getToDoTaskName(String line) {
        String[] words = line.split(" \\| ", 3);
        String toDoTaskName = words[2];
        return toDoTaskName;
    }
    private String[] getDeadlineDetails(String line) {
        String[] words = line.split(" \\| ", 4);
        String deadlineTaskName = words[2];
        String by = words[3];
        String[] deadlineDetails = new String[]{deadlineTaskName, by};

        return deadlineDetails;
    }

    private String[] getEventDetails(String line) {
        String[] words = line.split(" \\| ", 5);
        String eventTaskName = words[2];
        String from = words[3];
        String by = words[4];
        String[] eventDetails = new String[]{eventTaskName, from, by};

        return eventDetails;
    }
}