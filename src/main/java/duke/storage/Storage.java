package duke.storage;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
//    write to file
    public static final String filePath = "duke.txt";
    public static void writeToFile(TaskList tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toTextFileFormat());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void readFileData(TaskList tasks){
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            String data;
            while (s.hasNext()) {
                data = s.nextLine();
                addFileDataToList(tasks, data);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static void addFileDataToList(TaskList tasks, String data){
        String[] inputData = data.split("/");
        String taskType = inputData[0];
        Task loadedTask = null;
        switch (taskType){
            case "todo":
                loadedTask = new Todo(inputData[1], Boolean.parseBoolean(inputData[2]));
                break;

            case "deadline":
                loadedTask = new Deadline(inputData[1], inputData[3], Boolean.parseBoolean(inputData[2]));
                break;

            case "event":
                loadedTask = new Event(inputData[1], inputData[3], inputData[4], Boolean.parseBoolean(inputData[2]));
        }
        tasks.addTask(loadedTask);
    }

}
