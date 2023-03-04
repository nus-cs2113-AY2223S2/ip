package duke;

import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    /**
     * Constructor for Storage class.
     * Reads file and populates TaskList object.
     *
     * @param tasks The TaskList object to be populated from the file.
     */
    public Storage(TaskList tasks) {
        readFile(tasks);
    }

    /**
     * Writes the tasks in the TaskList object to a file.
     *
     * @param tasks The TaskList object to be written to a file.
     */
    public static void saveTasks(TaskList tasks) {
        Ui ui = new Ui();
        ui.showLine();
        Storage.writeFile(tasks);
    }

    /**
     * Writes the given TaskList object to a file.
     *
     * @param tasks The TaskList object to be written to a file.
     */
    public static void writeFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            myWriter.flush();
            String strTask = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).toString().contains("[T]")) {
                    strTask = "todoSplitFactString" + tasks.getTask(i).getDescription() +
                            "SplitFactString" + tasks.getTask(i).getMarked();
                } else if (tasks.getTask(i).toString().contains("[D]")) {
                    Deadline dl = (Deadline) tasks.getTask(i);
                    strTask = "deadlineSplitFactString" + dl.getDescription() +
                            "SplitFactString" + dl.getMarked() +
                            "SplitFactString" + dl.getBy();
                } else if (tasks.getTask(i).toString().contains("[E]")) {
                    Event event = (Event) tasks.getTask(i);
                    strTask = "eventSplitFactString" + event.getDescription() +
                            "SplitFactString" + event.getMarked() +
                            "SplitFactString" + event.getStart() +
                            "SplitFactString" + event.getEnd();
                }
                strTask += "\n";
                myWriter.write(strTask);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads from the file and populates the TaskList object.
     *
     * @param tasks The TaskList object to be populated from the file.
     */
    public static void readFile(TaskList tasks) {
        try {
            Files.createDirectories(Path.of("./data"));
            File myObj = new File("./data/duke.txt");

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            System.out.println("An error occurred.");
            try {
                String filePath = "./data/duke.txt";
                ArrayList<String> tmpStrTasks = new ArrayList<>();
                tmpStrTasks = (ArrayList<String>) Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
                for (int i = 0; i < tmpStrTasks.size(); i++) {
                    String[] strTask = tmpStrTasks.get(i).split("SplitFactString");
                    boolean isMark = false;
                    if (tmpStrTasks.get(i).contains("[X]")) {
                        isMark = true;
                    }
                    switch (strTask[0]) {
                    case "todo":
                        Todo todo = new Todo(strTask[1], isMark);
                        tasks.add(todo);
                        break;
                    case "deadline":
                        Deadline dL = new Deadline(strTask[1], isMark, strTask[3]);
                        tasks.add(dL);
                        break;
                    case "event":
                        Event event = new Event(strTask[1], isMark, strTask[3], strTask[4]);
                        break;
                    }
                }
            } catch (IOException | DukeException ioe) {
                ioe.printStackTrace();
            }
        }
    }
