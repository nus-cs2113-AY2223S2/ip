package dude.commands;

import dude.task.ListManager;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public abstract class SaveData {

    private static final String DIRECTORY = "storage";
    private static final String FILEPATH = DIRECTORY + "/data.txt";

    public static String getFilepath() {
        return FILEPATH;
    }

    public static String getDirectory() {
        return DIRECTORY;
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (int i = 0; i < ListManager.getList().size(); i++) {
                fw.write(ListManager.getList().get(i).toSave() + System.lineSeparator());
            }
            fw.close();
            System.out.println("Saving current list");
        } catch (Exception e) {
            System.out.println("Error saving");
        }
    }

    public static void loadSaved() {
        try {
            File fileDirectory = new File(getDirectory());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }
            File txtFile = new File(getFilepath());
            if (!txtFile.exists()) {
                txtFile.createNewFile();
            }
            File f = new File(FILEPATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            int index = 0;
            while (s.hasNext()) {
                String[] tmp = s.nextLine().split("=", 2);
                if (tmp[0].equals("1")) {
                    Parser.parseInput(tmp[1], true);
                    ListManager.getList().get(index).setDone();
                    index++;
                } else {
                    Parser.parseInput(tmp[1], true);
                    index++;
                }
            }
            s.close();
            System.out.println("Here is your saved list");
            ListManager.printList();
        } catch (Exception e) {
            System.out.println("Error loading data");
        }
    }


}
