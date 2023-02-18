package duke;
import duke.addable.*;
import duke.exception.ArgumentBlankException;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
public class FileManager {
    private static final String FILE_PATH = "data/tasklist.txt";
    private static final String[] FILE_ERROR_MESSAGE = {
            "Something went wrong when opening the saved tasks file"
    };
    private FileWriter fw;
    private File f;
    private ArrayList<Task> tasks = new ArrayList<>();
    public FileManager() {
        try {
            f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            readCurrentTaskList();
            saveCurrentTaskList();
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Couldn't find required file \'data/tasklist.txt\'");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTaskFromLine(String line) {
        String[] fields = line.split("\\|");
        try {
            switch (fields[0]) {
            case "T":
                tasks.add(new ToDo(
                        fields[2],
                        fields[1].equals("0") ? false : true
                ));
                break;
            case "D":
                tasks.add(new Deadline(
                        fields[2],
                        fields[3],
                        fields[1].equals("0") ? false : true
                ));
                break;
            case "E":
                tasks.add(new Event(
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[1].equals("0") ? false : true
                ));
                break;
            }
        } catch (ArgumentBlankException e) {
            Duke.printMessage(FILE_ERROR_MESSAGE);
        }


    }
    public void readCurrentTaskList() {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addTaskFromLine(s.nextLine());
            }
        } catch (IOException e) {
            System.out.println(FILE_ERROR_MESSAGE);
        }
    }
    public void saveCurrentTaskList() {
        try {
            fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                String line = getFormattedTaskLine(tasks.get(i));
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_ERROR_MESSAGE);
        }
    }

    public String getFormattedTaskLine(Task task) {
        String line = "";
        line += task.getLetter() + "|";
        line += (task.isDone() ? "1" : "0") + "|";
        line += task.getDescription() + "|";
        for (int i = 0; i < task.getExtraArguments().length; i ++) {
            line += task.getExtraArguments()[i] + "|";
        }
        return line;
    }
}
