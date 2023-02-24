package duke.util;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

public class Storage {
    public static void readDukeFile(ArrayList<Task> lists, String filePath, String fileName) throws FileNotFoundException {
        try {
            File dukeText = new File(filePath + "/" + fileName);
            Scanner s = new Scanner(dukeText);
            System.out.println("Duke file has been found. Reading now.");
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] inputLines = line.split("\\|");
                registerDukeFileTasks(lists, inputLines);
            }
        } catch (FileNotFoundException e) {
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdir();
                System.out.println("New directory has been made.");
            }
            System.out.println("File does not yet exist. Please provide some data to be written to the file.");
        }
    }

    public static void registerDukeFileTasks (ArrayList<Task> lists, String[] inputLines) {
        String type = inputLines[0].strip();
        boolean isDone = (inputLines[1].equals("1")) ? true : false;
        if (type.equals("T")) {
            String description = inputLines[2];
            lists.add(new Todo(description));
        } else if (type.equals("D")) {
            String description = inputLines[2];
            String deadline = inputLines[3].strip();
            lists.add(new Deadline(description, deadline));
        } else if (type.equals("E")) {
            String description = inputLines[2];
            String start = inputLines[3];
            String end = inputLines[4];
            lists.add(new Event(description, start, end));
        }

        if (isDone) {
            lists.get(lists.size() - 1).markAsDone();
        }
    }

    public static void saveDataFromInput(String filePath, String fileName, ArrayList<Task> lists) {
        try {
            String formattedDescriptions = convertTaskDescriptionForSaving(lists);
            Storage.writeToDukeFile(filePath, fileName, formattedDescriptions);
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

    private static void writeToDukeFile(String filePath, String fileName, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath + "/" + fileName);
        fw.write(textToAdd);
        fw.close();
    }
}

