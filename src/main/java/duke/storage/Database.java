package duke.storage;

import duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that handles the saving of the task list as a file and loading the tasks from the file.
 */
public class Database {

    public ArrayList<Task> taskList;
    private String databaseDirectory;
    private String filePath;

    private final static String DUKE_TXT_FILE_NAME = "duke.txt";

    /**
     * Constructor of the Database class that creates a new file and directory if they do not exist
     * and initialises and creates a new Arraylist of tasks with the tasks previously saved.
     */
    public Database() {
        taskList = new ArrayList<>();
        this.databaseDirectory= "./data/";
        this.filePath = this.databaseDirectory + DUKE_TXT_FILE_NAME;
        File directory = new File(this.databaseDirectory);
        File savedFile = new File(this.filePath);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
            initialisation(savedFile);
        } catch (IOException e) {
            System.out.println("Error while initialising database");
        }
    }

    /**
     * Appends the newly added task to the saved database txt file.
     *
     * @param userInput String of the task in a fixed format that has been newly added to the task list.
     * @throws IOException Executes when the FileWriter fails to append the task.
     */
    public void appendSaveTasks(String userInput) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(userInput + System.lineSeparator());
        fw.close();
    }

    /**
     * Updates the database when a task is being removed from the list or when a task is being marked/unmarked.
     *
     * @throws IOException Executes when the FileWriter fails to update the txt file.
     */
    public void updateDatabase() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); ++i){
            fw.write(taskList.get(i).taskInformation() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Initialises the Database by adding saved task from the saved txt file to the task list.
     *
     * @param savedFile The txt file that is being saved to store previous tasks on the list.
     * @throws IOException Executes when the scanner fails to scan or task list fails to add saved task.
     */
    private void initialisation(File savedFile) throws IOException{
        Scanner scan = new Scanner(savedFile);
        while (scan.hasNext()) {
            String taskInput = scan.nextLine();
            taskList.add(handleTask(taskInput));
        }
    }

    /**
     * Method used to convert the saved string from the saved file to a task object
     * so that it can be added to the list.
     *
     * @param taskInput The saved string being read from the file.
     * @return Returns the task object that is represented by the saved string.
     */
    private Task handleTask(String taskInput) {
        String[] components = taskInput.split(" , ");
        String command = components[0];
        String mark = components[1];
        String description = components[2];
        Task task = null;
        switch(command) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            String by = components[3];
            task = new Deadline(description, by);
            break;
        case "event":
            String from = components[3];
            String to = components[4];
            task = new Event(description, from, to);
            break;
        default:
        }
        updateMark(mark, task);
        return task;
    }

    /**
     * Method used to determine if the task is mark as done from the saved string in the saved file.
     *
     * @param mark The string that represents whether the task has been marked/unmarked.
     * @param task The task that is currently being added to the list.
     */
    private void updateMark(String mark, Task task) {
        if (mark.equals("true")) {
            task.setDone(true);
        } else {
            task.setDone(false);
        }
    }
}
