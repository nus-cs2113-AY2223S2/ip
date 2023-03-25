/**
 * Class that handles the reading and writing of duke.txt file
 * The file contains the TaskList
 */
package duke;
import java.io.*;
import java.util.ArrayList;
import duke.Tasks.*;

public class Storage {

    /**
     * Creates duke.txt file if it does not exist
     * Reads from duke.txt and stores its lines in a ArrayList
     * @return lines which is the ArrayList
     */
    protected static ArrayList<String> readFile() {
        File file = new File("duke.txt");
        ArrayList<String> lines = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return lines;
    }

    /**
     * Change the String description into the same format that is expected of user input
     * @param description with file read formatting
     * @return description with user input formatting
     */
    protected static String formatFileRead(String description) {
        String toDoDeadline = description.substring(0, description.indexOf("(") - 1);
        String dueDate = description.substring(description.indexOf("(") + 1, description.indexOf(")"));
        description = toDoDeadline + " /" + dueDate;
        return description;
    }
    public static void fillTaskList(TaskList taskList) {
        ArrayList<String> linesToStore = readFile();
        int numberOfLines = linesToStore.size();
        for (String currentLine : linesToStore) {
            String typeOfTask = currentLine.substring(3, 6);
            String statusOfTask = currentLine.substring(7, 10);
            String description = currentLine.substring(11);
            switch (typeOfTask) {
            case "[T]":
                ToDo todo = new ToDo(typeOfTask, statusOfTask, description);
                taskList.adder(todo);
                break;
            case "[D]":
                description = formatFileRead(description);
                Deadline deadline = new Deadline(typeOfTask, statusOfTask, description);
                taskList.adder(deadline);
                break;
            case "[E]":
                description = formatFileRead(description);
                Event event = new Event(typeOfTask, statusOfTask, description);
                taskList.adder(event);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Writes the TaskList to the text file
     * @param taskList which contains ArrayList of Task
     */
    public static void writeToFile(TaskList taskList) {
        File fileToDelete = new File("duke.txt");
        fileToDelete.delete();
        File fileToCreate = new File("duke.txt");
        try {
            FileWriter writer = new FileWriter(fileToCreate);
            for (int i = 0; i < taskList.getList().size(); i++) {
                writer.write((i + 1) + ". " + taskList.getTask(i).getPrintFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
