package duke.storage;

import duke.Deadline;
import duke.Event;
import duke.ToDo;
import duke.exceptions.EventException;
import duke.exceptions.ToDoException;
import duke.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    ArrayList<Task> tasksList = new ArrayList<>();
    int numOfTask = 0;
    public static String filePath = "src/duke_list.txt";
    public Path path = Paths.get(filePath);
    public Storage(String userFilePath){
        filePath = userFilePath;
    }

    public ArrayList<Task> loadFile () throws FileNotFoundException {
        numOfTask = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            System.out.println(task);
        }
        return tasksList;
    }
    public static void printFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void createFile(String filePath){
        try{
            File f = new File(filePath);
            if(f.createNewFile()){
                System.out.println("Your file has been created: " + f.getName());
            } else {
                System.out.println("Your file already exists");
            }
        } catch(IOException e) {
            System.out.println("Error Occurred");
        }
    }
    public void saveToFile(ArrayList<Task> taskArrayList){
        try{
            writeToFile(taskArrayList);
        }catch (IOException e){
            System.out.println("An error has occurred" + e.getMessage());
        }
    }
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
