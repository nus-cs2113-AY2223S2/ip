package Storage;

import duke.Deadline;
import duke.Todo;
import duke.Event;
import duke.DukeException;
import duke.Task;
import Ui.Print;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public final static String filePath = "Duke.txt";

    /**
     * Appends tasks to the file.
     * @param filePath is the filepath of the file
     * @param textToAppend is the tasks to append to the file
     * @throws IOException Its thrown when an error occurred during an input-output operation
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes tasks to the file when changes are made.
     * @param filePath is the filepath of the file
     * @param textToAdd is the tasks to overwrite the file when editing the list
     * @throws IOException Its thrown when an error occurred during an input-output operation
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Storage(){

    }

    /**
     * Loads data from Duke.txt file for the new session
     * @param tasks is the arraylist of tasks
     * @param i is the number of tasks in the task list currently
     * @return the updated number of tasks in the task list currently
     * @throws FileNotFoundException Its thrown when the file is not found
     * @throws DukeException Its thrown when the user input is empty
     */

    public int loadFromFile(ArrayList<Task> tasks, int i)
            throws FileNotFoundException, DukeException {

        File duke = new File(filePath);
        if (duke.exists()) {
            Scanner s = new Scanner(duke); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                i=collect(currentLine, tasks, i);
            }
        }
       return i;
    }

    /**
     * Collects data from Duke.txt file for the new session
     * @param currentLine it is the current string that we are collecting data of
     * @param tasks is the arraylist of tasks
     * @param i is the number of tasks in the task list currently
     * @return the updated number of tasks in the task list currently
     * @throws DukeException Its thrown when the user input is empty
     */

    public int collect(String currentLine, ArrayList<Task> tasks, int i) throws DukeException {


        Character taskLetter = currentLine.charAt(1);
        Character tick = currentLine.charAt(4);

        if (taskLetter == 'T') {
            String taskToDo = currentLine.substring(7, currentLine.length());
            tasks.add(new Todo(taskToDo, 2));

        } else if (taskLetter == 'D') {
            String[] ToSplitDeadline = currentLine.split("by:");

            String DeadlineTask = ToSplitDeadline[0].substring(7, ToSplitDeadline[0].length() - 1);
            String date = ToSplitDeadline[1].substring(0, ToSplitDeadline[1].length() - 1);
            tasks.add(new Deadline(DeadlineTask, date));

        } else if (taskLetter == 'E') {
            String[] ToSplitEvent = currentLine.split("from:");
            String EventTask = ToSplitEvent[0].substring(7, ToSplitEvent[0].length() - 1);
            String[] date = ToSplitEvent[1].split("to:");
            tasks.add(new Event(EventTask, date[0], date[1]));
        }
        i += 1;

        if (tick == 'X') {
            tasks.get(i - 1).markAsDone();
        }
        return i;

    }

    /**
     * Saves changes of tasks made to the file
     * @param tasks is the arraylist of tasks
     * @param i is the number of tasks in the task list currently
     */

    public void saveChanges (ArrayList<Task> tasks, int i) {

        Print ui;
        ui=new Print();

        String newString = "";

        for (int m = 0; m < i; m += 1) {
            int index = m + 1;
            newString += (tasks.get(m) + "\n");
        }

        //Updates changes onto the file
        try {
            writeToFile(filePath, newString);
        } catch (IOException e) {
            ui.printException();
        }
    }

    /**
     * Add tasks to the file
     * @param tasks is the arraylist of tasks
     * @param i is the number of tasks in the task list currently
     */

    public void addToFile (ArrayList<Task> tasks, int i) {

        Print ui;
        ui=new Print();

        try {
            appendToFile(filePath, tasks.get(i - 1).toString() + "\n");
        } catch (IOException e) {
            ui.printException();
        }
    }

}
