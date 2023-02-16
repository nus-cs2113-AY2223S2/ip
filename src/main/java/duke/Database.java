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

    public static final int COMMAND_INDEX = 0;
    public static final int TASK_DESCRIPTION_INDEX = 2;
    public static final int TASK_DEADLINE_INDEX = 3;
    public static final int TASK_FROM_INDEX = 3;
    public static final int TASK_TO_INDEX = 4;
    public static final int TASK_MARK_INDEX = 1;
    public static final String DIRECTORY_NAME = "data";
    public static final String FILE_PATH = "data/duke.txt";

    protected ArrayList<Task> tasks;

    protected ArrayList<String> databaseString;


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
            File directory = new File(DIRECTORY_NAME);
            directory.mkdirs();
            savedData.createNewFile();
        }
    }

    private void dataConversion() {
        for (String data : databaseString) {
            String[] SplitTaskConstituents = data.split(Task.COMMA_TASK_SEPARATOR);
            Task task = null;
            boolean isCorrupted = false;
            switch (SplitTaskConstituents[COMMAND_INDEX]) {
            case "T":
                task = new Todo(SplitTaskConstituents[TASK_DESCRIPTION_INDEX]);
                break;
            case "D":
                task = new Deadline(SplitTaskConstituents[TASK_DESCRIPTION_INDEX],
                        SplitTaskConstituents[TASK_DEADLINE_INDEX]);
                break;
            case "E":
                task = new Event(SplitTaskConstituents[TASK_DESCRIPTION_INDEX], SplitTaskConstituents[TASK_FROM_INDEX],
                        SplitTaskConstituents[TASK_TO_INDEX]);
                break;
            default:
                isCorrupted = true;
                break;
            }
            updateMarkings(SplitTaskConstituents, task, !isCorrupted);
        }
    }

    private void updateMarkings(String[] information, Task task, boolean isNotCorrupted) {
        if (isNotCorrupted) {
            if (information[TASK_MARK_INDEX].equals("true")) {
                task.setDone("mark");
            } else if (information[TASK_MARK_INDEX].equals("false")) {
                task.setDone("unmark");
            }
            tasks.add(task);
        }
    }
}
