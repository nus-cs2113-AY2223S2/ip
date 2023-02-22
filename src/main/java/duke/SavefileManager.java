package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.FileNotCreatedException;
import tasklist.TaskList;

public class SavefileManager {
    
    public static final File SAVE_DIR = new File("data");
    public static final File SAVE_FILE = new File("data/savefile.txt");
    private DukeUi ui;
    private static final boolean WITHOUT_SCANNER = false;

    public SavefileManager() {
        this.ui = new DukeUi(WITHOUT_SCANNER);
    }

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

    public TaskList parseSavefile() throws IOException {
        Scanner s = new Scanner(SAVE_FILE);
        TaskList taskList = new TaskList(s);
        s.close();
        return taskList;
    }

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
