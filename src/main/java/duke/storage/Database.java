package duke.storage;

import duke.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    public ArrayList<Task> taskList;
    private String databaseDirectory;
    private String filePath;

    private final static String DUKE_TXT_FILE_NAME = "duke.txt";

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

    public void appendSaveTasks(String userInput) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(userInput + System.lineSeparator());
        fw.close();
    }

    public void updateDatabase() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); ++i){
            fw.write(taskList.get(i).taskInformation() + System.lineSeparator());
        }
        fw.close();
    }

    private void initialisation(File savedFile) throws IOException{
        Scanner scan = new Scanner(savedFile);
        while (scan.hasNext()) {
            String taskInput = scan.nextLine();
            taskList.add(handleTask(taskInput));
        }
    }

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
        updateMark(components[1], task);
        return task;
    }

    private void updateMark(String mark, Task task) {
        if (mark.equals("true")) {
            task.setDone(true);
        } else {
            task.setDone(false);
        }
    }
}
