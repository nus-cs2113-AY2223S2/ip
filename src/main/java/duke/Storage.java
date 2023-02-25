package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import duke.commands.Task;
import duke.commands.Todo;
import duke.commands.Event;
import duke.commands.Deadline;

public class Storage {
    public Storage() {}

    private static String[] changeLoadedDescription(String dscrption) {
        String[] taskInfo = dscrption.split(" \\| ");
        return taskInfo;
    }

    public static ArrayList<Task> loadFile(String path) {
        ArrayList<Task> task = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String curTask = "";

            while (scanner.hasNextLine()) {
                curTask = scanner.nextLine();
                String[] taskInfo = changeLoadedDescription(curTask);
                if (taskInfo[0].equals("T")) {
                    task.add(new Todo(taskInfo[2]));
                } else if (taskInfo[0].equals("E"))
                    task.add(new Event(taskInfo[2], taskInfo[3]));
                else
                    task.add(new Deadline(taskInfo[2], taskInfo[3]));

                if (taskInfo[1].equals("1"))
                    task.get(task.size() - 1).markAsDone();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    private static String changeDescriptionForSaving(ArrayList<Task> tasks) {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task curTask = tasks.get(i);
            if (!curTask.getClass().getName().equals("duke.commands.Todo")) {
                if (curTask.getClass().getName().equals("duke.commands.Event")) {
                    content += "E | ";
                } else {
                    content += "D | ";
                }
            } else {
                content += "T | ";
            }

            content += ((curTask.getTaskStatus().equals("X") ? "1" : "0") + " | "
                    + curTask.getTaskDiscription());

            if (!curTask.getClass().getName().equals("duke.commands.Todo")) {
                content += (" | " + tasks.get(i).getDue());
            }

            content += System.lineSeparator();
        }
        return content;
    }

    public static void autoSave(ArrayList<Task> tasks, String path) throws IOException {
        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter(path);
        fw.write(changeDescriptionForSaving(tasks));
        fw.close();
    }

}
