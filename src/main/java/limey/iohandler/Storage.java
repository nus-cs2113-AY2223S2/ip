package limey.iohandler;

import limey.command.Deadline;
import limey.command.Event;
import limey.command.Task;
import limey.command.Todo;
import limey.exception.invalidDateException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * Creates an empty text file in the given filePath
     *
     * @param filePath the path where the empty text file will be saved
     */
    public static void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(System.lineSeparator());
        fw.close();
    }
    /**
     * Converts the task list to a text file to be saved in the given filePath and converts it to be saved for future retrieval
     *
     * @param filePath the path where the text file will be saved
     * @param tasks task list to convert and save as text file
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Integer i = 0; i < Task.numTasks; i++) {
            Character markIcon = tasks.get(i).getTaskIdentity().charAt(4);
            Character taskType = tasks.get(i).getTaskIdentity().charAt(1);
            String taskNameWODates;
            switch(taskType) {
            case ('D'): //deadline
                taskNameWODates = tasks.get(i).getTaskName().substring(0,tasks.get(i).getTaskName().indexOf("(by:"));
                fw.write(taskType.toString() + markIcon.toString() + taskNameWODates);
                fw.write("/by " + tasks.get(i).getInDate());
                break;
            case ('E'): //Event
                taskNameWODates = tasks.get(i).getTaskName().substring(0,tasks.get(i).getTaskName().indexOf("(from"));
                fw.write(taskType.toString() + markIcon.toString() + taskNameWODates);
                fw.write("/from " + tasks.get(i).getInFromDate());
                fw.write("/to " + tasks.get(i).getInToDate());
                break;
            default: //todo no additional string required
                fw.write(taskType.toString() + markIcon.toString() + tasks.get(i).getTaskName());
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
    /**
     * Reads in text from the file in a given filePath and converts it to array list of Task objects
     * To retrieve and update the current task list to match the one saved from memory
     *
     * @param filePath the path from which the previously saved list is to be extracted from
     * @param tasks task list to be updated to match the one saved in the file
     */
    public static void readFileToTasks(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String listItem;
        while (s.hasNext()) {
            listItem = s.nextLine();
            Character taskType = listItem.charAt(0);
            String taskName = listItem.substring(2).trim();
            boolean isTaskDone = listItem.charAt(1) == 'X';
            Task inTask;
            switch (taskType) {
            case 'T':
                inTask = new Todo(taskName);
                inTask.setDone(isTaskDone);
                tasks.add(inTask);
                break;
            case 'D':
                try {
                    Deadline inDeadline = new Deadline(taskName);
                    tasks.add(inDeadline);
                    tasks.get(Task.numTasks).setDone(isTaskDone);
                } catch (invalidDateException e){
                    Ui.invalidMessage("Invalid Date");
                }
                break;
            case 'E':
                try {Event inEvent = new Event(taskName);
                    tasks.add(inEvent);
                    tasks.get(Task.numTasks).setDone(isTaskDone);
                } catch (invalidDateException e){
                    Ui.invalidMessage("Invalid Date");
                }
                break;
            default:
                //no default because reading from an auto generated file, no errors expected
                break;
            }
            Task.numTasks++;
        }
    }
    /***
     * Updates the tasks list to contain the relevant tasks that can be found in the saved
     * list from previous run of limey
     *
     * @param tasks the task list being updated and changed to the saved list
     */
    public static void retrieveSavedList(ArrayList<Task> tasks) {
        try {
            String filePath = "SavedList";
            Storage.readFileToTasks(filePath, tasks);
        } catch (FileNotFoundException e) {
            Ui.invalidMessage("File not found");
        }
    }
}