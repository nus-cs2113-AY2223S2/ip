package hina.helper;

import hina.task.Deadline;
import hina.task.Event;
import hina.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public Storage() {
    }

    public static ArrayList<Task> readSaveFile(String savePath) throws FileNotFoundException {
        ArrayList<Task> savedList = new ArrayList<>();
        File saveFile = new File(savePath);
        Scanner save = new Scanner(saveFile);
        System.out.println("Saved list found, loading saved list...");
        while (save.hasNext()) {
            String line = save.nextLine();
            String[] taskDetails = line.split(" / ");
            switch (taskDetails[0]) {
            case "T":
                Task savedTask = new Task(taskDetails[2]);
                savedTask.setDone(!taskDetails[1].equals("0"));
                savedList.add(savedTask);
                break;
            case "D":
                try {
                    Deadline savedDeadline = new Deadline(taskDetails[2], LocalDateTime.parse(taskDetails[3], TaskList.formatter));
                    savedDeadline.setDone(!taskDetails[1].equals("0"));
                    savedList.add(savedDeadline);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                Event savedEvent = new Event(taskDetails[2], LocalDateTime.parse(taskDetails[3], TaskList.formatter), LocalDateTime.parse(taskDetails[4], TaskList.formatter));
                savedEvent.setDone(!taskDetails[1].equals("0"));
                savedList.add(savedEvent);
                break;
            }
        }
        return savedList;
    }

    public static void createSaveFile(String savePath, String dataDir) {
        System.out.println("Save file not found! Creating new file...");
        File newFile = new File(savePath);
        try {
            Path dataPath = Paths.get(dataDir);
            Files.createDirectory(dataPath);
            newFile.createNewFile();
            System.out.println("Save file created!");
        } catch (IOException ioException) {
            System.out.println("T.T Ahh! Something went wrong, could not create file!");
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter saveFile = new FileWriter("data/savedlist.txt");
        for (Task taskToSave : TaskList.getTaskList()) {
            saveFile.write(taskToSave.toSave());
            saveFile.write("\n");
        }
        saveFile.close();
    }
}
