package Storage;
import Exceptions.FileParseReadingException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Tasks.ToDo;
import Tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String SEPARATOR = " I:I ";

    private static String filePath;
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    public static void createSavedFile() throws IOException {
        File f = new File(filePath); // filePath == "data/taskList.txt"
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println("\nDirectory for file saving created.");
        } else {
            System.out.println("\nDirectory for file saving already exists.");
        }
        if (f.createNewFile()) {
            System.out.println("Save file created.");
        } else {
            System.out.println("Save file already exists.");
        }
        System.out.println();
    }

    public static ArrayList<String> encodeTasksToString(ArrayList<Task> taskList) {
        ArrayList<String> dataToStore = new ArrayList<>();
        String type, status, description, by, from, to, index;
        String temp = "";
        Deadline thisDeadline;
        Event thisEvent;
        dataToStore.add("Tasks List");
        for (int i = 0; i < taskList.size(); i += 1) {
            index = Integer.toString(i+1);
            type = taskList.get(i).getType();
            switch (type) {
            case "T" :
                status = taskList.get(i).getStatusFileIcon();
                description = taskList.get(i).getDescription();
                temp = index + SEPARATOR  + type + SEPARATOR + status + SEPARATOR + description;
                break;
            case "D" :
                thisDeadline = (Deadline) taskList.get(i);
                status = thisDeadline.getStatusFileIcon();
                description = thisDeadline.getDescription();
                by = thisDeadline.getBy();
                temp = index + SEPARATOR + type + SEPARATOR + status + SEPARATOR + description + SEPARATOR + by;
                break;
            case "E" :
                thisEvent = (Event) taskList.get(i);
                status = thisEvent.getStatusFileIcon();
                description = thisEvent.getDescription();
                from = thisEvent.getStartDateTime();
                to = thisEvent.getEndDateTime();
                temp = index + SEPARATOR + type + SEPARATOR + status + SEPARATOR + description + SEPARATOR + from + SEPARATOR + to;
            }
            dataToStore.add(temp);
        }
        return dataToStore;
    }

    public static Task decodeTaskFromStrings(String str) throws FileParseReadingException {
        String[] parsed = str.split(SEPARATOR);
        Task task;
        switch (parsed[1]) {
        case "T":
            task = new ToDo(parsed[3]);
            break;
        case "D":
            task = new Deadline(parsed[3], parsed[4]);
            break;
        case "E":
            task = new Event(parsed[3], parsed[4], parsed[5]);
            break;
        default:
            throw new FileParseReadingException("The task does not meet the parsing requirements for unpacking.");
        }
        if (parsed[2].equals("0")) {
            task.markAsUndone();
        } else {
            task.markAsDone();
        }
        return task;
    }

    //File reading and writing function

    public static void showFileContents() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println();
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static TaskList readFileContents() throws FileNotFoundException, FileParseReadingException {
        ArrayList<Task> list = new ArrayList<>();
        Task temp;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (s.hasNext()) {
            s.nextLine(); // to filter out the title of the file
        }
        while (s.hasNext()) {
            temp = decodeTaskFromStrings(s.nextLine());
            list.add(temp);
        }
        return new TaskList(list);
    }

    public static void writeToFile(ArrayList<String> textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String str : textToAdd) {
            fw.write(str + '\n');
        }
        fw.close();
    }

    public static void saveToFile(TaskList taskList) throws IOException {
        ArrayList<String> linesToWrite;
        linesToWrite = Storage.encodeTasksToString(taskList.getTasksList());
        Storage.writeToFile(linesToWrite);
    }

}
