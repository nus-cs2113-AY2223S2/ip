import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    static String filePath;
    public Storage(String filePath){
        Storage.filePath = filePath;
    }

    public static void readFile(String filePath, ArrayList<Task> list) {
        try {
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            for (Task task : list) {

                if (task.taskType == "T") {
                    writeFile.write(task.taskType + "|" + task.isCompleted + "|" + task.taskName + System.lineSeparator());
                }
                else if (task.taskType == "D") {
                    writeFile.write(task.taskType + "|" + task.isCompleted + "|" + task.taskName +  "|" + task.endTime + System.lineSeparator());
                }
                else{
                    writeFile.write(task.taskType + "|" + task.isCompleted + "|" + task.taskName +  "|" + task.startTime +  "|" + task.endTime + System.lineSeparator());
                }

            }
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }


    public static void readContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File savedFile = new File(filePath);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }
        try {
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating new file!");
        }
        Scanner s = new Scanner(new FileInputStream((filePath)));
        while (s.hasNext()) {
            String command = s.nextLine();
            String[] inputCommands = command.split("\\|", 4);
            String fileType = inputCommands[0];
            boolean isCompleted = true;
            if (Objects.equals(inputCommands[1], "0")) {
                isCompleted = false;
            }

            if (Objects.equals(fileType, "T")) {
                Task newTask = new Task(inputCommands[2]);
                newTask.setTaskProgress(isCompleted);
                list.add(newTask);
            }
            else if (Objects.equals(fileType, "D")) {
                Deadline newDeadline = new Deadline(inputCommands[2]);
                newDeadline.setTaskProgress(isCompleted);
                newDeadline.setEndTime(inputCommands[3]);
                list.add(newDeadline);
            }
            else if (Objects.equals(fileType, "E")){
                Event newEvent = new Event(inputCommands[2]);
                newEvent.setTaskProgress(isCompleted);
                String [] contents = inputCommands[3].split(" ");
                String startTime = contents[0];
                String endTime = contents[1];
                newEvent.setStartTime(startTime);
                newEvent.setEndTime(endTime);
                list.add(newEvent);
            }
            else
                throw new FileNotFoundException();
        }
    }
}

