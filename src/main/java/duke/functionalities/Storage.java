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

public class Storage {

    protected File f;
    protected ArrayList<Task> tasks;
    private final Ui ui;

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

    public void clearFileContents(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write("");
        fw.close();
    }

    public void appendToFile(File f, String textToAppend) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(f, true);
        fw.write(textToAppend);
        fw.close();
    }

    public void printFileContents(File f) throws FileNotFoundException {
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void addFileContents(File f, ArrayList<Task> tasks) throws IOException {
        Scanner s = new Scanner(f);
        int index = 0;
        int taskTypeIndex = 3;
        int taskStatusIndex = 6;
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.charAt(taskTypeIndex) == 'T') {
                int todoIndex = 9;
                tasks.add(new Todo(line.substring(todoIndex)));
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

    public void readDeadlineIntoTasksList(String task, ArrayList<Task> tasks) {
        int descriptionIndex = 9;
        int byIndex = task.indexOf("(by");
        String deadlineDescription = task.substring(descriptionIndex, byIndex);
        int deadlineStartIndex = byIndex + 4;
        int deadlineEndIndex = task.length() - 1;
        String deadline = task.substring(deadlineStartIndex, deadlineEndIndex);
        tasks.add(new Deadline(deadlineDescription, deadline));
    }

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