package duke.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//import static duke.classes.Duke.addTask;
//import static duke.classes.Duke.markTask;
//import static duke.classes.Duke.unmarkTask;
import static duke.classes.Tasklist.addTask;
import static duke.classes.Tasklist.markTask;
import static duke.classes.Tasklist.unmarkTask;

public class Storage {
    protected static String filepath;
    protected ArrayList<Task> listOfTask;

    public Storage(String filepath, ArrayList<Task> listOfTask) {
        this.filepath = filepath;
        this.listOfTask = listOfTask;
    }

    public static void getStorage() throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String temp = scan.nextLine();
            String type = temp.substring(1,2);
            String status = temp.substring(6,7);
            if (type.equals("T")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                addTask(task);
            } else if (type.equals("D")) {
                String info = temp.substring(7, temp.indexOf("("));
                String timeBy = temp.substring(temp.indexOf("(")+1, temp.length() - 1);
                Deadline task = new Deadline(info, timeBy);
                if (status.equals("X")) {
                    markTask(task);
                } else {
                    unmarkTask(task);
                }
                addTask(task);
            } else if (type.equals("E")) {
                String info = temp.substring(7,temp.indexOf("("));
                String timeFrom = temp.substring(temp.indexOf("(")+1, temp.lastIndexOf(","));
                String timeBy = temp.substring(temp.lastIndexOf(",")+1, temp.length() - 1);
                Event task = new Event(info, timeFrom, timeBy);
                if (status.equals("X")) {
                    markTask(task);
                } else {
                    unmarkTask(task);
                }
                addTask(task);
            }
        }
    }
}
