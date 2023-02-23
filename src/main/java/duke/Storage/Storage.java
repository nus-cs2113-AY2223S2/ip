package duke.Storage;

import duke.tasks.*;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import static duke.tasks.Deadline.dateTimeFormatter;
import static duke.main.Duke.taskCount;

/**
 * Represents the database of the tasks list in local device
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * to create new file to store task if there is no previous database
     *
     * @throws IOException if  file crash during file creation
     */

    public void initFile() throws IOException {
        File newFile = new File(this.filepath);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
    }

    /**
     * Load previously stored file to Duke application
     *
     * @param tasksList
     * @throws FileNotFoundException if file not found
     */
    public void loadFile(TaskList tasksList) throws FileNotFoundException, ParseException, DataFormatException {

        File file = new File(this.filepath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] args = input.split(",", 5);
            String type = args[0];
            Task newTask;
            switch (type) {
            case "T":
                newTask = new Todo(args[2]);
                tasksList.add(newTask);
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            case "D":
                newTask = new Deadline(args[2], LocalDateTime.parse(args[3].trim(), dateTimeFormatter));
                tasksList.add(newTask);
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            case "E":
                newTask = new Event(args[2], LocalDateTime.parse(args[3].trim(), dateTimeFormatter), LocalDateTime.parse(args[4].trim(), dateTimeFormatter));
                tasksList.add(newTask);
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            }
        }


    }

    /**
     * Update database whenever there is changes in tasks
     * eg: add, delete, unmark , mark
     *
     * @param tasksList
     * @throws IOException if file crash during overwriting
     */
    public static void updateFile(TaskList tasksList) throws IOException {
        FileWriter overwriteFile = new FileWriter("./taskslist.csv");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasksList.get(i);
            String type = task.getType();
            String taskName = task.getTaskName();
            String status = task.getStatusIcon();
            switch (type) {
            case "T":
                overwriteFile.write(type + "," + status + "," + taskName + "\n");
                break;
            case "D":
                Deadline taskD = (Deadline) task;
                overwriteFile.write(type + "," + status + "," + taskName + "," + taskD.getDeadline() + "\n");
                break;
            case "E":
                Event taskE = (Event) task;
                overwriteFile.write(
                        type + "," + status + "," + taskName + "," + taskE.getStart() + "," + taskE.getEnd() + "\n");
                break;
            default:
            }
        }
        overwriteFile.close();
    }
}
