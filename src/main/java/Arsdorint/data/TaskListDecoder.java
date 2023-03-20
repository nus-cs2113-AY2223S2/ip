package Arsdorint.data;

import Arsdorint.task.Deadline;
import Arsdorint.task.Event;
import Arsdorint.task.Task;
import Arsdorint.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Arsdorint.task.Event.TYPE_EVENT;
import static Arsdorint.task.Todo.TYPE_TODO;

public class TaskListDecoder {
    private static final String PARSE_LIMITER = "\\|";

    /**
     * Read the class that handles decoding task from a line in the file and add Task to the TaskList
     *
     * @param path the path of the file
     * @return TaskList
     */
    public static ArrayList<Task> decodeFile(String path) throws DecodeException, IOException {
        ArrayList<Task> taskListData = new ArrayList<>();
        File file = new File(path);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            taskListData.add(decodeLine(fileReader.nextLine()));
        }
        fileReader.close();
        return taskListData;
    }

    private static Task decodeLine(String nextLine) throws DecodeException {
        try {
            Task add;
            String[] parsedString = nextLine.split(PARSE_LIMITER);
            String command = parsedString[0].trim();
            boolean isDone = Boolean.valueOf(parsedString[1]);
            String description = parsedString[2];
            switch (command) {
            case Todo.TYPE_TODO:
                add = new Todo(isDone,description);
                return add;
            case Event.TYPE_EVENT:
                add = new Event(isDone, description, parsedString[3]);
                return add;
            case Deadline.TYPE_DEADLINE:
                add = new Deadline(isDone, description, parsedString[3]);
                return add;
            default:
                throw new DecodeException();
            }
        } catch (Exception err) {
            throw new DecodeException();
        }
    }
    public static class DecodeException extends Exception {
    }

}
