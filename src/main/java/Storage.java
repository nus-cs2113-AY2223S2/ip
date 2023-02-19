import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static duke.FileOperations.*;

/**
 * Represents a Storage object that handles the loading of data from text file and
 * saving of data into text file.
 */
public class Storage {
    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Loads the data from the text file when Duke starts up and writes the
     * existing data into the list of tasks.
     * @throws FileNotFoundException when the text file "data/duke.txt" is
     * not found.
     */
    public void loadData() throws FileNotFoundException {
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

    /**
     * Attempts to load the data from the text file into the list of tasks.
     * Attempts to create the text file for the user if it is not found.
     */
    public void load() {
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! The file does not exist. Creating a new file.");
            String pathName = "data";
            String filePath = "data/duke.txt";
            File directory = new File(pathName);
            File file = new File(filePath);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created.");
                } else {
                    System.out.println("Directory already exists.");
                }
            }
            try {
                if (file.createNewFile()) {
                    System.out.println("Now your data will be saved in the file. Please proceed!");
                } else {
                    System.out.println("File already exists.");
                }
                printLine();
            } catch (IOException exception) {
                System.out.println("Error while creating the file. Your data won't be saved :(");
                printLine();
            }
        }
    }

    /**
     * Saves the tasks in the current list of tasks automatically whenever
     * a new change is made to the task list. Ensures that user's data is
     * saved even if the program crashes.
     */
    public void saveData() {
        String filePath = "data/duke.txt";
        try {
            writeToFile(filePath, convertToData(Task.tasks.get(0)) + System.lineSeparator());
            for (int i = 1; i < Task.tasks.size(); i++) {
                appendToFile(filePath, convertToData(Task.tasks.get(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }

    /**
     * Converts data from inside the text file into a Task object before
     * loading the data into the task list.
     * @param str Task to be loaded from text file into task list.
     */
    public void convertFromData(String str) {
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

    /**
     * Converts a task from inside the task list into the correct format for
     * saving the task in the text file.
     * @param t Task to be saved from task list into text file.
     */
    public String convertToData(Task t) {
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
            String[] eventTime = duration.split(" to: ");
            if (marked.equals('X')) {
                data = ("E | 1 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            } else {
                data = ("E | 0 |" + description + " | " + eventTime[0].substring(1) + "-" + eventTime[1]);
            }
        }
        return data;
    }
}
