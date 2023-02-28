package psyduck.storage;

import psyduck.tasklist.TaskList;
import psyduck.task.*;
import psyduck.ui.Ui;
import psyduck.command.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    protected Ui ui;
    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public static File createFile(String filepath) {
        try {
            File f = new File(filepath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
            }
            return f;
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return null;
    }

    public static void readFile(String filePath, TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] fullLine = s.nextLine().split(" / ");
            String taskType = fullLine[0];
            String isTaskDone = fullLine[1];
            String description = fullLine[2];
            switch (taskType) {
            case "T":
                ToDo todo = new ToDo(description);
                tasks.addTask(todo);
                TaskList.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            case "D":
                String by = fullLine[3];
                Deadline deadline = new Deadline(description, by);
                tasks.addTask(deadline);
                TaskList.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            case "E":
                String from = fullLine[3];
                String to = fullLine[4];
                Event event = new Event(description, from, to);
                tasks.addTask(event);
                TaskList.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            default:
                //invalid task type/wrong format ignored
            }

        }
    }
    public static void writeToFile(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(createFile(filePath));
        for (int i = 1; i < TaskList.getTaskCount() + 1; i++) {
            if (TaskList.getTask(i).getType().equals("T")) {
                fw.write( TaskList.getTask(i).getType() + " / "
                        + TaskList.getTask(i).isDone() + " / "
                        + TaskList.getTask(i).getDescription() + " / "
                        + System.lineSeparator());
            } else if (TaskList.getTask(i).getType().equals("D")) {
                Deadline temp = (Deadline) TaskList.getTask(i);
                fw.write( temp.getType() + " / "
                        + temp.isDone() + " / "
                        + temp.getDescription() + " / "
                        + temp.getBy() + " / "
                        + System.lineSeparator());
            } else if (TaskList.getTask(i).getType().equals("E")) {
                Event temp = (Event) TaskList.getTask(i);
                fw.write( temp.getType() + " / "
                        + temp.isDone() + " / "
                        + temp.getDescription() + " / "
                        + temp.getFrom() + " / "
                        + temp.getTo() + " / "
                        + System.lineSeparator());
            } else {
                System.out.println("An error has occurred, file writing unsuccessful");
                fw.close();
                return;
            }

        }
        fw.close();
    }

}
