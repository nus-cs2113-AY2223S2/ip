package duke;

import duke.exception.InvalidSaveFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.Duke.CAPACITY;
import static duke.Output.DIVIDER;

public class FileReader {
    private static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
    private static final char TXT_TODO_WORD = 'T';
    private static final char TXT_DEADLINE_WORD = 'D';
    private static final char TXT_EVENT_WORD = 'E';

    private static Task addTask(String text) throws InvalidSaveFile {
        char type = text.charAt(0);
        char status = text.charAt(4);
        String param = text.substring(8);
        switch (type) {
        case TXT_TODO_WORD:
            ToDo newToDo = new ToDo(param);
            if (status == '1') {
                newToDo.setDone(true);
            }
            return newToDo;
        case TXT_DEADLINE_WORD:
            final String[] paramAndBy = Processor.deadline(param);
            Deadline newDeadline = new Deadline(paramAndBy[0], paramAndBy[1]);
            if (status == '1') {
                newDeadline.setDone(true);
            }
            return newDeadline;
        case TXT_EVENT_WORD:
            final String[] paramAndFromTo = Processor.event(param);
            Event newEvent = new Event(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            if (status == '1') {
                newEvent.setDone(true);
            }
            return newEvent;

        // FIX THIS LATERER!!!!!!!!!!!!!!!!!!!!!!!!
        default:
            throw new InvalidSaveFile();
        }
    }
    private static Task[] readFileContents() throws FileNotFoundException {
        File save = new File(FILE_PATH);
        Scanner s = new Scanner(save);
        Task[] allTasks = new Task[CAPACITY];
        int counter = 0;
        while (s.hasNext()) {
            try {
                allTasks[counter] = addTask(s.nextLine());
                counter++;
            } catch (InvalidSaveFile e) {
                System.out.println(DIVIDER + "There is an error in save.txt at line " + (counter+1) + "\n" +
                        "Please edit the save file at " + FILE_PATH + "and try again. Sorry!\n" + DIVIDER);
            }
        }
        return allTasks;
    }

    public static Task[] initDuke() {
        Task[] allTasks = new Task[CAPACITY];
        try {
            allTasks = readFileContents();
            return allTasks;
        } catch (FileNotFoundException e) {
            return allTasks;
        }
    }

    // write from allTasks to save.txt
    private static void exitDuke() {
        // delete existing save.txt
        // write new save.txt
    }
}
