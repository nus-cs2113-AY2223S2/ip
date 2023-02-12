package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

public class Storage {
    private static final Boolean LOAD_FROM_SAVE_DATA = true;
    private static final String MARK = "mark ";
    private static final String directoryLocation = "src/main/java/data";
    private static final String fileLocation = "src/main/java/data/listData.txt";

    public void loadData(ArrayList<Task> taskList) {
        File data = new File(directoryLocation);
        checkForDataDirectory(data);
        File listData = new File(fileLocation);
        checkForListData(listData);
        pushDataToTaskList(taskList, listData);
    }

    private void pushDataToTaskList(ArrayList<Task> taskList, File listData) {
        try {
            Scanner listDataScanner = new Scanner(listData);
            TaskAdder taskAdder = new TaskAdder();
            Marker marker = new Marker();
            while (listDataScanner.hasNext()) {
                String[] currentTaskInput = listDataScanner.nextLine().split(" ", 2);
                taskAdder.addTaskToList(taskList, currentTaskInput[1], LOAD_FROM_SAVE_DATA);
                try {
                    if (Integer.parseInt(currentTaskInput[0]) == 1) {
                        marker.handleMarkUnmarkAction(taskList, MARK + (taskList.size()), LOAD_FROM_SAVE_DATA);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This data's entry seems to be corrupted, not entering corrupted data");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("For some reason, listData is still unable to be found");
        }
    }

    private void checkForDataDirectory(File directory){
        if (!directory.isDirectory()){
            System.out.println("Data directory does not exist, creating a new one");
            if (directory.mkdir()){
                System.out.println("Successfully created a data directory");
            }
            else{
                System.out.println("Unable to create a data directory for some reason :<");
            }
        }
    }

    private void checkForListData(File listData){
        if (!listData.exists()){
            System.out.println("Data file does not exist, creating a new one");
            try{
                if (listData.createNewFile()){
                    System.out.println("Created a new listData file");
                }
            }
            catch(IOException e){
                System.out.println("We can't create a file for some reason :<, exiting program");
                System.exit(1);
            }
        }
    }

    public void saveListDate(ArrayList<Task> taskList){
        try{
            writeToFile();
        }
        catch (IOException e){
            System.out.println("For some reason I cant delete the content of the folder");
        }
        for (Task task : taskList) {
            String currentLine;
            if (task instanceof Todo) {
                currentLine = parseString((Todo) task) + System.lineSeparator();
            } else if (task instanceof Deadline) {
                currentLine = parseString((Deadline) task) + System.lineSeparator();
            } else if (task instanceof Event) {
                currentLine = parseString((Event) task) + System.lineSeparator();
            } else {
                System.out.println("For some reason the current file is not of a known subclass of Task, please check implementation");
                continue;
            }
            try {
                appendToFile(currentLine);
            } catch (IOException e) {
                System.out.println("Unable to append to file");
            }

        }
    }


    private static String parseString(Todo todo){
        String done = "0";
        if (todo.done){
            done = "1";
        }
        return done + " todo " + todo.description;
    }
    private static String parseString(Deadline deadline){
        String done = "0";
        if (deadline.done){
            done = "1";
        }
        return done + " deadline " + deadline.description + " /by " + deadline.by;
    }

    private static String parseString(Event event){
        String done = "0";
        if (event.done){
            done = "1";
        }
        return done + " event " + event.description + " /from " + event.startDate + " /to " + event.endDate;
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(Storage.fileLocation);
        fw.write("");
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Storage.fileLocation, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
