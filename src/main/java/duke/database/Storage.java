package duke.database;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public ArrayList<Task> taskList;
    public static final String FILE_PATH = "data/duke.txt";

    public Storage() throws IOException {
        File f = new File(FILE_PATH);
        this.taskList = new ArrayList<>();

        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                Task currTask = parseToTask(taskString);
                this.taskList.add(currTask);
            }
        } else {
            File dir = new File("data");
            dir.mkdirs();
            f.createNewFile();
        }
    }

    /** Overwriting the txt file */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /** Converts string to respective task type */
    public Task parseToTask(String str) {
        Task currTask;
        String[] stringArr = str.split("//");
        String typeOfTask = stringArr[2];

        if (typeOfTask.equals("T")) {
            currTask = new ToDo((stringArr[1]));
        } else if (typeOfTask.equals("D")) {
            currTask = new Deadline(stringArr[1], stringArr[3]);
        } else {
            currTask = new Event(stringArr[1], stringArr[3], stringArr[4]);
        }

        if (stringArr[0].equals("X")) {
            currTask.setDone();
        }
        return currTask;
    }

}
