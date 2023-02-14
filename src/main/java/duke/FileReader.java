package duke;

import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static duke.Duke.CAPACITY;

public class FileReader {
    private static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
    private static final char TXT_TODO_WORD = 'T';
    private static final char TXT_DEADLINE_WORD = 'D';
    private static final char TXT_EVENT_WORD = 'E';

    private static Task processContents(String text) throws FileNotFoundException {
        char type = text.charAt(0);
        char status = text.charAt(4);
        String task = text.substring(8);
        switch (type) {
        case TXT_TODO_WORD:
            return new ToDo(task);

        // FIX THIS LATERER!!!!!!!!!!!!!!!!!!!!!!!!
        case TXT_DEADLINE_WORD:
            return new ToDo(task);
        case TXT_EVENT_WORD:
            return new ToDo(task);
        default:
            throw new FileNotFoundException();
        }
    }
    private static Task[] readFileContents(String filePath) throws FileNotFoundException {
        File save = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(save); // create a Scanner using the File as the source
        Task[] allTasks = new Task[CAPACITY];
        int counter = 0;
        while (s.hasNext()) {
            allTasks[counter] = processContents(s.nextLine());
            counter++;
        }
        return allTasks;
    }

//     read from file save.txt
//     process the txt
//     add to allTasks
    public static Task[] initDuke() {
        Task[] allTasks = new Task[CAPACITY];
        try {
            allTasks = readFileContents(FILE_PATH);
            return allTasks;
        } catch (FileNotFoundException e) {
            System.out.println("peepee");
            // create new empty file??? File newTxt = new File(FILE_PATH);
            return allTasks;
        }
    }

    // write from allTasks to save.txt
    private static void exitDuke() {
    }
}
