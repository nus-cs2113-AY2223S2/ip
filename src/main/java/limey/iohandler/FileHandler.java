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

public class FileHandler {
    public static void writeToFile(String filePath, Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Integer i = 0; i < Task.numTasks; i++) {
            Character markIcon = tasks[i].getTaskIdentity().charAt(4);
            Character taskType = tasks[i].getTaskIdentity().charAt(1);
            String taskNameWODates;
            switch(taskType) {
            case ('D'): //deadline
                taskNameWODates = tasks[i].getTaskName().substring(0,tasks[i].getTaskName().indexOf("(by:"));
                fw.write(taskType.toString() + markIcon.toString() + taskNameWODates);
                fw.write("/by " + tasks[i].getDueDate());
                break;
            case ('E'): //Event
                taskNameWODates = tasks[i].getTaskName().substring(0,tasks[i].getTaskName().indexOf("(from"));
                fw.write(taskType.toString() + markIcon.toString() + taskNameWODates);
                fw.write("/from " + tasks[i].getFromDate());
                fw.write("/to " + tasks[i].getToDate());
                break;
            default: //todo no additional string required
                fw.write(taskType.toString() + markIcon.toString() + tasks[i].getTaskName());
                break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void readFileToTasks(String filePath, Task[] tasks) throws FileNotFoundException {
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
                tasks[Task.numTasks] = inTask;
                break;
            case 'D':
                try {
                    //taskName = taskName.substring(0,taskName.indexOf("(by:")) + taskName.substring(taskName.lastIndexOf(")") + 1);
                    Deadline inDeadline = new Deadline(taskName);
                    tasks[Task.numTasks] = inDeadline;
                    tasks[Task.numTasks].setDone(isTaskDone);
                } catch (invalidDateException e){
                    Speech.invalidMessage("Invalid Date");
                }
                break;
            case 'E':
                try {
                    //taskName = taskName.substring(0,taskName.indexOf("(from:")) + taskName.substring(taskName.lastIndexOf(")") + 1);
                    Event inEvent = new Event(taskName);
                    tasks[Task.numTasks] = inEvent;
                    tasks[Task.numTasks].setDone(isTaskDone);
                } catch (invalidDateException e){
                    Speech.invalidMessage("Invalid Date");
                }
                break;
            default:
                //no default because reading from an auto generated file, no errors expected
                break;
            }
            Task.numTasks++;
        }
    }
}