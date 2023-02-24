package duke.file;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;

import duke.Common;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.ToDo;
import duke.tasktypes.Task;

public class FileClass {
    public File directory;
    public File file;
    public ArrayList<Task> tasks;
    public static final String FILE_PATH = "data.txt";
    public static final String WRITEFILE_EXCEPTION_MESSAGE = "OPPS!!! Something went wrong when you write to data file";

    public FileClass(String filePath, ArrayList<Task> tasks) {
        directory = new File("data");
        directory.mkdirs();
        file = new File(directory, filePath);
        this.tasks = tasks;
    }

    public void moveDataToArrayList() {
        try {
            file.createNewFile();
            this.addTasks();
        } catch (Exception error) {
            // no code needed here
        }
    }
    public void addTasks() throws FileNotFoundException {
        Scanner s = new Scanner(file); // create a Scanner using the file as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            addToTaskList(line);
        }
    }

    public void addToTaskList(String line) {
        String[] elements = line.split(Pattern.quote(" | "));
        if (elements[0].equals("T")) {
            addTodoToTaskList(elements);
        } else if (elements[0].equals("D")) {
            addDeadlineToTaskList(elements);
        } else {
            addEventToTaskList(elements);
        }
    }

    private void addTodoToTaskList(String[] elements) {
        ToDo task = new ToDo(elements[2]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    private void addDeadlineToTaskList(String[] elements) {
        Deadline task = new Deadline(elements[2], elements[3]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    private void addEventToTaskList(String[] elements) {
        Event task = new Event(elements[2], elements[3], elements[4]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    private void setTaskStatus(String taskStatus, Task task) {
        if (taskStatus.equals("1")) {
            task.mark();
        } else {
            task.unMark();
        }
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(directory + "/" + filePath, true);
        fileWriter.write(textToAppend);
        fileWriter.close();
    }

    public void appendTaskToDataFile(String inputToDataFile) {
        try {
            Common.dataFile.appendToFile(FILE_PATH, inputToDataFile);
        } catch (IOException e) {
            System.out.println(WRITEFILE_EXCEPTION_MESSAGE);
        }
    }
    public void deleteTask(String filePath, int lineNumber) throws IOException {
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        ArrayList<String> extractedTasks = new ArrayList<>();
        int lineCount = 0;
        while (s.hasNext()) {
            ++lineCount;
            String line = s.nextLine();
            if (lineCount == lineNumber) {
                continue;
            }
            extractedTasks.add(line);
        }
        String newFileContent = "";
        for (String line : extractedTasks) {
            newFileContent += (line + "\n");
        }
        FileWriter fileWriter = new FileWriter(directory + "/" + filePath);
        fileWriter.write(newFileContent);
        fileWriter.close();
    }
}
