package Support;

import BasisSupport.Status;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    // This class contains method related to read tasks in and store tasks in a txt file
    private static String filePath = "";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //Put all the things in the tasks to the file
        for (int i = 0; i < tasks.getSize(); i++) {
            fw.write((tasks.getTask(i)).showTask() + "\n");
        }
        fw.close();
    }

    public static TaskList readFromFile(TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {

            String taskLine = s.nextLine();
            String[] taskLineList = taskLine.split("");
            Status taskStatus;
            boolean isDone = false;
            String description = taskLine.substring(7);

            if (taskLineList[1].equals("T")) {
                taskStatus = Status.T;
            } else if (taskLineList[1].equals("E")) {
                taskStatus = Status.E;
            } else {
                taskStatus = Status.D;
            }

            if (taskLineList[4].equals("X")) {
                isDone = true;
            }
            Task currentTask = new Task(description, taskStatus, isDone);
            tasks.addTask(currentTask);
        }
        return tasks;
    }

    public static void saveFile(TaskList tasks) {
        try {
            Storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("The path is wrong, try again: " + e.getMessage());
        }
    }

    // We will make a directory first if the directory doesn't exist
    public static void makeDirectory() {
        File data = new File("data");
        boolean created = data.mkdir();
        if(!created) {
            System.out.println("Failed to create repository");
        }
    }

}