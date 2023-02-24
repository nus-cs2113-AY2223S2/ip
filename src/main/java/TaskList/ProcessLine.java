package TaskList;

import Storage.Storage;
import duke.Deadline;
import duke.Todo;
import duke.Event;
import duke.DukeException;
import duke.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Ui.Print;

public class ProcessLine {

    /**
     * Appends tasks to the file.
     *
     * @param filePath     is the filepath of the file
     * @param textToAppend is the tasks to append to the file
     * @throws IOException Its thrown when an error occurred during an input-output operation
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes tasks to the file when changes are made.
     *
     * @param filePath  is the filepath of the file
     * @param textToAdd is the tasks to overwrite the file when editing the list
     * @throws IOException Its thrown when an error occurred during an input-output operation
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    protected String line;
    protected ArrayList<Task> tasks;

    public ProcessLine(String line, ArrayList<Task> tasks) {
        this.line = line;
        this.tasks = tasks;
    }

    //For "mark", "unmark", "delete"
    public int editTask(String filepath, int index_for_edit, ArrayList<Task> tasks, int i) {

        Print ui;
        ui = new Print(line);

        if (line.toLowerCase().contains("unmark")) {
            tasks.get(index_for_edit - 1).markAsUnDone();

            ui.printUnMark(tasks, index_for_edit);

        } else if (line.toLowerCase().contains("mark")) {
            tasks.get(index_for_edit - 1).markAsDone();
            ui.printMark(tasks, index_for_edit);

        } else if (line.toLowerCase().contains("delete")) {
            ui.printDelete(tasks, index_for_edit, i);
            i -= 1;
            tasks.remove(tasks.get(index_for_edit - 1));
        }

        Storage Storage;
        Storage = new Storage(filepath);
        Storage.saveInFile(tasks, i);
        return i;

    }


    public int createTask(int i, String line) {
        Print ui;
        ui = new Print(line);

        boolean empty;
        empty = false;

        if (line.toLowerCase().contains("todo")) {
            String[] ToSplitTodo = line.split(" ");

            // Checks if the argument is empty
            try {
                String TodoTask = line.toLowerCase().replaceAll("todo", "");
                tasks.add(new Todo(TodoTask, ToSplitTodo.length));

            } catch (DukeException ex) {
                empty = true;
                ui.printEmpty();
            }

            //sample : deadline return book /by Sunday
        } else if (line.toLowerCase().contains("deadline")) {
            String[] ToSplitDeadline = line.split("/");
            String DeadlineTask = ToSplitDeadline[0].toLowerCase().substring(9, ToSplitDeadline[0].length() - 1);
            tasks.add(new Deadline(DeadlineTask, ToSplitDeadline[1].substring(3, ToSplitDeadline[1].length())));

            //sample:event project meeting /from Mon 2pm /to 4pm
        } else if (line.toLowerCase().contains("event")) {
            String[] ToSplitEvent = line.split("/");
            String EventTask = ToSplitEvent[0].toLowerCase().substring(6, ToSplitEvent[0].length());
            tasks.add(new Event(EventTask, ToSplitEvent[1].substring(5, ToSplitEvent[1].length()), ToSplitEvent[2].substring(3, ToSplitEvent[2].length())));

        }

        if (!empty) {
            ui.printTaskAdded(tasks, i);
            i += 1;

            //Updates changes onto the file
            try {
                appendToFile("Duke.txt", tasks.get(i - 1).toString() + "\n");
            } catch (IOException e) {
                ui.printException();
            }
        }
        return i;
    }
}


