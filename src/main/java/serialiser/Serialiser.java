package serialiser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import task.ToDo;
import task.Deadline;
import task.Event;
import task.ITaskController;
import task.Task;

public class Serialiser implements ISerialiser{
    private final String fileDirectory = "data/duke.txt";
    private final Path path = Paths.get(fileDirectory);
    private Scanner scanner;
    
    public Serialiser () throws IOException {
        scanner = new Scanner(path);
    }
    @Override
    public void deserialiseFile(ITaskController taskController) {
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
    @Override
    public void serialiseFile(ITaskController taskController) {
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
    
}
