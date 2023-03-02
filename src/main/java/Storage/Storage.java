package Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    ArrayList<Task> list;
    Path path;
    public Storage(Path path) {
        this.list = new ArrayList<Task>();
        this.path = path;
    }
    public ArrayList<Task> getList() {
        return this.list;
    }
    public ArrayList<Task> readFromFile() throws Exception {
        //Frome https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
        Path path = this.path;
        byte[] tasksInByteForm = new byte[1000000];
        ArrayList<Task> list = new ArrayList<Task>();
        if (Files.exists(path)) {
            try {
                tasksInByteForm = Files.readAllBytes(path);
            } catch (IOException e) {
                //TODO error catching
            }
            String tasksInStringForm = new String(tasksInByteForm);
            String[] arrayOfTasks = tasksInStringForm.split("\n");

//            System.out.println(arrayOfTasks);
            for (String task : arrayOfTasks) {
                Task recreatedTask = createTaskFromString(task);
                list.add(recreatedTask);
            }
        }
        return list;
    }
    private static Task createTaskFromString(String string) throws Exception {
        String[] attributes = string.split("\\|");
        if (attributes.length < 1) {
            System.out.println("Wrong format for task");
        }
        String taskType = attributes[0];
        boolean bool;
        String taskName;
        String boolString;
        String deadline;
        String start;
        switch(taskType) {
            case ("class Tasks.Task"):
                taskName = attributes[1];
                boolString = attributes[2];
                bool = boolString.equals("true");
                return new Task(taskName, bool, 0);
            case ("class Tasks.Deadline"):
                taskName = attributes[1];
                boolString = attributes[2];
                deadline = attributes[3];
                bool = boolString.equals("true");
                return new Deadline(taskName, bool, 0, deadline);
            case ("class Tasks.Event"):
                taskName = attributes[1];
                boolString = attributes[2];
                deadline = attributes[3];
                start = attributes[4];
                bool = boolString.equals("true");
                return new Event(taskName, bool, 0, start, deadline);
            default:
                throw new Exception("Wasn't a task, deadline, or event.");
        }
    }
}
