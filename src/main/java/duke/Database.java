package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Database {

    protected ArrayList<Task> tasks;

    protected ArrayList<String> databaseString;
    public static final String FILE_PATH = "data/duke.txt";

    public Database() {
        this.tasks = new ArrayList<>();
        this.databaseString = new ArrayList<>();
        try {
            initialise();
        } catch (IOException e) {
            System.out.println("failed to initialise database");
        }
    }

    public void saveAddTask(String information) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(writeTask(information));
        fw.close();
    }

    public void updateDatabaseTask() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.size(); ++i) {
            fw.write(writeTask(tasks.get(i).getTaskString()));
        }
        fw.close();
    }

    public static String writeTask(String information) {
        information += System.lineSeparator();
        return information;
    }

    private void initialise() throws IOException {
        File savedData = new File(FILE_PATH);
        if (savedData.exists()) {
            Scanner contents = new Scanner(savedData);
            while (contents.hasNext()) {
                databaseString.add(contents.nextLine());
            }
            dataConversion();
        } else {
            File directory = new File("data");
            directory.mkdirs();
            savedData.createNewFile();
        }
    }

    private void dataConversion() {
        for (String data : databaseString) {
            String[] information = data.split(" , ");
            Task task = null;
            boolean isCorrupted = false;
            switch (information[0]) {
            case "T":
                task = new Todo(information[2]);
                break;
            case "D":
                task = new Deadline(information[2], information[3]);
                break;
            case "E":
                task = new Event(information[2], information[3], information[4]);
                break;
            default:
                isCorrupted = true;
            }
            if (!isCorrupted) {
                if (information[1].equals("true")) {
                    task.setDone("mark");
                } else if (information[1].equals("false")) {
                    task.setDone("unmark");
                }
                tasks.add(task);
            }
        }
    }
}
