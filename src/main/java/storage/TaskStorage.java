package storage;

import command.Command;
import parser.Parser;
import task.Task;
import task.TaskList;
import exception.DukeException;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskStorage {
    private final String filePath;

    public TaskStorage(String filePath)  {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws java.io.IOException {
        File dir = new File("data");
        File f = new File("data/duke.txt");
        if (!dir.exists()) {
            System.out.println("Directory does not exist, creating a directory called data...");
            boolean success = dir.mkdir();
            if (success) {
                System.out.println("Directory data has been successfully created");
            }
        }
        if (!f.exists()) {
            System.out.println("File does not exist, creating a new file ./data/duke.txt");
           boolean success = f.createNewFile();
           if (success) {
               System.out.println("File duke.txt has been successfully created");
           }
        }

        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task t: tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("data/duke.txt");
        if (f.exists()) {
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String inputStr = s.nextLine();
                    Task taskToAdd = Parser.processSavedInput(inputStr);
                    tasks.add(taskToAdd);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception : Failed to read existing file " + e);
            }
        }
        return tasks;
    }
}
