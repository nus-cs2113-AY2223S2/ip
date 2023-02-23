package duke.functionalities;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class deals with Storage (Loading Tasks from the File and Saving Tasks in the File)
 * */
public class Storage {

    protected File f;
    protected ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Creates a new Duke Data File if file doesn't exist in the System
     *
     * @param FilePath The Path of Duke Data File
     * @param ui The User Interface Class
     * */
    public Storage(String FilePath, Ui ui) {
        this.ui = ui;
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, FilePath);
        String filePath = path.toString();
        try {
            this.f = new File(filePath);
            if (f.createNewFile()) {
                ui.showStatus(true);
            } else {
                ui.showFileExists(f);
            }
        } catch (IOException e) {
            ui.showStatus(false);
        }
    }

    /**
     * Loads the contents of Duke Data File into the Task List
     *
     * @throws DukeException if there are errors in loading file contents
     * @return The Task ArrayList
     * */
    public ArrayList<Task> load() throws DukeException {
        ui.showUserMessage(" loading stored data >>> Here is the content we found: ");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            printFileContents(f);
            addFileContents(f, tasks);
            this.tasks = tasks;
        } catch (FileNotFoundException e) {
            ui.showUserMessage(" File not found");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the Duke Task List contents into the Duke Data File
     * */
    public void saveFile() {
        try {
            clearFileContents(f);
        } catch (IOException e) {
            ui.showSomethingWentWrong(e);
        }
        for (Task userTask : tasks) {
            try {
                appendToFile(f, userTask + System.lineSeparator());
            } catch (IOException e) {
                ui.showSomethingWentWrong(e);
            }
        }
    }

    /**
     * Clears the Contents of Duke Data File
     *
     * @param f The Duke Data File
     * @throws IOException if there are errors encountered when clearing file contents
     * */
    public void clearFileContents(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write("");
        fw.close();
    }

    /**
     * Copies the Duke Task List contents into Duke Data File
     *
     * @param f The Duke Data File
     * @param textToAppend The Duke Task to Copy
     * @throws IOException if there are errors encountered when copying Task List contents
     * */
    public void appendToFile(File f, String textToAppend) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(f, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Prints the Duke Data File Contents to the User
     *
     * @param f The Duke Data File
     * @throws FileNotFoundException if the Duke Data File is not found
     * */
    public void printFileContents(File f) throws FileNotFoundException {
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            ui.showUserMessage(s.nextLine());
        }
    }

    /**
     * Adds Duke Data File contents into the Task List
     *
     * @param f The Duke Data File
     * @param tasks The Task ArrayList
     * @throws IOException if there are errors encountered when adding file contents
     * */
    public void addFileContents(File f, ArrayList<Task> tasks) throws IOException {
        Scanner s = new Scanner(f);
        int index = 0;
        int taskTypeIndex = 3;
        int taskStatusIndex = 6;
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.charAt(taskTypeIndex) == 'T') {
                int todoTaskIndex = 9;
                tasks.add(new Todo(line.substring(todoTaskIndex)));
            } else if (line.charAt(taskTypeIndex) == 'D') {
                readDeadlineIntoTasksList(line, tasks);
            } else if (line.charAt(taskTypeIndex) == 'E') {
                readEventIntoTasksList(line, tasks);
            }
            if (line.charAt(taskStatusIndex) == 'X') {
                tasks.get(index).setTaskStatus(true);
            } else if (line.charAt(taskStatusIndex) == ' ') {
                tasks.get(index).setTaskStatus(false);
            }
            index++;
        }
    }

    /**
     * Reads Stored Deadline tasks from Duke Data File into the Task List
     *
     * @param task The Deadline Task
     * @param tasks The Task ArrayList
     * */
    public void readDeadlineIntoTasksList(String task, ArrayList<Task> tasks) {
        int descriptionIndex = 9;
        int byIndex = task.indexOf("(by");
        String deadlineDescription = task.substring(descriptionIndex, byIndex);
        int deadlineStartIndex = byIndex + 4;
        int deadlineEndIndex = task.length() - 1;
        String deadline = task.substring(deadlineStartIndex, deadlineEndIndex);
        tasks.add(new Deadline(deadlineDescription, deadline));
    }

    /**
     * Reads Stored Event tasks from Duke Data File into the Task List
     *
     * @param task The Event Task
     * @param tasks The Task ArrayList
     **/
    public void readEventIntoTasksList(String task, ArrayList<Task> tasks) {
        int eventIndex = 9;
        int fromIndex = task.indexOf("(from");
        String event = task.substring(eventIndex, fromIndex);
        int eventStartIndex = fromIndex + 6;
        int toIndex = task.indexOf("to");
        String eventStart = task.substring(eventStartIndex, toIndex);
        int eventEndStartIndex = toIndex + 3;
        int eventEndIndex = task.length() - 1;
        String eventEnd = task.substring(eventEndStartIndex, eventEndIndex);
        tasks.add(new Event(event, eventStart, eventEnd));
    }
}