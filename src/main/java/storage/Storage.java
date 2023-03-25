package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import parser.Parser;
import Task.Task;
import TaskManager.TaskManager;

public class Storage {
    private String filePath;
    private Parser parser = new Parser();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskManager taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter info = new BufferedWriter(fw);
        for (int i = 0; i < tasks.size(); i++) {
            String lineToAdd = parser.parseTaskToWrite(tasks.get(i));
            info.write(lineToAdd);
            info.newLine();
        }
        info.close();
    }

    public ArrayList<Task> readFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task;
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                task = parser.parseFileContent(sc.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            newFile.createNewFile();
        }
        return tasks;

    }
}