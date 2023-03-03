package User;
import Tasks.Deadline;
import Tasks.Task;
import Tasks.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * Reads through the text file and initiates all the saved tasks
     * @param filePath the string that represents the filepath where the text is stored
     * @return The ArrayList with all the stored tasks
     * @throws FileNotFoundException throws an error if the file is not found
     */
    public static ArrayList<Task> readFile(String filePath) throws FileNotFoundException {
        ArrayList<Task> tempTasks = new ArrayList<Task>(100);
        String[] userInputParts;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] newInput = newLine.split(" : ",3);
            String userInput = newInput[2];
            String taskType = newInput[1];
            switch (taskType) {
            case "T":
                Task task = new Task(userInput);
                tempTasks.add(task);
                break;
            case "D":
                userInput = userInput.replace("(","");
                userInput = userInput.replace(")","");
                userInputParts = userInput.split("By:");
                Deadline deadline = new Deadline(userInputParts[0], userInputParts[1]);
                tempTasks.add(deadline);
                break;
            case "E":
                userInput = userInput.replace("(","");
                userInput = userInput.replace(")","");
                userInputParts = userInput.split("From:");
                String description = userInputParts[0];
                userInputParts = userInputParts[1].split("To:");
                Event event = new Event(description, userInputParts[0], userInputParts[1]);
                tempTasks.add(event);
                break;
            default:
                UI.errorReadingFile();
                break;
            }
            if (newInput[0].equals("[X]")) {
                tempTasks.get(tempTasks.size() - 1).markAsDone();
            }
        }
        return tempTasks;
    }

    /**
     * Writes a string into the text file to be stored
     * @param toFile the formatted string from the tasks that are to be written into the text file
     * @throws IOException if an I/O error occurs while writing to this file
     */
    public static void writeToFile(String toFile) throws IOException {
        FileWriter fw = new FileWriter("duke.txt",true);
        fw.write(toFile);
        fw.close();
    }

    /**
     * Wipes the file clean
     * @throws IOException if an I/O error occurs while writing to this file
     */
    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        fw.close();
    }

    /**
     * Updates the text file with the current list of tasks during the latest running of Duke
     * @param taskList the ArrayList of tasks to be updated to the text file
     */
    public static void updateFile(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            try {
                writeToFile(taskList.get(i).toFile());
                if (i < taskList.size()-1){
                    writeToFile("\n");
                }
            } catch (IOException e) {
                System.out.println("ERROR!");
            }
        }
    }

}
