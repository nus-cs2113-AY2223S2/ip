package siri.general;

import siri.task.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static siri.Duke.indexOfTask;
import static siri.Duke.tasks;

/**
 * Represents the file used to store task lists data.
 */
public class Storage {

    protected String filePath;
    protected File file;

    /**
     * Store tasks that have been previously loaded.
     */
    protected ArrayList<Task> oldTasks;

    /**
     * Constructor that creates a new file and array list (old tasks).
     *
     * @param filePath
     */
    public Storage (String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
        this.oldTasks = new ArrayList<>();
    }

    /**
     * Create a new file if there is no existing file.
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    /**
     * Loads the {@code ArrayList<Task>} data from this storage file, and then returns it.
     * Returns an empty {@code ArrayList<Task>} if the file is empty.
     *
     * @return oldTasks Task List data stored in the storage file
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String[] command = s.nextLine().split(" | ", 2);
            readFile(command[0], command[1]);
            indexOfTask++;
        }
        return oldTasks;
    }

    /**
     * Read the file and load the tasks data into {@code oldTasks}.
     * Add different task types according to {@param taskCommand} and {@param taskD}.
     *
     * @param taskCommand arguments in the storage file
     * @param taskD arguments in the storage file
     * @throws IOException
     */
    private void readFile(String taskCommand, String taskD) throws IOException {
        String[] taskStatus = taskD.split(" | ", 3);//taskStatus[1] is the status
        String[] taskName = taskStatus[2].split("| ", 2);
        switch(taskCommand) {
        case "T":
            oldTasks.add(new ToDo(taskName[1].trim()));
            break;
        case "D":
            String[] deadlineTaskD = taskName[1].split(" /by: ",2);
            oldTasks.add(new Deadline(deadlineTaskD[0].trim(), deadlineTaskD[1].trim()));
            break;
        case "E":
            String[] eventName = taskName[1].split(" /from: ", 2);
            String[] eventDate = eventName[1].split(" to: ", 2);
            oldTasks.add(new Event(eventName[0].trim(), eventDate[0].trim(), eventDate[1].trim()));
            break;
        }

        if(taskStatus[1].equals("X")){
            oldTasks.get(indexOfTask).setDone(true);
        } else {
            oldTasks.get(indexOfTask).setDone(false);
        }
    }

    /**
     * Add task to the storage file.
     *
     * @param taskToAdd formatted task description according to user input
     * @throws IOException
     */
    public void appendToFile(String taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd + System.lineSeparator());
        fw.close();
    }

    /**
     * Update the task list in the storage file at the end of the application.
     *
     * @throws IOException
     */
    public void overwriteEntireList() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < indexOfTask; ++i){
            fw.write(tasks.getTaskList().get(i).toFileString() + System.lineSeparator());
        }
        fw.close();
    }
}