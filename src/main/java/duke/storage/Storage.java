package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private File dataFile;
    protected final String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(this.filePath);
        this.tasks = new ArrayList<>();
    }

    public File getDataFile() {
        return dataFile;
    }

    public void createFile() throws IOException {
        File file = new File(this.filePath);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }
        if (dataFile.length() == 0) {
            throw new IOException();
        }
        ArrayList<String> dataItems = (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    public ArrayList<Task> loadData() throws IOException {
        try {
            ArrayList<String> dataItems = readFile();
            tasks = parse(dataItems);
        } catch (FileNotFoundException e) {
            System.out.println(Ui.LINE + e);
            try {
                createFile();
            } catch (IOException error) {
                System.out.println("Meow! Failed to create a file: " + e.getMessage()
                        + System.lineSeparator() + Ui.LINE);
            }
            System.out.println("A new local file created to store your tasks!"
                    + System.lineSeparator() + Ui.LINE);
        } catch (IOException e) {
            System.out.println("Empty local file! Time to add some tasks!"
                    + System.lineSeparator() + Ui.LINE);
        }
        return tasks;
    }

    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            String taskType = getTaskType(line);
            String taskDescription = getTaskDescription(line);
            String taskStatus = getTaskStatus(line);
            switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDescription);
                if (taskStatus.equals("X")) {
                    todo.setDone();
                }
                allTasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(getTrimmedDescription(taskDescription),
                        getDeadline(taskDescription));
                if (taskStatus.equals("X")) {
                    deadline.setDone();
                }
                allTasks.add(deadline);
                break;
            case "E":
                Event event = new Event(getTrimmedDescription(taskDescription),
                        getEventDuration(taskDescription)[0], getEventDuration(taskDescription)[1]);
                if (taskStatus.equals("X")) {
                    event.setDone();
                }
                allTasks.add(event);
                break;
            default:
                System.out.println("Unknown task encountered. Skipping");
                break;
            }
        }
        return allTasks;
    }

    private String getTaskDescription(String line) {
        String taskDescription = line.substring(7);
        return taskDescription;
    }

    private String getTaskType(String line) {
        String taskType = line.substring(0, 2);
        taskType = taskType.replace("[", "");
        taskType = taskType.replace("]", "");
        return taskType;
    }

    private String getTaskStatus(String line) {
        String taskStatus = line.substring(3, 5);
        taskStatus = taskStatus.replace("[", "");
        taskStatus = taskStatus.replace("]", "");
        return taskStatus;
    }

    private String getDeadline(String taskDescription) {
 //       String deadline = "/by " + taskDescription.split("by: ", 2)[1];
        String deadline = taskDescription.split("\\(by: ", 2)[1].replace(")", "");
        return deadline;
    }

    private String[] getEventDuration(String taskDescription) {
        String[] temp = taskDescription.split("to: ", 2);
        String to = temp[1];
        String from = temp[0].split("from: ", 2)[1];
        temp[1] = "/to" + to;
        temp[0] = "/from" + from;
        return temp;
    }

    private String getTrimmedDescription(String taskDescription) {
        String trimmedDescription = taskDescription.split(" \\(", 2)[0];
        return trimmedDescription;
    }

           /* try {
    } catch (IOException e) {
        System.out.println(Ui.LINE + e + System.lineSeparator());
        try {
            obj.originalFile.createFile();
        } catch (IOException error) {
            System.out.println("Meow! Failed to create a file: " + e.getMessage());
        }
        System.out.println("File Created!");
    }*/
}