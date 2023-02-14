package duke;

import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.exception.InvalidSaveFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.Duke.CAPACITY;
import static duke.Duke.allTasks;

public class Storage {
    public static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
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
            markStatus(status, newToDo);
            return newToDo;
        case TXT_DEADLINE_WORD:
            final String[] paramAndBy;
            try {
                paramAndBy = Processor.deadline(param);
            } catch (InvalidDeadline e) {
                throw new InvalidSaveFile();
            }
            Deadline newDeadline = new Deadline(paramAndBy[0], paramAndBy[1]);
            markStatus(status, newDeadline);
            return newDeadline;
        case TXT_EVENT_WORD:
            final String[] paramAndFromTo;
            try {
                paramAndFromTo = Processor.event(param);
            } catch (InvalidEvent e) {
                throw new InvalidSaveFile();
            }
            Event newEvent = new Event(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            markStatus(status, newEvent);
            return newEvent;
        default:
            throw new InvalidSaveFile();
        }
    }

    private static void markStatus(char status, Task newTask) {
        if (status == 'X') {
            newTask.setDone(true);
        }
    }

    private static Task[] readFileContents(File save) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        Task[] newAllTasks = new Task[CAPACITY];
        int counter = 0;
        while (s.hasNext()) {
            try {
                newAllTasks[counter] = addTask(s.nextLine());
                counter++;
            } catch (InvalidSaveFile e) {
                Output.printInvalidSaveFile(counter);
            }
        }
        return newAllTasks;
    }

    public static Task[] initDuke() throws IOException {
        Task[] newAllTasks = new Task[CAPACITY];
        File save = new File(FILE_PATH);
        try {
            newAllTasks = readFileContents(save);
            return newAllTasks;
        } catch (FileNotFoundException e) {
            Output.printErrorFileNotFound();
            save.createNewFile();
            return newAllTasks;
        }
    }

    public static void updateDuke() throws IOException {
        FileWriter overwrite = new FileWriter(FILE_PATH);
        for (int i = 0; i < Task.getCounter(); i++) {
            Task task = allTasks[i];
            String desc = task.getDescription();
            String type = task.getType();
            String stat = task.getStatus();
            switch (type) {
            case "todo":
                overwrite.write("T | " + stat + " | " + desc + "\n");
                break;
            case "deadline":
                Deadline tempDeadline = (Deadline) task;
                String by = tempDeadline.getBy();
                overwrite.write("D | " + stat + " | " + desc + " /by " + by + "\n");
                break;
            case "event":
                Event tempEvent = (Event) task;
                String from = tempEvent.getFrom();
                String to = tempEvent.getTo();
                overwrite.write("E | " + stat + " | " + desc + " /from " + from + " /to " + to + "\n");
                break;
            }
        }
        overwrite.close();
    }
}
