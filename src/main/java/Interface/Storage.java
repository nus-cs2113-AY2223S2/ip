package Interface;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Event;
import Tasks.Deadline;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String FilePath = "data" ; //location of file to be read
    private static String File = "data/duke.txt";

    /**
     * Initializes the storage system, checks if directory and file exists, else create them.
     * Runs readFromFile function responsible for loading the file.
     *
     * @throws IOException exception thrown when input/output error occurs
     */
    public static void initFile() throws IOException {
        File dir = new File(FilePath);
        if(!dir.exists()) {
            dir.mkdir();
            Ui.makeDirectory();
        }
        File f = new File(File);
        if(!f.exists()) {
            f.createNewFile();
            Ui.makeFile();
        }
        try {
            readFromFile();
            Ui.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Copies entries from .txt file into ArrayList of tasks. Identifies type of task then adds
     * them into task list accordingly
     *
     * @throws IOException exception thrown when input/output error occurs
     */
    public static void readFromFile() throws IOException {
        ArrayList<Task> tasks = TaskList.getTasks();
        File f = new File(File);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String text = s.nextLine();
            String[] arrayOfText = text.split(":");
            String type = arrayOfText[0];
            switch (type) {
            case "todo":
                Todo newTodo = new Todo(arrayOfText[2].trim());
                if ((arrayOfText[1].equals("1"))) {
                    newTodo.markAsDone();
                } else {
                    newTodo.markAsNotDone();
                }
                tasks.add(newTodo);
                break;
            case "deadline":
                Deadline newDeadline = new Deadline(arrayOfText[2].trim(), arrayOfText[3].trim());
                if ((arrayOfText[1].equals("1"))) {
                    newDeadline.markAsDone();
                } else {
                    newDeadline.markAsNotDone();
                }
                tasks.add(newDeadline);
                break;
            case "event":
                Event newEvent = new Event(arrayOfText[2].trim(), arrayOfText[3].trim(), arrayOfText[4].trim());
                if ((arrayOfText[1].equals("1"))) {
                    newEvent.markAsDone();
                } else {
                    newEvent.markAsNotDone();
                }
                tasks.add(newEvent);
                break;
            default:
                System.out.println("Failed to read from save file");
            }
        }
    }

    /**
     * Copies entries in ArrayList of tasks into a .txt file following a specified format.
     * This is called everytime the list of tasks is changed.
     */
    public static void writeToFile() {
        ArrayList<Task> tasks = TaskList.getTasks();
        try {
            FileWriter fw = new FileWriter(File);
            for(int i = 0; i < tasks.size(); i += 1) {
                Task currentTask = tasks.get(i);
                String taskType = currentTask.getType();
                String isDone = String.valueOf(currentTask.getIsDone());
                String description = currentTask.getDescription();
                if(taskType.equals("todo")) {
                    fw.write(taskType + ":" + isDone + ":" + description + System.lineSeparator());
                } else if(taskType.equals("deadline")) {
                    Deadline task = (Deadline) currentTask;
                    fw.write(taskType + ":" + isDone + ":" + description + ":" + task.getBy() + System.lineSeparator());
                } else if(taskType.equals("event")) {
                    Event task = (Event) currentTask;
                    fw.write(taskType + ":" + isDone + ":" + description + ":" + task.getFrom() + ":" + task.getTo() + System.lineSeparator());
                } else {
                    System.out.println("Corrupted task detected, unable to write to save file");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to save file");
        }
    }
}
