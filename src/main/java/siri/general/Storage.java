package siri.general;

import siri.task.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static siri.Duke.indexOfTask;
import static siri.Duke.tasks;

public class Storage {

    protected String filePath;
    protected File file;

    protected ArrayList<Task> oldTasks;
    public Storage (String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
        this.oldTasks = new ArrayList<>();
    }

    public void createFile() throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    public ArrayList<Task> load() throws IOException {
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String[] command = s.nextLine().split(" | ", 2);
            readFile(command[0], command[1]);
            indexOfTask++;
        }
        return oldTasks;
    }

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

    public void appendToFile(String filePath, String taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd + System.lineSeparator());
        fw.close();
    }

    public void overwriteEntireList() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < indexOfTask; ++i){
            fw.write(tasks.getTaskList().get(i).toFileString() + System.lineSeparator());
        }
        fw.close();
    }
}