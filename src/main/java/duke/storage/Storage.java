package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private TaskList tasks;

    public Storage(String filePath, TaskList tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public void createSavedTasksFile() {
        File savedTasks = new File(filePath);
        if (!savedTasks.exists()) {
            try {
                savedTasks.createNewFile();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public void loadSavedTasks() throws FileNotFoundException {
        File savedTasks = new File(filePath);
        if (savedTasks.exists()) {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                String[] splitTask = task.split(" \\| ");
                addSavedTasksToList(splitTask);
            }
        }
    }

    public void addSavedTasksToList(String[] splitTask) {
        String taskType = splitTask[0];
        boolean isDone = Boolean.parseBoolean(splitTask[1]);
        if (taskType.equals("T") || taskType.equals("D") || taskType.equals("E")) {
            String taskDescription = splitTask[2];
            if (taskType.equals("T")) {
                tasks.addTodo(taskDescription, isDone);
            } else if (taskType.equals("D") || taskType.equals("E")) {
                String date = splitTask[3];
                if (taskType.equals("D")) {
                    tasks.addDeadline(taskDescription + " /by " + date, isDone);
                } else {
                    tasks.addEvent(taskDescription + " " + date, isDone);
                }
            }
        }
    }

    public void saveTaskToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(formatTask());
        writer.close();
    }

    public String formatTask() {
        String formattedTask = "";
        for (int i = 1; i <= tasks.getNumTasks(); i++) {
            Task task = tasks.getTasks().get(i);
            String taskType = task.getTaskType();
            String taskStatus = task.getStatusIcon();
            String taskDescription = task.getDescription();
            if (taskStatus.equals("X")) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (taskType.equals("T")) {
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + System.lineSeparator();
            } else if (taskType.equals("D")){
                Deadline deadline = (Deadline) task;
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + " | " + deadline.getDeadline() + System.lineSeparator();
            } else {
                Event event = (Event) task;
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + " | from: " + event.getFromDate() + " to: " + event.getToDate() + System.lineSeparator();
            }
        }
        return formattedTask;
    }

}