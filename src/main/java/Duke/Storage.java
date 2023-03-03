package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static final String storagePath = "src/Storage.txt";

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /***
     * Iterates through save file and initializes saved data into taskList and returns
     * @param taskList
     * @return int representing number of tasks initialized
     * @throws FileNotFoundException
     */
    static int initializeTaskList(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(storagePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int numberOfSaves = 0;
        while (s.hasNext()) {
            String saveString = s.nextLine();
            String[] saveData = saveString.split("t/|m/|n/|s/");
            switch (saveData[0]) {
            case "T":
                taskList.add(new Todo(saveData[2]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2).equals("X")) {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;

            case "D":
                taskList.add(new Deadline(saveData[2], saveData[3]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2).equals("X")) {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;

            case "E":
                taskList.add(new Event(saveData[2], saveData[3], saveData[4]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2).equals("X")) {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;
            }

        }
        return numberOfSaves;
    }

    /**
     * Write save data of taskList to text file.
     * @param taskList Array list of task
     * @param numberOfTasks number of tasks in taskList
     * @throws IOException when appendToFile gives errors
     */
    static void saveTaskList(ArrayList<Task> taskList, int numberOfTasks) throws IOException {
        writeToFile(storagePath, "");
        for (int i = 0; i < numberOfTasks; i += 1) {
            Task taskIndex = taskList.get(i);
            appendToFile(storagePath, taskIndex.saveInfo());
        }
    }
}
