package duke.util;

import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class FileManager {
    static private final ArrayList<String> storedData = new ArrayList<>();
    public static void readFile(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            storedData.add(s.nextLine());
        }
    }

    public static void writeFile(String path, ArrayList<Task>tasks) throws IOException {
        //Overwrites previous text in file
        FileWriter fw = new FileWriter(path, false);
        for (Task i: tasks) {
            String t = i.checkType();
            char done = i.checkDone().charAt(1);
            String w = t.charAt(1) + " % " + done + " % " + i.getTask() + "\n";
            fw.write(w);
        }
        fw.close();
    }

    public static void writeTask(String path, Task task) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        String t = task.checkType();
        char done = task.checkDone().charAt(1);
        String w = t.charAt(1) + " % " + done + " % " + task.getTask() + "\n";
        fw.write(w);
        fw.close();
    }

    public static void createFile(String path) {
        try {
            Path dir = Paths.get("data");
            Files.createDirectories(dir);
            File newFile = new File(path);
            if (newFile.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static void handleFile(DataManager dm) throws DukeException {
        for (String str: storedData) {
            String[] temp = str.split("%",5);
            switch (temp[0].trim()) {
            case ("T"):
                dm.handleTodo(temp[2].trim(), false);
                if (temp[1].trim().equals("X")) {
                    dm.handleMark("task", temp[2].trim(), false);
                }
                break;
            case ("D"):
                try {
                    dm.handleDeadline(temp[2].trim() + "/by" + temp[3], false);
                    if (temp[1].trim().equals("X")) {
                        dm.handleMark("task", temp[2].trim(), false);
                    }
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            case ("E"):
                try {
                    dm.handleEvent(temp[2].trim() + "/from" + temp[3] + "/to" + temp[4], false);
                    if (temp[1].trim().equals("X")) {
                        dm.handleMark("task", temp[2].trim(), false);
                    }
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            }
        }
    }
}
