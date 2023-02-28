package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.FileNotCreatedException;
import tasklist.TaskList;

/**
 * Provides the methods for managing the savefile. Creates the directory and savefile, and saves
 * the file when needed.
 */

public class SavefileManager {
    
    public static final File SAVE_DIR = new File("data");
    public static final File SAVE_FILE = new File("data/savefile.txt");
    private DukeUi ui;
    private static final boolean WITHOUT_SCANNER = false;

    public SavefileManager() {
        this.ui = new DukeUi(WITHOUT_SCANNER);
    }

    /**
     * Checks if the data directory exists, if no, creates it.
     * 
     * @throws FileNotCreatedException if data directory creation fails
     */

    public void checkSaveDir() throws FileNotCreatedException {
        if (!SAVE_DIR.exists()) {
            ui.printDirNotFound();
            if(SAVE_DIR.mkdir()) {
                ui.printDirCreated();
            } else {
                throw new FileNotCreatedException("data directory");
            }
        }
    }

    /**
     * Checks if the savefile exists, if no, creates it.
     * 
     * @throws IOException if savefile creation unsuccessful due to I/O errors
     * 
     * @throws FileNotCreatedException if savefile creation unsuccessful due to file already existing for some reason
     */

    public void checkSavefile() throws IOException, FileNotCreatedException {
        if (!SAVE_FILE.exists()) {
            ui.printSavefileNotFound();
            try {
                if (SAVE_FILE.createNewFile()) {
                    ui.printSavefileCreated();
                } else {
                    throw new FileNotCreatedException("savefile");
                }
            } catch (Exception e) {
                System.out.println("savefile creation unsuccessful, exiting program.");
                throw e;
            }
        } else {
            ui.printSavefileFound();
        }
    }

    /**
     * Parses the savefile into the tasklist.
     * 
     * @return Tasklist with the save data
     * 
     * @throws IOException if I/O errors occur while reading the file
     */

    public TaskList parseSavefile() throws IOException {
        Scanner s = new Scanner(SAVE_FILE);
        TaskList taskList = new TaskList(s);
        s.close();
        return taskList;
    }

    /**
     * Saves the data in the tasklist into the savefile.
     * 
     * @param taskList the tasklist in question
     */

    public void save(TaskList taskList) {
        ui.printSavingToFile();
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE, false);
            fileWriter.write("");
            taskList.saveToFile(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Saving failed for some reason.");
            e.printStackTrace();
        }
    }
}
