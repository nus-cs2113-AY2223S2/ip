import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Represents a file writer and reader for Duke. A <code>Storage</code> object has the capacity to load pre-existing
 * data upon Duke's start up, and continually update the data file upon any changes.
 */
public class Storage {

    protected static String filePath;

    public Storage(String filePath){
        Storage.filePath = filePath;
    }

    /**
     * Converts data in a text file into <code>Task</code> for storage within the <code>TaskArray</code> of Duke.
     * If no text file is located, creates a blank file to begin from.
     */
    public void load() {
        File loadData = new File(filePath);
        try {
            Scanner newScanner = new Scanner(loadData);
            while (newScanner.hasNext()) {
                processLine(newScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    /**
     * Takes in a formatted string from a text file and creates the appropriate object for storage in <code>storedTasks</code> array.
     * @param line A line of data stored within a text file which has been read from.
     */

    public void processLine(String line) {
        String[] newLine = line.split(" | ", 2);
        switch (newLine[1].substring(2,3)) {
            case "t":
                TaskArray.createToDo(newLine[1].substring(3));
                break;
            case "d":
                TaskArray.createDeadline(newLine[1].substring(3));
                break;
            case "e":
                TaskArray.createEvent(newLine[1].substring(3));
                break;
        }
        if (newLine[0].equals("true")) {
            TaskArray.storedTasks[TaskArray.getCount()-1].markAsDone();
        }
    }


    /**
     * Creates a blank text file that will be updated as Duke's data evolves.
     */
    public static void createFile() {
        try {
            File newFile = new File(filePath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Unknown error occurred. File was not created.");
        }
    }

    /*
    public static void addToFile(Task task){
        try {
            FileWriter file = new FileWriter(filePath, true);
            file.write(task.toFile());
            file.close();
        }catch (java.io.IOException e){
            System.out.println("Unknown error occurred. Task-file was not updated.");
        }
    }
     */

    /**
     * Updates a given text file to reflect changes that user has made within Duke.
     */
    public static void updateFile(){
        try {
            FileWriter file = new FileWriter(filePath);
            for (int i = 0; i < TaskArray.getCount(); i++) {
                file.write(TaskArray.storedTasks[i].toFile());
            }
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }
}
