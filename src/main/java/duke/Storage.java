package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static TaskList initialTaskList;
    private static final String FILE_NOT_FOUND_MESSAGE = "File Not Found";

    public Storage() {
        initialTaskList = new TaskList();
    }

    private void writeTasksToFile(TaskList taskList, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = taskList.getTaskList();
        for (Task t : tasks) {
            if (t != null) {
                fw.write(t.saveFormat() + System.lineSeparator());
            }
        }

        fw.close();
    }

    public void save(TaskList taskList, String filename) throws IOException {
        try {
            writeTasksToFile(taskList, filename);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public TaskList load(String filename) {
        File f = new File(filename);

        if (f.exists()) {
            try {
                loadFileContents(f);
            } catch (FileNotFoundException e) {
                System.out.println(FILE_NOT_FOUND_MESSAGE);
            }
        }
        return initialTaskList;
    }

    private static void loadFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            processFileContents(s.nextLine());
        }
    }

    private static void processFileContents(String line) {
        String[] words = line.split("\\|");
        String type = words[0];
        boolean isDone = false;
        if (Integer.valueOf(words[1]) == 1) {
            isDone = true;
        }
        switch (type) {
        case "T":
            loadTodo(words, isDone);
            break;
        case "E":
            loadEvent(words, isDone);
            break;
        case "D":
            loadDeadline(words, isDone);
            break;
        default:
            // unknown char error
        }
    }

    private static void loadDeadline(String[] words, boolean isDone) {
        Deadline deadline = new Deadline(words[2], words[3]);
        if (isDone) {
            deadline.markAsDone();
        }
        initialTaskList.addDeadline(deadline);
    }

    private static void loadEvent(String[] words, boolean isDone) {
        Event event = new Event(words[2], words[3], words[4]);
        if (isDone) {
            event.markAsDone();
        }
        initialTaskList.addEvent(event);
    }

    private static void loadTodo(String[] words, boolean isDone) {
        Todo todo = new Todo(words[2]);
        if (isDone) {
            todo.markAsDone();
        }
        initialTaskList.addTodo(todo);
    }

}
