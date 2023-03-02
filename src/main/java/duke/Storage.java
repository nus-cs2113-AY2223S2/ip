package duke;
import duke.addable.*;
import duke.exception.ArgumentBlankException;
import duke.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/tasklist.txt";
    private static final String[] FILE_ERROR_MESSAGE = {
            "Something went wrong when opening the saved tasks file"
    };
    private FileWriter fw;
    private Ui ui;
    private File f;
    private ArrayList<Task> tasks = new ArrayList<>();
    public Storage(Ui ui) {
        try {
            this.ui = ui;
            f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            readCurrentTaskList();
        } catch (IOException e) {
            System.out.println("Couldn't find required file " + FILE_PATH);
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
        System.out.println(line);
        try {
            switch (fields[0]) {
            case "T":
                System.out.println("\\");
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
            ui.printMessage(FILE_ERROR_MESSAGE);
        }
        System.out.println(tasks);


    }
    public void readCurrentTaskList() {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println("has next");
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
