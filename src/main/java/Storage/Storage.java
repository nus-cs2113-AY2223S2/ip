package Storage;
import Exceptions.DukeException;
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
    public ArrayList<Task> readFromFile(Path filePath) throws DukeException {
        //Frome https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
//        Path path = this.path;
        byte[] tasksInByteForm = new byte[1000000];
        ArrayList<Task> list = new ArrayList<Task>();
        if (Files.exists(path)) {
            try {
                tasksInByteForm = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new DukeException("File unreadable! Unable to call readAllBytes() on the path given");
            }
            String tasksInStringForm = new String(tasksInByteForm);
            String[] arrayOfTasks = tasksInStringForm.split("\n");

//            System.out.println(arrayOfTasks);
            for (String task : arrayOfTasks) {
                Task recreatedTask = createTaskFromString(task);
                if (recreatedTask == null) {
                    System.out.println("Unable to create task " + task);
                    continue;
                }
                list.add(recreatedTask);
            }
        }
        return list;
    }
    private static Task createTaskFromString(String string){
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
                System.out.println(taskType + " is not an accepted task type");
                return null;
        }
    }
}
