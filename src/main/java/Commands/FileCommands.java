package Commands;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;

public class FileCommands {

    public static File dukeFile = new File("duke.txt");

    /**
     * Reads the existing file data of the user's tasks, and loads the data into the current task list
     * @param taskList the ArrayList of the user's tasks
     * @throws IOException
     */
    public static void readFileData(ArrayList<Task> taskList) throws IOException {
        // Check if duke.txt file exists, and creates a new one if it does not, e.g.
        // First time user
        if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }

        Scanner scan = new Scanner(dukeFile); // read contents

        while (scan.hasNext()) {
            String[] splitLine = scan.nextLine().split("\\|"); // Split by / to get the relevant task details

            String taskType = splitLine[0];
            String taskStatus = splitLine[1];
            String taskDescription = splitLine[2];

            switch (taskType) {
                case "T":
                    taskList.add(new Todo(taskDescription));
                    break;

                case "D":
                    //String[] commandDeadlineSplit = taskDescription.split(" /by", 2);
                    String deadlineDescription = taskDescription; // Get description of the user input
                    String byDate = splitLine[3]; // Deadline of the user input
                    taskList.add(new Deadline(deadlineDescription, byDate));
                    break;

                case "E":
                    //String[] commandEventSplit = taskDescription.split(" /from | /to");
                    String eventDescription = taskDescription; // Get event description from user input
                    String eventFromDate = splitLine[3]; // Get event from date
                    String eventToDate = splitLine[4]; // Get event to date
                    taskList.add(new Event(eventDescription, eventFromDate, eventToDate)); // Add the new event to
                                                                                           // arraylist
                    break;
            }
            if (taskStatus.equals("1")) {
                Task newTask = taskList.get(taskList.size() - 1);
                newTask.markAsDone(); // mark those done as done
            }
        }
        scan.close();
    }

    /**
     * Saves the user's tasks into the duke.txt file 
     * 
     * @param taskList the ArrayList of the user's tasks
     * @throws IOException
     */
    public static void saveFile(ArrayList<Task> taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter("duke.txt");
            // Format tasks to save
            for (int taskNumber = 0; taskNumber < taskList.size(); taskNumber++) {
                Task saveFormat = taskList.get(taskNumber);
                fw.write(saveFormat.formatTask() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("\tOops! Please try again.");
        }
    }

}
