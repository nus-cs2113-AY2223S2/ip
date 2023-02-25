package Duke;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * Storage class to manage the saving of task data when the user exits, and creates a starting array with tasks from the
 * previous instance
 */
public class Storage {

    static String filePath = "data/tasklist.txt";

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Writes the current data in the task list to an external text file
     * @param filePath Path of text file to write to
     * @param list  Array list of type Task to read from to write to the text file
     */
    public static void writeFile(String filePath, ArrayList<Task> list) {



        try {
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            for (Task task : list) {


                if (Objects.equals(task.taskType, "D")) {
                    Deadline newDeadline = (Deadline) task;
                    writeFile.write(newDeadline.taskType + "|" + newDeadline.isCompleted + "|" + newDeadline.taskName + "|" + newDeadline.endTime + System.lineSeparator());
                } else if (Objects.equals(task.taskType, "E")) {
                    Event newEvent = (Event) task;
                    writeFile.write(newEvent.taskType + "|" + newEvent.isCompleted + "|" + newEvent.taskName + "|" + newEvent.startTime + "|" + newEvent.endTime + System.lineSeparator());
                } else if (Objects.equals(task.taskType, "T")) {
                    writeFile.write(task.taskType + "|" + task.isCompleted + "|" + task.taskName + System.lineSeparator());
                }

            }
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Error writing file!");
        }
    }

    /**
     * Reads from an external text file to generate a task list that the user starts with
     * @param filePath Path of file to read from
     * @param list Array list of tasks for the user to start with
     * @throws FileNotFoundException If text file is not found or is not in the required format
     */
    public static void readFile(String filePath, ArrayList<Task> list) throws FileNotFoundException {


        File savedFile = new File(filePath);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }
        try {
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating new file!");
        }

        Scanner s = new Scanner(new FileInputStream((filePath)));
        try {
            while (s.hasNext()) {
                String command = s.nextLine();
                String[] inputCommands = command.split("\\|");
                String fileType = inputCommands[0];
                boolean isCompleted = true;
                if (Objects.equals(inputCommands[1], "false")) {
                    isCompleted = false;
                }


                if (Objects.equals(fileType, "D")) {
                    Deadline newDeadline = new Deadline(inputCommands[2]);
                    newDeadline.setAsDeadline();
                    newDeadline.setTaskProgress(isCompleted);
                    newDeadline.setEndTime(inputCommands[3]);
                    list.add(newDeadline);
                } else if (Objects.equals(fileType, "E")) {
                    Event newEvent = new Event(inputCommands[2]);
                    newEvent.setAsEvent();
                    newEvent.setTaskProgress(isCompleted);
                    newEvent.setStartTime(inputCommands[3]);
                    newEvent.setEndTime(inputCommands[4]);
                    list.add(newEvent);
                } else if (Objects.equals(fileType, "T")) {
                    Task newTask = new Task(inputCommands[2]);
                    newTask.setTaskProgress(isCompleted);
                    list.add(newTask);
                } else
                    throw new FileNotFoundException();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Tasklist.txt file has been corrupted!");
        }
    }
}

