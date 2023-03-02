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

public class WriteFile {
    private static String[] loadInfo(String line) {
        String[] taskInfo = line.split(" | ");
        return taskInfo;
    }

    public static ArrayList<Task> loadFile(String path) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String task;

            while (scanner.hasNextLine()) {
                task = scanner.nextLine();
                String[] taskInfo = loadInfo(task);
                if (taskInfo[0].equals("T")) {
                    tasks.add(new Todo(taskInfo[2]));
                } else if (taskInfo[0].equals("E"))
                    tasks.add(new Event(taskInfo[2], taskInfo[3], taskInfo[4]));
                else
                    tasks.add(new Deadline(taskInfo[2], taskInfo[3]));
                if (taskInfo[1].equals("1"))
                    tasks.get(tasks.size() - 1).markAsDone();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    private static String fileContent(Task task) {
        String content = "";
        if (task.getClass().getName().equals("Duke.commands.Todo")) {
            content += "T | ";
        }
        if (task.getClass().getName().equals("duke.commands.Event")) {
            content += "E | ";
        }
        if (task.getClass().getName().equals("duke.commands.Deadline")){
            content += "D | ";
        }
        content += ((task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription());
        if (task.getClass().getName().equals("duke.commands.Deadline")){
            content += (" | " + task.getDueTime());
        }
        if(task.getClass().getName().equals("duke.commands.Event")){
            content +=(" | " + task.getStartTime() + " | " + task.getDueTime());
        }
        return content;
    }
    private static void saveFile(String path, ArrayList<Task> tasks) {
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
            for (Task task : tasks) {
                fl.write(fileContent(task));
                fl.write(System.lineSeparator());
            }
            fl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}