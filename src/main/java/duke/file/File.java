package duke.file;

import duke.task.Task;
import duke.taskTypes.Deadline;
import duke.taskTypes.Event;
import duke.taskTypes.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Custom File class to read and write to a save file.
 * The save file will be created and maintained in the root folder of where the Duke JAR file is located.
 */
public class File {
    static final String FILE_PATH = "./duke.txt";

    public static void loadFileContent(ArrayList<Task> taskList) throws IOException {
        java.io.File f = new java.io.File(FILE_PATH);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] task = line.split("\\|");
            String taskType        = task[0];
            String taskDoneStatus  = task[1];
            String taskDescription = task[2];

            switch (taskType) {
                case "T":
                    Todo new_Todo = new Todo(taskDescription);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_Todo.markDone();
                    }
                    taskList.add(new_Todo);

                    break;

                case "D":
                    String taskBy = task[3];
                    Deadline new_deadline = new Deadline(taskDescription, taskBy);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_deadline.markDone();
                    }

                    taskList.add(new_deadline);
                    break;

                case "E":
                    String[] taskDuration = task[3].split(" ",2);
                    Event new_event = new Event(taskDescription, taskDuration[0], taskDuration[1]);
                    if (taskDoneStatus.equalsIgnoreCase("X")) {
                        new_event.markDone();
                    }

                    taskList.add(new_event);
                    break;
            }
        }
    }

    /**
     * Writes the task list to a save file
     *
     * @param taskList The taskList to update the save file
     */
    public static void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (Task taskToAppend : taskList) {
            String toWrite;

            String taskType = taskToAppend.getTypeIcon();
            String taskDone = taskToAppend.getDoneIcon();
            String taskDescription = taskToAppend.getDescription();

            toWrite = taskType + "|" + taskDone + "|" + taskDescription;

            if (taskToAppend.isDeadline()) {
                String taskDeadline = taskToAppend.getBy();
                toWrite = toWrite + "|" + taskDeadline;
            } else if (taskToAppend.isEvent()) {
                String taskFrom = taskToAppend.getFrom();
                String taskTo = taskToAppend.getTo();
                toWrite = toWrite + "|" + taskFrom + " " + taskTo;
            }

            toWrite = toWrite + '\n';
            fw.write(toWrite);
        }
        fw.close();
    }
}
