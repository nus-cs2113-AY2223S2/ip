package file.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;

public class Storage {
    final static String DEFAULT_SAVE_PATH = "data/savedList.txt";
    final static String DEFAULT_DIRECTORY_NAME = "data";

    private static File directoryAndFileChecker() throws IOException {
        File f = new File(DEFAULT_SAVE_PATH);
        File directory = new File(DEFAULT_DIRECTORY_NAME);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return f;
    }
    public static void setUpFile(ArrayList<Task> actions) {
        try {
            File f = directoryAndFileChecker();
            String line;
            String[] decisions;
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                line = in.nextLine();
                decisions = line.split("/");
                switch (decisions[0]) {
                    case "t":
                        setUpToDo(decisions, actions);
                        break;
                    case "d":
                        setUpDeadline(decisions, actions);
                        break;
                    case "e":
                        setUpEvent(decisions, actions);
                        break;
                    default:
                        System.out.println("File has been corrupted");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void saveFile(ArrayList<Task> actions) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_SAVE_PATH);
            for (Task i : actions) {
                fw.write(i.getCommand() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void setUpToDo(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Todo(decisions[2]);
        if (decisions[1].equals("X")) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }

    private static void setUpDeadline(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Deadline(decisions[2], decisions[3]);
        if (decisions[1].equals("X")) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }

    private static void setUpEvent(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Event(decisions[2], decisions[3], decisions[4]);
        if (decisions[1].equals("X")) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }
}
