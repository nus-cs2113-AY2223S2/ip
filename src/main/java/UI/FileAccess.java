package UI;

import Tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class FileAccess {
    private static String filePath = "data" ; //location of file to be read
    private static String file = "data/duke.txt";
    public static void initFile() throws IOException {
        File dir = new File(filePath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(file);
        if(!f.exists()) {
            f.createNewFile();
        }
        try {
            readFromFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readFromFile() throws IOException {
        ArrayList<Task> tasks = TaskList.getTasks();
        File f = new File(file);
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
                System.out.println("Failed to copy save file");
            }
        }
    }
    public static void writeToFile() {
        ArrayList<Task> tasks = TaskList.getTasks();
        try {
            FileWriter fw = new FileWriter(file);
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
                    System.out.println("Corrupted task detected");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

    }
}
