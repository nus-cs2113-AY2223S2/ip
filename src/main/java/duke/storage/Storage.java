package duke.storage;

import duke.Deadline;
import duke.Event;
import duke.ToDo;
import duke.exceptions.EventException;
import duke.exceptions.StorageFileException;
import duke.exceptions.ToDoException;
import duke.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the file used to store the task list data.
 */
public class Storage {
    /** Task list used to store all the tasks added/ edited by the user*/
    protected ArrayList<Task> tasksList = new ArrayList<>();
    int numOfTask = 0;

    /** Default file path set in the event that the user doesn't provide a file name.*/
    public static String filePath = "duke_list.txt";
    public Path path = Paths.get(filePath);

    /**
     * Reassigns the default file path to the user's defined file path.
     *
     * @param userFilePath the user's defined file path.
     */
    public Storage(String userFilePath){
        filePath = userFilePath;
    }

    /**
     * Loads task list data from the storage file and returns it.
     *
     * @throws FileNotFoundException if the file cannot be located or does not exist.
     */
    public ArrayList<Task> loadFile () throws StorageFileException {
        numOfTask = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(path);
        } catch (IOException e) {
            return null;
        }
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskSet = task.trim().split("\\|");
            String type = taskSet[0].trim();
            boolean isDone = taskSet[1].trim().equals("1");
            String description = taskSet[2].trim();
            numOfTask += 1;
            switch (type) {
                case "T":
                    try {
                        ToDo toDoTask = new ToDo(description, isDone);
                        tasksList.add(toDoTask);
                    } catch (ToDoException e) {
                        System.out.println("The description of a todo cannot be empty!");
                    }
                    break;
                case "D":
                    String deadline = taskSet[3].trim();
                    Deadline deadlineTask = new Deadline(description, isDone, deadline);
                    tasksList.add(deadlineTask);
                    break;
                case "E":
                    String fromDate = taskSet[3].trim();
                    String toDate = taskSet[4].trim();
                    try {
                        Event eventTask = new Event(description, isDone, fromDate, toDate);
                        tasksList.add(eventTask);
                    } catch (EventException e) {
                        System.out.println("To field is empty!");
                        break;
                    }
                default:
            }
        }
        return tasksList;
    }

    /**
     * Creates a new storage file.
     *
     * @param filePath the location of the storage file.
     * @throws IOException if there were errors creating the file.
     */
    public void createFile(String filePath){
        try{
            File f = new File(filePath);
            if(f.createNewFile()) {
                System.out.println("Your file has been created: " + f.getName());
            }
        } catch(IOException e) {
            System.out.println("Error Occurred");
        }
    }

    /**
     * Saves changes made to the task list to the storage file by calling the writeToFile method.
     *
     * @param taskArrayList an Array List containing Tasks added by the user.
     * @throws IOException if there were errors writing to the file.
     */
    public void saveToFile(ArrayList<Task> taskArrayList){
        try{
            writeToFile(taskArrayList);
        }catch (IOException e){
            System.out.println("An error has occurred" + e.getMessage());
        }
    }

    /**
     * Writes all the data in the task list into the storage file.
     *
     * @param taskArrayList an Array List containing Tasks added by the user.
     * @throws IOException if there were errors writing to the file.
     */
    public void writeToFile(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task t : taskArrayList) {
            String type = t.getType();
            switch(type){
                case "T":
                    String targetT =  t.getType() + " | " + t.getStatusIconSave() + " | " + t.getDescription();
                    fw.write(targetT+ "\n");
                    break;
                case "D":
                    String targetD =  t.getType() + " | " + t.getStatusIconSave() + " | " + t.getDescription() + " | " +
                            t.getDeadlineSave();
                    fw.write(targetD + "\n");
                    break;
                case "E":
                    String targetE =  t.getType() + " | " + t.getStatusIconSave() + " | " + t.getDescription() + " | " +
                            t.getPeriodSave();
                    fw.write(targetE + "\n");
                    break;
            }
        }
        fw.close();
    }

}
