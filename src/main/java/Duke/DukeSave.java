package Duke;

import java.io.File;
import java.io.FileNotFoundException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DukeSave {
    /**
     * Divider used to separate details of a task saved in file
     */
    private static final String SAVEFILESEPARATOR = ", ";
    private static String filePath = "DukeTaskList.txt";
    private static File textFile;
    private static Scanner textFileScanner;

    private static void setupFile() {
        textFile = new File(filePath);

        try {
            textFileScanner = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
    }
    
    /**
     * Saves an ArrayList of tasks to DukeTaskList.txt file
     * 
     * @param list ArrayList of tasks
     */
    public static void saveDukeToFile(ArrayList<DukeTasks> list) {
        String writeToFile = "";
        
        for (DukeTasks dukeTasks : list) {
            // extracting details from task object
            String[] dataToTextFile= {dukeTasks.getTaskType(), dukeTasks.isDone.toString(), dukeTasks.description};
            if (dukeTasks instanceof DukeDeadlines) {
                dataToTextFile[2] += "/by" + ((DukeDeadlines) dukeTasks).getBy();
            } else if (dukeTasks instanceof DukeEvents) {
                dataToTextFile[2] += "/from" + ((DukeEvents) dukeTasks).getFromDetail() + "/to" + ((DukeEvents) dukeTasks).getToDetail();
            }

            // save task details split by regex ", "
            writeToFile += dataToTextFile[0] + SAVEFILESEPARATOR + dataToTextFile[1] + SAVEFILESEPARATOR + dataToTextFile[2] + System.lineSeparator();
        }

        // write task list to text file
        try {
            textFile = new File(filePath);
            FileWriter dukeWriter = new FileWriter(textFile);
            dukeWriter.write(writeToFile);
            dukeWriter.close();
        } catch (IOException e) {
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
    }

    /**
     * Returns an ArrayList of tasks from DukeTaskList.txt file
     * 
     * @return ArrayList of tasks
     */
    public static ArrayList<DukeTasks> loadDukeFromFile() {
        ArrayList<DukeTasks> savedList = new ArrayList<>();
        setupFile();

        while (textFileScanner.hasNext()) {
            // {taskType, taskStatus, taskName}
            String[] loadTaskInfo = new String[3];
            String[] separateTaskDetails;
            loadTaskInfo = textFileScanner.nextLine().split(SAVEFILESEPARATOR, 3);
            
            // create tasks individually
            switch (loadTaskInfo[0]) {
            case "T":
                savedList.add(new DukeToDos(loadTaskInfo[2]));
                break;
        
            case "D":
                separateTaskDetails = loadTaskInfo[2].split("/by", 2);
                savedList.add(new DukeDeadlines(separateTaskDetails[0], separateTaskDetails[1]));
                break;
                
            case "E":
                separateTaskDetails = loadTaskInfo[2].split("/from", 2);
                savedList.add(new DukeEvents(separateTaskDetails[0], separateTaskDetails[1]));
                break;
        
            default:
                break;
            }

            // mark task if done
            if (Boolean.parseBoolean(loadTaskInfo[1])) {
                savedList.get(savedList.size() - 1).markDone();
            }
        }

        return savedList;
    }
}
