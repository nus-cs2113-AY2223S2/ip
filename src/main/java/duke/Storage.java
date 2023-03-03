package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.commands.Task;
import duke.commands.Todo;
import duke.commands.Event;
import duke.commands.Deadline;
import duke.commands.Datetime;

public class Storage {
    public Storage() {}

    private static String[] changeLoadedDescription(String dscrption) {
        String[] taskInfo = dscrption.split(" \\| ");
        return taskInfo;
    }

    /**
     * converts the datetime information in String to DateTime type
     * 
     * @param datetime the time information read in String type
     * @param formatter the DateTime Formatter
     * @return DateTime information in DateTime type
     */
    private static Datetime convertToDT(String datetime, DateTimeFormatter formatter) {
        String[] splittedDatetime = datetime.split(" ");
        if (splittedDatetime.length == 1) {
            return new Datetime(LocalDate.parse(splittedDatetime[0], formatter));
        } else {
            return new Datetime(LocalDate.parse(splittedDatetime[0], formatter),
                    LocalTime.parse(splittedDatetime[1]));
        }
    }

    /**
     * import tasks from the local file
     * 
     * @param path the path of th elocal file
     * @return A ArrayList which store tasks from local file
     */
    public static ArrayList<Task> loadFile(String path) {
        ArrayList<Task> task = new ArrayList<>();
        DateTimeFormatter loadingFormatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");

        try {
            Scanner scanner = new Scanner(new File(path));
            String currentTask = "";

            while (scanner.hasNextLine()) {
                currentTask = scanner.nextLine();
                String[] taskInfo = changeLoadedDescription(currentTask);
                if (taskInfo[0].equals("T")) {
                    task.add(new Todo(taskInfo[2]));
                } else if (taskInfo[0].equals("E"))
                    task.add(new Event(taskInfo[2], convertToDT(taskInfo[3], loadingFormatter)));
                else
                    task.add(new Deadline(taskInfo[2], convertToDT(taskInfo[3], loadingFormatter)));

                if (taskInfo[1].equals("1"))
                    task.get(task.size() - 1).markAsDone();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    /**
     * convert the list of task into a String that can be written into the locally stored file
     * 
     * @param tasks the current lis of task
     * @return A String which contains all the information of current tasklist
     */
    private static String changeDescriptionForSaving(ArrayList<Task> tasks) {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (!currentTask.getClass().getName().equals("duke.commands.Todo")) {
                if (currentTask.getClass().getName().equals("duke.commands.Event"))
                    content += "E | ";
                else
                    content += "D | ";
            } else
                content += "T | ";

            content += ((currentTask.getTaskStatus().equals("X") ? "1" : "0") + " | "
                    + currentTask.getTaskDiscription());
            if (!currentTask.getClass().getName().equals("duke.commands.Todo"))
                content += (" | " + tasks.get(i).getDue());
            content += System.lineSeparator();
        }
        return content;
    }

    /**
     * save the current list of tasks.
     * 
     * @param tasks the tasks
     * @param path the path of the loal file
     * @throws IOException
     */
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
