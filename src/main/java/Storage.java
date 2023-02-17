import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static duke.FileOperations.*;

public class Storage {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    //load the data from the hard disk when Duke starts up
    //write the existing data into the current list of tasks
    public static void loadData() throws FileNotFoundException {
        String filePath = "data/duke.txt";
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            convertFromData(str);
        }
        System.out.println("Saved data has been loaded!");
        printLine();
    }

    public static void load() {
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! The file does not exist. Creating a new file.");
            String pathName = "data";
            String filePath = "data/duke.txt";
            File directory = new File(pathName);
            File file = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                file.createNewFile();
                System.out.println("Now your data will be saved in the file. Please proceed!");
                printLine();
            } catch (IOException exception) {
                System.out.println("Error while creating the file.");
                printLine();
            }
        }
    }

    //save the tasks in the hard disk automatically whenever the task list changes
    public static void saveData() {
        String filePath = "data/duke.txt";
        try {
            writeToFile(filePath, convertToData(Task.tasks.get(0)) + System.lineSeparator());
            for (int i = 1; i < Task.tasks.size(); i++) {
                appendToFile(filePath, convertToData(Task.tasks.get(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    //convert data from text file into Task list format to Load Data
    public static void convertFromData(String str) {
        String[] task = str.split("\\|");
        Character c = task[0].charAt(0); //check if task is a To-do, Deadline or Event
        Character marked = task[1].charAt(1); //check if task is marked done or not
        if (c.equals('T')) {
            String description = task[2].substring(1);
            Task t = new Todo(description);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        } else if (c.equals('D')) {
            String description = task[2].substring(1,(task[2].length()-1));
            String deadline = task[3].substring(1);
            Task t = new Deadline(description, deadline);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        } else {
            String description = task[2].substring(1,(task[2].length()-1));
            String event = task[3].substring(1);
            String[] duration = event.split("-");
            Task t = new Event(description, duration[0], duration[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            if (marked.equals('1')) {
                t.markAsDone();
            }
        }
    }

    //convert tasks in arraylist to save in text file for Save Data
    public static String convertToData(Task t) {
        String str = ""+t;
        String data;
        Character c = str.charAt(1); //check if task is a To-do, Deadline or Event
        Character marked = str.charAt(4); //check if task is marked done or not
        String task =  str.substring(6);
        if (c.equals('T')) {
            if (marked.equals('X')) {
                data = ("T | 1 |" + task);
            } else {
                data = ("T | 0 |" + task);
            }
        } else if (c.equals('D')) {
            int openBracket = task.indexOf("(");
            int closeBracket = task.indexOf(")");
            String description = task.substring(0,openBracket);
            String by = task.substring(openBracket+1, closeBracket);
            String[] deadline = by.split("by: ");
            if (marked.equals('X')) {
                data = ("D | 1 |" + description + " | " + deadline[1]);
            } else {
                data = ("D | 0 |" + description + " | " + deadline[1]);
            }
        } else {
            int openBracket = task.indexOf("(");
            int closeBracket = task.indexOf(")");
            String description = task.substring(0,openBracket);
            String duration = task.substring(openBracket+6, closeBracket);
            String[] eventTime = duration.split("to: ");
            if (marked.equals('X')) {
                data = ("E | 1 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            } else {
                data = ("E | 0 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            }
        }
        return data;
    }
}
