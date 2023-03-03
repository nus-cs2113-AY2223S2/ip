package Duke;

import Duke.Exception.EmptyDeadlineException;
import Duke.Exception.EmptyEventsException;
import Duke.Exception.EmptyToDoException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final int BRACKETSKIP_INDEX = 7;
    private static final int FIRSTBRACKET_FRONT = 0;
    private static final int FIRSTBRACKET_BACK = 3;
    private static final int SECONDBRACKET_FRONT = 3;
    private static final int SECONDBRACKET_BACK = 6;


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Write the data to the default file location
     *
     * @param taskToBeStored info to be written to the file.
     * @throws IOException If default file location is invalid.
     */
    public static void dukeDataStorage(String taskToBeStored) {
        File f = new File("dukeData.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            writeToFile("dukeData.txt", taskToBeStored);
        } catch (IOException e) {
            System.out.println("File creation of writing invalid");
        }
    }

    /**
     * Parse data stored in file into String object.
     *
     * @param filePath file path of data file to be read.
     * @return String object representing all the data in the data file.
     * @throws FileNotFoundException If file path is invalid.
     */
    public static String parseFileContentsToString(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        String fileContent = "";
        if (!f.exists()) { // for first log in, there is no file
            return fileContent;
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String taskStored = s.nextLine();
            fileContent = fileContent + taskStored + System.lineSeparator();
        }
        return fileContent;
    }

    /**
     * Parse data stored in an arraylist to a single String object to be stored in data file. Used
     * in writing to data file.
     *
     * @param list arraylist to be parsed.
     * @return String object representing all the data in the data file.
     */
    public static String arraylistToStringConverter(ArrayList<Task> list) {
        String output = "";
        for (Task t : list) {
            output = output + t.toString() + System.lineSeparator();
        }
        return output;
    }

    /**
     * Parse data stored as a single String object into an arrayList. Used in reading from data file.
     *
     * @param fileContent a String object representing data in the file.
     * @return arraylist which stores all the tasks stored in file as elements.
     * @throws EmptyDeadlineException If task read is empty.
     * @throws EmptyToDoException If task read is empty.
     * @throws EmptyEventsException If task read is empty.
     */
    public static ArrayList<Task> stringToArraylistConverter(String fileContent) {
        ArrayList<Task> list = new ArrayList<>();
        if (fileContent.equals("")) {
            return list;
        }
        String[] bufferArray = fileContent.split(System.lineSeparator());
        for (String t : bufferArray) {
            Task task = new Task(t);
            try {
                if (t.substring(FIRSTBRACKET_FRONT, FIRSTBRACKET_BACK).equals("[T]")) {
                    String todoDetail = "todo " + t.substring(BRACKETSKIP_INDEX);
                    task = new ToDos(todoDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                } else if (t.substring(FIRSTBRACKET_FRONT, FIRSTBRACKET_BACK).equals("[D]")) {
                    String deadlineDetail = "deadline " + t.substring(BRACKETSKIP_INDEX, t.indexOf("(")) + "/" +
                            t.substring(t.indexOf("(") + 1, t.indexOf(")"));
                    task = new Deadlines(deadlineDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                } else {
                    String eventDetail = "event " + t.substring(BRACKETSKIP_INDEX, t.indexOf("(")) + "/" +
                            t.substring(t.indexOf("(") + 1, t.indexOf("to")) + "/" +
                            t.substring(t.indexOf("to"), t.indexOf(")"));
                    eventDetail = eventDetail.replace(":", "");
                    task = new Events(eventDetail);
                    task.setMark(t.substring(SECONDBRACKET_FRONT, SECONDBRACKET_BACK));
                }
            } catch (EmptyToDoException e) {
                System.out.println("Corrupt todo detected, it is empty");
            } catch (EmptyDeadlineException e) {
                System.out.println("Corrupt Deadline detected, it is empty");
            } catch (EmptyEventsException e) {
                System.out.println("Corrupt Events detected, it is empty");
            }
            list.add(task);
        }
        return list;
    }
}

