import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private List<Task> taskList = new ArrayList<Task>();
    private static String fileName = "duke.txt";

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        pullFileData();
        System.out.println("What can I do for you?");
    }

    // public void addTask(String taskName){
    // Task t = new Task(taskName);
    // taskList.add(t);
    // System.out.printf(
    // "Got it. I've added this task:\n" +
    // String.format("added: %s\n", taskName)

    // );
    // }

    public void addEvent(String taskName) {
        Event t = new Event(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addTodo(String taskName) {
        Todo t = new Todo(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addDeadline(String taskName) {
        Deadline t = new Deadline(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void changeTaskState(boolean doneState, Integer index) {
        if (index > taskList.size()) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            if (doneState) {
                taskList.get(index).markAsDone();
            } else {
                taskList.get(index).markAsUndone();
            }
        }
    }

    public void delete(int index) {
        if (index > taskList.size()) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            System.out.printf("Noted. I've removed this task:" +
                    taskList.get(index).toString());
            taskList.remove(index);
            System.out.println(String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
        }
    }

    public void list() {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            Integer i = 0;
            for (Task task : taskList) {
                System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                i++;
            }
        }
    }

    private void pullFileData() {
        File file = new File(fileName);
        String data;
        try {
            if (!file.createNewFile()) {
                Scanner fileData = new Scanner(file);
                while (fileData.hasNext()) {
                    data = fileData.nextLine();
                    String[] inputArgs = data.split("|");
                    addFileData(inputArgs);
                }
                fileData.close();
            }
        } catch (IOException e) {
            System.out.print("\nError getting file data");
        }
        System.out.println("These are the tasks from your file:\n");
        list();
    }

    private void addFileData(String[] inputArgs) {
        Task newTask;
        String command = inputArgs[0];
        boolean taskStatus = Boolean.parseBoolean(inputArgs[1]);
        switch (command) {
            case "T":
                newTask = new Todo(inputArgs[2]);
                break;
            case "D":
                newTask = new Deadline(inputArgs[2]);
                break;
            case "E":
                newTask = new Event(inputArgs[2]);
                break;
            default:
                throw new IllegalStateException("File contents are invalid");
        }
        if (taskStatus) {
            newTask.markAsDone();
        }
        taskList.add(newTask);
    }

    public void saveToFile() {
        try {
            FileWriter fWriter = new FileWriter(fileName);
            for (Task task : taskList) {
                fWriter.write(task.fileFormat());
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print("IOException Error: data not saved to file\n");
        }
    }
}
