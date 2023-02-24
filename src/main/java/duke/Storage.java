package duke;

import duke.exception.IllegalCommandException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage (String filePath) {
        this.filePath = filePath;
    }
    public void load() throws IOException {
        File folder = new File("data");
        if (!(folder.exists() && folder.isDirectory())) {
            // create new folder under root directory
            new File("data").mkdirs();
        }
        File txtFile = new File("data/duketasks.txt");
        if (!txtFile.exists()) {
            txtFile.createNewFile();
        }
        Scanner f = new Scanner(txtFile);
        while (f.hasNext()) {
            String nextLine = f.nextLine();
            String[] saveLine = nextLine.split(" ", 3);
            String fileLine = saveLine[2];
            String command = saveLine[1].trim();
            // last string is "1" for done, "0" for not done
            String checkDone = saveLine[0].trim();
            try {
                // add task into duke
                TaskList.addTaskFromFile(fileLine, command);
            } catch (IllegalCommandException e) {
                Ui.printException("Parsing error!");
            }
            if (checkDone.equals("1")) {
                TaskList.lastTaskSetDone(true);
            }
        }
    }

    public void save() throws IOException {
        File f = new File("data/duketasks.txt");
        if (f.exists()) {
            f.delete();
        }
        f.createNewFile();
        FileWriter writeFile = new FileWriter(f);
        ArrayList<String> saveStrings = TaskList.getSaveString();
        for (String s : saveStrings) {
            writeFile.write(s);
        }
        writeFile.close();
    }

    public void initialise() {
        Ui.greet();
        try {
            load();
        } catch (IOException e) {
            Ui.printException("Something went wrong while loading file!");
        }
    }
}
