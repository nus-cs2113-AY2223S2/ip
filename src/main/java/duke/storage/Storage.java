package duke.storage;

import duke.exception.FolderNotFoundException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage object that handles loading and saving of user data
 */
public class Storage {
    private final String filePath;
    private final String folderName;

    public Storage(String folderName, String filePath) {
        this.filePath = filePath;
        this.folderName = folderName;
    }

    /**
     * Loads user data
     *
     * @throws FolderNotFoundException if folder is not found
     * @throws FileNotFoundException   if file is not found
     */
    public void load() throws FolderNotFoundException, FileNotFoundException {
        File folder = new File(folderName);
        if (!folder.exists()) {
            throw new FolderNotFoundException();
        }
        File file = new File(filePath);
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String row = s.nextLine();
            String[] timeSpan = new String[2]; //String array of size 2;
            String date = null;
            int posOfStartBracket = row.indexOf("(");
            int posOfEndBracket = row.indexOf(")");
            if (row.contains("[E]")) {
                timeSpan = (row.substring(posOfStartBracket + 1, posOfEndBracket)).split(" To: ", 2);
                timeSpan[0] = timeSpan[0].replaceFirst("From: ", "");
            }
            String taskIdentifier = row.substring(0, 6);
            if (row.contains("[D]")) {
                date = row.substring(posOfStartBracket + 1, posOfEndBracket).replace("By: ", "");
            }
            switch (taskIdentifier) {
                case "[T][ ]":
                    TaskList.addToList(new Todo(row.substring(7), false));
                    break;
                case "[T][X]":
                    TaskList.addToList(new Todo(row.substring(7), true));
                    break;
                case "[D][ ]":
                    TaskList.addToList(new Dateline(row.substring(7, posOfStartBracket - 1), false,
                            date));
                    break;
                case "[D][X]":
                    TaskList.addToList(new Dateline(row.substring(7, posOfStartBracket - 1), true,
                            date));
                    break;
                case "[E][ ]":
                    TaskList.addToList(new Event(row.substring(7, posOfStartBracket - 1), false,
                            timeSpan[0], timeSpan[1]));
                    break;
                case "[E][X]":
                    TaskList.addToList(new Event(row.substring(7, posOfStartBracket - 1), true,
                            timeSpan[0], timeSpan[1]));
                    break;
            }
        }
    }

    /**
     * Saves user data
     *
     * @throws IOException if trouble accessing files
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int num = 1; num <= TaskList.getNumberOfTasks(); ++num) {
            Tasks thisTask = TaskList.getTaskList().get(num - 1);
            fw.write(String.valueOf(thisTask));
            fw.write("\n");
        }
        fw.close();
    }

    public void createNewFile() throws IOException {
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("File created");
        } else
            System.out.println("File already exists");
    }

    public void createNewFolder() {
        File folder = new File(folderName);
        if (folder.mkdir()) {
            System.out.println("Folder created");
        } else {
            System.out.println("Folder already exists");
        }
    }
}
