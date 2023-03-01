import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles saved storage.
 * Use this to save, write, and parse files.
 */
public final class Storage {
    private static final String DEFAULT_FILE_PATH = "data/papatask.txt";
    public static final String FILE_DELIMITER = "|";

    /**
     * Constructor with custom FilePath defined
     * @param filePath Where the saved files are stored.
     */
    public Storage(String filePath) {
        openFile(filePath);

    }

    /**
     * Constructor without filepath defined.
     * Uses default file path ({@value DEFAULT_FILE_PATH}) and runs constructor as usual.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Open the saved tasks file upon startup of PAPA.<br>
     * Checks if the directory and text file exist, and writes to the file.
     * @param filePath the path where data is stored.
     */
    public static void openFile(String filePath) {
        File file = new File(filePath);

        // Create the directories required (if not exist)
        if (!file.getParentFile().exists()) {
            // Make directories, return true if successful.
            if (file.getParentFile().mkdirs()) {
                System.out.println("Successfully created directory in " + file.getAbsolutePath());
            } else {
                System.out.println("Directory exists or unable to create.");
            }
        }

        // Check if file exists.
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred in creating a new save file.");
            }
            System.out.println("New save file created in " + file.getAbsolutePath());
        } else {
            System.out.println("Loaded your saved tasks.");
        }

        readFile(DEFAULT_FILE_PATH);
    }

    /**
     * Appends input text to the save file.
     * @param textToAdd The String to append to the file.
     * @throws IOException Unable to write successfully.
     */
    public static void writeToFile(String textToAdd) {
        try {
            // 2nd argument true: indicates to append instead of overwrite.
            // I want to overwrite.
            FileWriter writer = new FileWriter(DEFAULT_FILE_PATH);
            writer.write(textToAdd);
            // Add newline
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong: " + e.getMessage());
        }
    }

    /**
     * Read the file and return.
     * @param filePath Path where the save data is stored.
     */
    public static void readFile(String filePath) {
        File f = new File(path);
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                readLineAsTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found >_<: " + e.getMessage());
        }
    }

    /**
     * Reads the line in the save file and invokes TaskList to add task, if any.
     * @param line String line in the text file.
     */
    private static void readLineAsTask(String line) {
        if (line.isBlank()) {
            return;
        }
        // Delimiter and any amount of whitespace on left/right. Note, need to escape regex \\.
        String[] linesSplit = line.split("\\s+" + "\\" + FILE_DELIMITER + "\\s+");


        switch (linesSplit[0]) {
        case "T":
            // Has to contain T, isdone, and Description
            if (linesSplit.length == 3) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        case "D":
            // Has to contain D, isdone, description, by
            if (linesSplit.length == 4) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        case "E":
            // Has to contain E, isdone, description, from, to
            if (linesSplit.length == 5) {
                TaskList.addTaskFromFile(linesSplit);
                break;
            }
            // FALLTHROUGH
        default:
            System.out.println("I think there's an error with the file.");
        }
    }
}
