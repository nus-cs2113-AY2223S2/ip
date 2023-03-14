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
    /**
     * Class constructor.
     */
    public Storage() {
    }

    /**
     * Returns an <code>ArrayList</code> containing <code>Task</code> objects from a .txt save file
     * at a specified path on the hard disk.
     *
     * @param savePath relative path to the save file.
     * @return the <code>ArrayList</code> of saved <code>Task</code> elements.
     * @throws FileNotFoundException If the file could not be found at the specified path.
     */
    public static ArrayList<Task> readSaveFile(String savePath) throws FileNotFoundException {
        ArrayList<Task> savedList = new ArrayList<>();
        File saveFile = new File(savePath);
        Scanner save = new Scanner(saveFile);
        Ui.saveFound();
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
                    Deadline savedDeadline = new Deadline(taskDetails[2],
                            LocalDateTime.parse(taskDetails[3], TaskList.formatter));
                    savedDeadline.setDone(!taskDetails[1].equals("0"));
                    savedList.add(savedDeadline);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                Event savedEvent = new Event(taskDetails[2], LocalDateTime.parse(taskDetails[3], TaskList.formatter),
                        LocalDateTime.parse(taskDetails[4], TaskList.formatter));
                savedEvent.setDone(!taskDetails[1].equals("0"));
                savedList.add(savedEvent);
                break;
            }
        }
        return savedList;
    }

    /**
     * Creates a new .txt file at the specified path. If the directory does not exist,
     * creates a new one.
     *
     * @param savePath relative path to the new file.
     * @param dataDir relative path to the new file's directory.
     */
    public static void createSaveFile(String savePath, String dataDir) {
        Ui.saveNotFound();
        File newFile = new File(savePath);
        try {
            Path dataPath = Paths.get(dataDir);
            Files.createDirectory(dataPath);
            newFile.createNewFile();
            Ui.saveCreated();
        } catch (IOException ioException) {
            Ui.fileCreateError();
        }
    }

    /**
     * Prints all the formatted <code>Task</code> objects in the <code>TaskList</code> on separate lines
     * into a .txt file on the hard disk.
     *
     * @throws IOException If the file could not be written to.
     */
    public static void writeToFile() throws IOException {
        FileWriter saveFile = new FileWriter("data/savedlist.txt");
        for (Task taskToSave : TaskList.getTaskList()) {
            saveFile.write(taskToSave.toSave());
            saveFile.write("\n");
        }
        saveFile.close();
    }
}
