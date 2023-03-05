package duke.instructions;

import duke.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    //static final String FILE_PATH = "data/tasks.txt";
    protected static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public static void writeTaskToFile(String filePath, ArrayList<Task> taskNameList){

        try{
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            for(Task tasks : taskNameList){
                String typeOfTask = tasks.getTaskType();
                switch(typeOfTask){
                case "D":
                    Deadline newDeadline = (Deadline) tasks;
                    writeFile.write("D | " + newDeadline.getStatusIcon() + " | " + newDeadline.getTaskList());
                    break;
                case "E":
                    Event newEvent = (Event) tasks;
                    writeFile.write("E | " + newEvent.getStatusIcon() + " | " + newEvent.getTaskList());
                    break;
                case "T":
                    Todo newToDo = (Todo) tasks;
                    writeFile.write("E | " + newToDo.getStatusIcon() + " | " + newToDo.getTaskList());
                    break;
                default:
                    System.out.println("Invalid inout, please enetr a valid command");
                }


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void readFile(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {

        File savedFile = new File(filePath);
        if(!savedFile.getParentFile().exists()){
            savedFile.getParentFile().mkdirs();

        }
        try{
            if(!savedFile.exists()){
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file!!!");
        }


    }
    public static void loadTaskFromFile(String filePath, ArrayList<Task> taskList) throws DukeException {
        try{
            readFile(filePath, taskList);
        }catch(java.io.FileNotFoundException e) {
            System.out.println("Error loading tasks from the file ");
        }

    }




}
