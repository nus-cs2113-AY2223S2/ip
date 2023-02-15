package util;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String DATAPATH = "data/duke.txt";
    public static final File DATAFILE = new File(DATAPATH);
    public Ui ui;
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

    private static void writeToFile(String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter(DATAPATH, append);
        fw.write(textToAdd);
        fw.close();
    }

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
