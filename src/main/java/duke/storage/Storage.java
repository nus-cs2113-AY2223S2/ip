package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import duke.TaskList;
import duke.data.*;

public class Storage {
    private static String filePath = "";

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static void FileWriting(TaskList tasks) {
        //File f = new File(filePath);
        try {
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write("");
            FileWriter fw2 = new FileWriter(filePath, true);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task t = tasks.getTask(i);
                if (t instanceof Todo) {
                    Todo todo = (Todo) t;
                    String text = "T|" + todo.getStatusIcon() + "|" + todo.getDescription() + "\n";
                    fw2.write(text);
                } else if (t instanceof Deadline) {
                    Deadline deadline = (Deadline) t;
                    String text = "D|" + deadline.getStatusIcon() + "|" + deadline.getDescription() + "|" + deadline.getBy() + "\n";
                    fw2.write(text);
                } else if (t instanceof Event) {
                    Event event = (Event) t;
                    String text = "E|" + event.getStatusIcon() + "|" + event.getDescription() + "|" + event.getFrom() + "|" + event.getTo() + "\n";
                    fw2.write(text);
                }
            }
            fw1.close();
            fw2.close();
            System.out.println("Your task list is saved!");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void FileReading(TaskList tasks) {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] text = line.split("\\|");
                if (line.startsWith("T")) {
                    Todo todo = new Todo(text[2]);
                    todo.setDone(!text[1].equals(" "));
                    tasks.addTask(todo);
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(text[2], text[3]);
                    deadline.setDone(!text[1].equals(" "));
                    tasks.addTask(deadline);
                } else if (line.startsWith("E")) {
                    Event event = new Event(text[2], text[3], text[4]);
                    event.setDone(!text[1].equals(" "));
                    tasks.addTask(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            System.out.println("Sorry no line found. ");
        }
    }

    public static void saveFile(TaskList tasks) {
        Storage.FileWriting(tasks);
    }




}
