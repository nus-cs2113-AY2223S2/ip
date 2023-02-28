package serialiser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.DukeException;
import task.ToDo;
import ui.IUi;
import ui.Ui;
import task.Deadline;
import task.Event;
import task.ITaskController;
import task.Task;

public class Serialiser implements ISerialiser{
    private final String fileDirectory = "data/duke.txt";
    private final Path path = Paths.get(fileDirectory);
    private Scanner scanner;
    /**
     * Reads text file into task objects
     * @param taskController
     */
    private void deserialiseFile(ITaskController taskController) {
        String line = null;
        //read line by line
        while(scanner.hasNextLine()){
            try {
                //process each line
                line = scanner.nextLine();
                // Check if the line starts with T, D, E
                Task target = getTaskObject(line);
                // call respective task to make sense of the argument
                target.fromString(line);
                // add task to the controller
                taskController.addTask(target);
            } catch (IOException | SerialiseException e) {
                System.out.println("ERROR NOT UNDERSTOOD: " + e.getMessage());
            }
        }
    }
    /**
     * Returns task object based on string provided
     * @param line
     * @return
     * @throws IOException
     */
    private Task getTaskObject(String line) throws IOException {
        switch (line.charAt(0)) {
        case 'T':
            return new ToDo();
        case 'D':
            return new Deadline();
        case 'E':
            return new Event();
        default:
            throw new IOException();
        }
    }
    /**
     * Writes task object information into text file
     * @param taskController
     */
    private void serialiseFile(ITaskController taskController) {
        String storageString = "";
        while (!taskController.isEmpty()) {
            storageString = storageString + taskController.removeTaskForStorage().toStorageString() + '\n';
        }
        byte[] storageStringToBytes = storageString.getBytes();
        try {
            Files.write(path, storageStringToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void loadDataFile(ITaskController taskController) throws DukeException{
        try {
            checkDataDirectory();
            scanner = new Scanner(path);
            deserialiseFile(taskController);
        } catch (IOException e) {
            throw new DukeException("Invalid path", new IllegalAccessError());
        }
    }
    @Override
    public void saveDataFile(ITaskController taskController) {
        serialiseFile(taskController);
    }
    /**
     * Checks for existence of data directory.
     * Also checks for existence of file in directory.
     * @throws IOException
     */
    private void checkDataDirectory() throws IOException{
        IUi ui = Ui.getInstance();
        File directory = new File("data");
        if (!directory.isDirectory()) {
            // Then directory does not exist and need to create one
            ui.printSystemErrorMessage("The data directory does not exist yet!!\nProceeding to create one now...");
            directory.mkdir();
            checkDataFile();
            ui.printSystemMessage("Directory creation success");
        }
        else {
            checkDataFile();
        }
    }
    /**
     * Checks for existence of data file.
     * @throws IOException
     */
    private void checkDataFile() throws IOException{
        IUi ui = Ui.getInstance();
        File file = new File("data/duke.txt");
        if (!file.isFile()) {
            // Then directory does not exist and need to create one
            ui.printSystemErrorMessage("The data file does not exist yet!!\nProceeding to create one now...");
            file.createNewFile();
            ui.printSystemMessage("file creation success");
        }
    }
}
