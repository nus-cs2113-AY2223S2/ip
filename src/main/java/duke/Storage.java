package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String filepath = "./data/duke.txt";

    public static Task addTask(String description) {
        String[] taskDescription;
        taskDescription = description.split("|", 3);
        Task taskToAdd;
        switch (taskDescription[0]) {
        case "T":
            taskToAdd = new Todo(taskDescription[2]);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        case "D":
            String[] deadlineDescription = taskDescription[2].split("|", 2);
            taskToAdd = new Deadline(deadlineDescription[0], deadlineDescription[1]);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        case "E":
            String[] eventDescription = taskDescription[2].split("|", 2);
            String dates[] = eventDescription[1].split("-");
            String start = dates[0];
            String end = dates[1];
            taskToAdd = new Event(eventDescription[0], start, end);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        default:
            taskToAdd = null;
            break;
        }
        return taskToAdd;
    }

    public static ArrayList<Task> getData() throws IOException {
        ArrayList<Task> currentList = new ArrayList<>();
        File f = new File(filepath);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String description = s.nextLine();
                currentList.add(addTask(description));
            }
        }
        return currentList;
    }

    public static String getNumberIcon(Task task) {
        if (task.getStatusIcon().equals("X")) {
            return "1";
        }
        return "0";
    }

    public static String formatTask(ArrayList<Task> tasks) throws IOException {
        String text = null;
        for (Task i : tasks) {
            if (i.getType().equals("todo")) {
                text = "T" + " | " + getNumberIcon(i) + " | " + i.getDescription();
            }
            if (i.getType().equals("deadline")) {
                text = "D" + " | " + getNumberIcon(i) + " | " + i.getDescription() + " | " + i.getEnd();
            }
            if (i.getType().equals("event")) {
                text = "E" + " | " + getNumberIcon(i) + " | " + i.getDescription() + " | " + i.getStart() + "-" + i.getEnd();
            }
        }
        return text;
    }

    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        String textToAdd = formatTask(tasks);
        fw.write(textToAdd);
        fw.close();
    }
}
