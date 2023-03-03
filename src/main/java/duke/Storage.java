package duke;

import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {


    public Storage(TaskList tasks) {
        readFile(tasks);
    }


    public static void saveTasks(TaskList tasks) {
        Ui ui = new Ui();
        ui.showLine();
        Storage.writeFile(tasks);
    }

    public static void writeFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            myWriter.flush();
            String strTask = "";
            for (int i = 0; i < tasks.getSize(); i++) {
//                myWriter.write(tasks.getTask(i).toString() + "\n");
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
    public static void readFile(TaskList tasks) {
        try {
            ArrayList<String> tmpStrTasks = new ArrayList<>();
            tmpStrTasks = (ArrayList<String>) Files.readAllLines(Paths.get("./data/duke.txt"), StandardCharsets.UTF_8);

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

//                        insertTodo("todo " + newTask.substring(7), isMark);
                        break;
                    case "deadline":
                        Deadline dL = new Deadline(strTask[1], isMark, strTask[3]);
                        tasks.add(dL);
//                        insertDeadline("deadline " + newTask.substring(7), isMark);
                        break;
                    case "event":

                        Event event = new Event(strTask[1], isMark, strTask[3], strTask[4]);
//                        insertEvent("event " + newTask.substring(7), isMark);
                        break;
                }
            }
        } catch (IOException | DukeException ioe) {
            ioe.printStackTrace();
        }
    }
}
