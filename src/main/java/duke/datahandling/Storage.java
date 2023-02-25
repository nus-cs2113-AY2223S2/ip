package duke.datahandling;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.userinterface.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /** Constant to store location of the file used for writing/storing of data. */
    public final String FILE_LOCATION = "duke.txt";

    /** Instance of Ui class for User Interface operations. */
    public static final Ui dukeUserInterface = new Ui();

    /**
     * Reads the strings that are stored in the .txt file and converts them into editable data
     * within the program by storing them on an ArrayList.
     * @param list The ArrayList used to store all the information found in the .txt file.
     * @return Returns the final size of the ArrayList "list" after populating the ArrayList.
     * @throws IOException Exception related to all input and output errors.
     */
    public int readFileData(ArrayList<Task> list) throws IOException {
        File database = new File(FILE_LOCATION);
        if (database.exists()) {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNext()) {
                String fileLine = fileReader.nextLine();
                String[] fileData = fileLine.split("/");
                uploadDataToArrayList(fileData, list);
            }
            dukeUserInterface.printLineSeparator();
            System.out.println("File found! Loading file from: " + database.getAbsolutePath());
            System.out.println("You currently have " + list.size() + " tasks in your database.");
            dukeUserInterface.printLineSeparator();
        } else {
            dukeUserInterface.printMissingFileError();
            File newFile = new File("docs");
            boolean directoryCreated = newFile.mkdirs();
            boolean fileCreated = database.createNewFile();
            if (directoryCreated || fileCreated) {
                dukeUserInterface.printFileCreationStatus(database.getAbsolutePath());
            }
        }
        return list.size();
    }

    /**
     * Processes what is written in the first string of each line of the .txt file and decides
     * what is to be written into the ArrayList for storage.
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    public void uploadDataToArrayList(String[] fileData, ArrayList<Task> list) {
        switch (fileData[0]) {
        case "T":
            transferToDo(fileData, list);
            break;
        case "D":
            transferDeadline(fileData, list);
            break;
        case "E":
            transferEvent(fileData, list);
            break;
        }
    }

    /**
     * If line found in .txt file is determined to be a task of type "Event", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private void transferEvent(String[] fileData, ArrayList<Task> list) {
        Event newEventTask = new Event(fileData[2], fileData[3], fileData[4]);
        if (fileData[1].equals("1")) {
            newEventTask.markDone();
        }
        list.add(newEventTask);
    }

    /**
     * If line found in .txt file is determined to be a task of type "Deadline", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private void transferDeadline(String[] fileData, ArrayList<Task> list) {
        Deadline newDeadlineTask = new Deadline (fileData[2], fileData[3]);
        if (fileData[1].equals("1")) {
            newDeadlineTask.markDone();
        }
        list.add(newDeadlineTask);
    }

    /**
     * If line found in .txt file is determined to be a task of type "To-Do", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private void transferToDo(String[] fileData, ArrayList<Task> list) {
        ToDo newToDo = new ToDo(fileData[2]);
        if (fileData[1].equals("1")) {
            newToDo.markDone();
        }
        list.add(newToDo);
    }

    /**
     * Writes information stored on the ArrayList "list" into the .txt file, effectively saving the information
     * contained in the "list" for future use.
     *
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @throws IOException Exception that is generated due to errors in input or output.
     */
    public void writeFileData(ArrayList<Task> list) throws IOException{
        FileWriter fileRewriter = new FileWriter(FILE_LOCATION);
        for (Task task : list) {
            String taskType = getTaskType(task);
            fileRewriter.write(taskType + "/" + task.getBooleanValueOfStatus() + "/" + task.getDescription());
            if (taskType.equals("D")) {
                fileRewriter.write("/" + ((Deadline) task).getBy());
            } else if (taskType.equals("E")) {
                fileRewriter.write("/" + ((Event) task).getFrom() + "/" + ((Event) task).getTo());
            }
            fileRewriter.write(System.lineSeparator());
        }
        fileRewriter.close();
    }

    /**
     * Retrieves the class of the object (To-Do, Event, Deadline etc.)
     * Generates a single-letter string to be used for writing to the .txt file.
     *
     * @param task A specific task found in the ArrayList "list".
     * @return A single-letter string representing the different class types (e.g. T,D,E)
     */
    private static String getTaskType(Task task) {
        if (task.getClass().toString().equals("class duke.task.ToDo")) {
            return "T";
        }
        if (task.getClass().toString().equals("class duke.task.Deadline")) {
            return "D";
        }
        return "E";
    }

}
