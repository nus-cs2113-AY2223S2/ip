package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private static String fileFolder;
    public Storage(String filePath) {
        this.filePath = filePath;
        String[] splitPath = filePath.split("/", 2);
        fileFolder = splitPath[0];
    }

    public static ArrayList<Task> readDukeFile() throws FileNotFoundException {
        File dukeText = new File(filePath);
        Scanner s = new Scanner(dukeText);
        ArrayList<Task> lists = new ArrayList<Task>();
        System.out.println("Duke file has been found. Reading now.");
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] inputLines = line.split("\\|");
            Parser.registerDukeFileTasks(lists, inputLines);
        }
        return lists;
    }

    public static void makeDukeDataDirectory() {
        File directory = new File(fileFolder);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    public static void saveDataFromInput(ArrayList<Task> lists) {
        try {
            String formattedDescriptions = convertTaskDescriptionForSaving(lists);
            Storage.writeToDukeFile(formattedDescriptions);
        } catch (IOException e) {
            System.out.println("Something went wrong with saving your data.");
        }
    }

    public static String convertTaskDescriptionForSaving (ArrayList<Task> lists) {
        StringJoiner joinedTasksString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < lists.size(); i++) {
            joinedTasksString.add(lists.get(i).toSaveString());
        }
        return joinedTasksString.toString();
    }

    private static void writeToDukeFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}

