import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILEPATH = "data/duke.txt";

    /** Writes tasks to file after user exits */
    static void writeToFile(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task task : tasks) {
                String taskType = task.getType();
                String line = "";

                switch (taskType) {
                case "todo":
                    // format: T|description
                    line = line.concat("T|" + task.getStatusIcon() + "|" + task.description + "\n");
                    fw.write(line);
                    break;

                case "deadline":
                    // format: D|description/by:
                    line = line.concat("D|" + task.getStatusIcon() + "|" + task.description);
                    line = line.concat("/by: " + task.getTimings() + "\n");
                    fw.write(line);
                    break;

                case "event":
                    // format: E|description/from: /to:
                    line = line.concat("E|" + task.getStatusIcon() + "|" + task.description);
                    String[] timings = task.getTimings().split("/");
                    line = line.concat("/from: " + timings[0] + "/by: " + timings[1] + "\n");
                    fw.write(line);
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /** Loads tasks from previous save files when starting Duke */
    static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            File saveFile = new File(FILEPATH);
            Scanner saveFileScanner = new Scanner(saveFile);
            String[] currentLine;
            String taskType;
            String taskStatus;
            String taskDescription;
            int taskNo = 0;
            while (saveFileScanner.hasNext()) {
                currentLine = saveFileScanner.nextLine().split("\\|");
                taskType = currentLine[0];
                taskStatus = currentLine[1];
                taskDescription = currentLine[2];

                switch (taskType) {
                case "T":
                    Tasklist.addTodo(tasks, taskDescription);
                    if (taskStatus.equals("X")) {
                        Tasklist.markTask(tasks, taskNo);
                    }
                    break;

                case "D":
                    Tasklist.addDeadline(tasks, taskDescription);
                    if (taskStatus.equals("X")) {
                        Tasklist.markTask(tasks, taskNo);
                    }
                    break;

                case "E":
                    Tasklist.addEvent(tasks, taskDescription);
                    if (taskStatus.equals("X")) {
                        Tasklist.markTask(tasks, taskNo);
                    }
                    break;
                }
            }
            return tasks;

        } catch (FileNotFoundException e) {
            // returns empty ArrayList
            return tasks;
        }
    }

}
