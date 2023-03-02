package duke;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Storage class deals with saving and loading TaskManager class.
 */
public class Storage extends TaskManager {

    /**
     * Loads data from a file into the given TaskManager object.
     *
     * @param listOfItems The TaskManager object to load the data into.
     * @return The TaskManager object with the loaded data.
     * @throws FileNotFoundException If the file to load from is not found.
     */
    public static TaskManager loadFile(TaskManager listOfItems) throws FileNotFoundException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String l = s.nextLine();
            if (l.startsWith(" [T]")) {
                loadTodo(listOfItems, l);
            } else if (l.startsWith(" [D]")) {
                loadDeadline(listOfItems, l);
            } else if (l.startsWith(" [E]")) {
                loadEvent(listOfItems, l);
            }
        }
        s.close();
        return listOfItems;
    }

    /**
     * Loads a Todo item from a string and adds it to the given TaskManager object.
     *
     * @param listOfItems The TaskManager object to add the Todo item to.
     * @param l           he string containing the Todo item details.
     */
    public static void loadTodo(TaskManager listOfItems, String l) {
        String name = l.substring(7).trim();
        if (l.contains("[ ]")) {
            listOfItems.addTask(name, false);
        } else {
            listOfItems.addTask(name, true);
        }
    }

    /**
     * Loads an Event item from a string and adds it to the given TaskManager
     * object.
     *
     * @param listOfItems The TaskManager object to add the Event item to.
     * @param l           The string containing the Event item details.
     */
    public static void loadEvent(TaskManager listOfItems, String l) {
        int fromIndex = l.indexOf("(from: ");
        int toIndex = l.indexOf(" to: ", fromIndex);
        String name = l.substring(8, fromIndex).trim();
        String startTime = l.substring(fromIndex + 7, toIndex);
        String finishTime = l.substring(toIndex + 5, l.length() - 1);
        if (l.contains("[ ]")) {
            listOfItems.addEvent(name, startTime, finishTime, false);
        } else {
            listOfItems.addEvent(name, startTime, finishTime, true);
        }
    }

    /**
     * Loads a Deadline item from a string and adds it to the given TaskManager
     * object.
     *
     * @param listOfItems The TaskManager object to add the Deadline item to.
     * @param l           The string containing the Deadline item details.
     */
    public static void loadDeadline(TaskManager listOfItems, String l) {
        int byIndex = l.indexOf("(by: ");
        String name = l.substring(8, byIndex - 1);
        int endIndex = l.indexOf(")");
        String deadline = l.substring(byIndex + 5, endIndex);
        if (l.contains("[ ]")) {
            listOfItems.addDeadline(name, deadline, false);
        } else {
            listOfItems.addDeadline(name, deadline, true);
        }
    }

    /**
     * Save the TaskManager object into the harddrive.
     * 
     * @param listOfItems The TaskManager object
     * @throws IOException If there is something wrong with saving the file
     */
    public static void saveFile(TaskManager listOfItems) throws IOException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < listOfItems.getSize(); i++) {
            try {
                fw.write(listOfItems.getId(i).toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }

}
