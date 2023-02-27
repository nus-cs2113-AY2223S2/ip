package util;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * The Storage class contains methods for reading and writing of tasks from/to
 * the data file.
 */

public class Storage {
    /**
     * The path of the data file.
     */
    public static final String DATAPATH = "data/duke.txt";
    public static final File DATAFILE = new File(DATAPATH);
    /**
     * The Ui object used for printing messages to the console.
     */
    public Ui ui;

    /**
     * Initializes the data file and directory if they do not already exist.
     */
    public static void initializeFile() {
        File dataDir = new File("data");
        Ui ui = new Ui();
        try {
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            boolean created = DATAFILE.createNewFile();
            if (created) {
                System.out.println("Data file created at " + DATAFILE.getAbsolutePath());
                ui.printDashLine();
            } else {
                System.out.println("Data file already exists at " + DATAFILE.getAbsolutePath());
                ui.printDashLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the data file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the data file into an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects representing the tasks in the data file.
     * @throws FileNotFoundException if the data file is not found.
     */
    public static ArrayList<Task> loadDataFromFile() throws FileNotFoundException {
        ArrayList<Task> commands = TaskList.createList();
        if (DATAFILE.length() > 0) {
            Scanner s = new Scanner(DATAFILE);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = Integer.parseInt(parts[1]) == 1;
                String description = parts[2];
                switch (taskType) {
                    case "T":
                        commands.add(new Todo(description));
                        commands.get(commands.size() - 1).setDone(isDone);
                        break;
                    case "D":
                        String by = parts[3];
                        commands.add(new Deadline(description, by));
                        commands.get(commands.size() - 1).setDone(isDone);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        commands.add(new Event(description, from, to));
                        commands.get(commands.size() - 1).setDone(isDone);
                        break;
                    default:
                        // Ignore invalid tasks
                        break;
                }
            }
        }
        return commands;
    }

    /**
     * Writes a string to the data file.
     *
     * @param textToAdd The string to be written to the data file.
     * @param append    Whether to append to the data file or overwrite it.
     * @throws IOException if an I/O error occurs.
     */
    private static void writeToFile(String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter(DATAPATH, append);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates the data file with the tasks in the given ArrayList.
     *
     * @param commands The ArrayList of Task objects to write to the data file.
     * @throws IOException if an I/O error occurs.
     */
    public static void updateDatafile(ArrayList<Task> commands) throws IOException {
        writeToFile("", false);
        try {
            for (int i = 0; i < commands.size(); i++) {
                Task task = commands.get(i);
                writeToFile(task.toStringForSave() + System.lineSeparator(), true);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
