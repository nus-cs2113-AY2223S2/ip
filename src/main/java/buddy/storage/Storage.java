package buddy.storage;

import buddy.Buddy;
import buddy.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import buddy.tasks.Task;
import buddy.tasks.Todo;
import buddy.tasks.Deadline;
import buddy.tasks.Event;

public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage class
     *
     * @param filePath Path to the file where it is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new directory and a new file if they don't exist
     */
    public void createFile() {
        File directory = new File(filePath);
        File newFile = new File(filePath + "/BuddyTaskList.txt");

        try {
            if (directory.mkdirs()) {
                System.out.println("Directory has been created :)");
            }

            if (newFile.createNewFile()) {
                System.out.println("File has been created :)");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file :(");
            e.printStackTrace();
        }
    }

    /**
     * Updates the file by overwriting it with the updated task list
     *
     * @param taskList List of tasks
     * @throws IOException If there is an error with the hard disk
     */
    public void updateFile(TaskList taskList) throws IOException {
        FileWriter overwriteFile = new FileWriter(filePath + "/BuddyTaskList.txt");
        for (Task task : taskList) {
            String taskType = task.getType();
            String taskName = task.getTaskName();
            String taskStatus = task.getStatusIcon();

            switch (taskType) {
                case "T":
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "\n");
                    break;
                case "D":
                    Deadline deadlineTask = (Deadline) task;
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "$" + deadlineTask.getDeadline() + "\n");
                    break;
                case "E":
                    Event eventTask = (Event) task;
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "$" + eventTask.getStart() + "$" + eventTask.getEnd() + "\n");
                    break;
                default:
            }
        }
        overwriteFile.close();
    }

    /**
     * Loads the existing file with the task list
     *
     * @param taskList List of tasks
     * @throws FileNotFoundException If there is no file found
     */
    public void loadFile(TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath + "/BuddyTaskList.txt");
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskElements = line.split("\\$");
            String taskType = taskElements[0].trim();
            String taskStatus = taskElements[1].trim();
            String taskName = taskElements[2].trim();

            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(taskName);
                    if (taskStatus.equals("X")) {
                        newTodo.setDone(true);
                    }
                    taskList.add(newTodo);
                    Buddy.taskCount++;
                    break;

                case "D":
                    String deadline = taskElements[3].trim();
                    Deadline newDeadline = new Deadline(taskName, deadline);
                    if (taskStatus.equals("X")) {
                        newDeadline.setDone(true);
                    }
                    taskList.add(newDeadline);
                    Buddy.taskCount++;
                    break;

                case "E":
                    String start = taskElements[3].trim();
                    String end = taskElements[4].trim();
                    Event newEvent = new Event(taskName, start, end);

                    if (taskStatus.equals("X")) {
                        newEvent.setDone(true);
                    }
                    taskList.add(newEvent);
                    Buddy.taskCount++;
                    break;
                default:

            }
        }
    }
}
