package Duke;

import Duke.commands.Deadline;
import Duke.commands.Event;
import Duke.commands.Task;
import Duke.commands.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class implements methods that help load and save files.
 */
public class Storage {
    /**
     * The method loads tasks stored in the .txt file when the project starts.
     *
     * @param path The path of the .txt file
     * @return the task list stored in the .txt file
     */
    public static ArrayList<Task> loadFile(String path) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String task;

            while (scanner.hasNextLine()) {
                task = scanner.nextLine();
                String[] taskInfo = task.split(" [|] ");
                if (taskInfo[0].equals("T")) {
                    tasks.add(new Todo(taskInfo[2]));
                } else if (taskInfo[0].equals("E")) {
                    tasks.add(new Event(taskInfo[2], taskInfo[3], taskInfo[4]));
                } else {
                    tasks.add(new Deadline(taskInfo[2], taskInfo[3]));
                }

                if (taskInfo[1].equals("1"))
                    tasks.get(tasks.size() - 1).markAsDone();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    private static String fileContent(ArrayList<Task> tasks) {
        String content = "";
        for(Task task : tasks) {
            if (task.getClass().getName().equals("Duke.commands.Todo")) {
                content += "T | " +((task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription());
            } else if (task.getClass().getName().equals("Duke.commands.Event")) {
                content += "E | " +((task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription())+(" | " + task.getStartTime() + " | " + task.getDueTime());
            } else if (task.getClass().getName().equals("Duke.commands.Deadline")) {
                content += "D | " +((task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription())+(" | " + task.getDueTime());
            }
            content += System.lineSeparator();
        }
        return content;
    }

    public static void saveFile(String path, ArrayList<Task> tasks) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not exists, create it ...");
            if (!file.getParentFile().exists()) {
                System.out.println("Directory not exists, create it ...");
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fl = new FileWriter(path);
            fl.write(fileContent(tasks));
            fl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}