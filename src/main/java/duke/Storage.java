package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

public class Storage {
    public static void loadData(ArrayList<Task> tasks, String filepath) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            // read tasksDataInStringFormat from file
            while (s.hasNext()) {
                // get each line
                String taskInStringFormat = s.nextLine();

                // get task type
                String taskType = taskInStringFormat.substring(0,6);


                // useful variables for later
                String taskName;
                String taskInformation;
                String deadlineBy;
                String eventStart;
                String eventEnd;

                // copy back to tasks (the array for now), based on what the line is
                switch (taskType) {
                case "[T][ ]":
                    // example for reference: return book
                    taskName = taskInStringFormat.substring(7);
                    tasks.add(new ToDo(taskName));
                    break;
                case "[T][X]":
                    taskName = taskInStringFormat.substring(7);
                    tasks.add(new ToDo(taskName));
                    tasks.get(tasks.size()-1).setDone(true);
                    break;
                case "[D][ ]":
                    // example for reference: return book (by: June 6th)
                    // taskInformation = taskInStringFormat.split(" ", 2)[1];
                    taskInformation = taskInStringFormat.substring(7);
                    taskName = taskInformation.split(" \\(", 2)[0];
                    deadlineBy = taskInformation.split(": ", 2)[1].replace(")","");
                    tasks.add(new Deadline(taskName, deadlineBy));
                    break;
                case "[D][X]":
                    taskInformation = taskInStringFormat.substring(7);
                    taskName = taskInformation.split(" \\(", 2)[0];
                    deadlineBy = taskInformation.split(": ", 2)[1].replace(")","");
                    tasks.add(new Deadline(taskName, deadlineBy));
                    tasks.get(tasks.size()-1).setDone(true);
                    break;
                case "[E][ ]":
                    // for reference: project meeting (from: Aug 6th 2pm to: 4pm)
                    taskInformation = taskInStringFormat.substring(7);
                    taskName = taskInformation.split(" \\(", 2)[0];
                    taskInformation = taskInformation.split("from: ",2)[1];
                    eventStart = taskInformation.split(" to:",2)[0];
                    eventEnd = taskInformation.split("to: ",2)[1].replace(")", "");
                    tasks.add(new Event(taskName, eventStart, eventEnd));
                    break;
                case "[E][X]":
                    taskInformation = taskInStringFormat.substring(7);
                    taskName = taskInformation.split(" \\(", 2)[0];
                    taskInformation = taskInformation.split("from: ",2)[1];
                    eventStart = taskInformation.split(" to:",2)[0];
                    eventEnd = taskInformation.split("to: ",2)[1].replace(")", "");
                    tasks.add(new Event(taskName, eventStart, eventEnd));
                    tasks.get(tasks.size()-1).setDone(true);
                    break;
                default:
                    System.out.println("OOPS! Something went wrong while converting task data!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    public static void saveData(String filepath, ArrayList<Task> tasks) {
        try {
            String tasksDataInStringFormat = tasksDataToString(tasks);
            Storage.writeToFile(filepath, tasksDataInStringFormat);
        } catch (IOException e) {
            System.out.println("OOPS! Something went wrong with saving your data!");
        } catch (DukeException e) {
            System.out.println("OOPS! Something went wrong when converting your data for saving!");
        }
    }

    public static String tasksDataToString (ArrayList<Task> tasks) throws DukeException {
        StringJoiner joiner = new StringJoiner("\n");
        try {
            for (int i = 0; i < tasks.size(); i++) {
                String taskInformation = tasks.get(i).toString();
                joiner.add(taskInformation);
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        return joiner.toString();
    }

    // using overwrite type rather than append type to deal with removal/marking/unmarking of tasks for now
    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }
}
