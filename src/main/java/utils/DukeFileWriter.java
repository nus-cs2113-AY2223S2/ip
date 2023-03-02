package utils;

import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
* File format:
* T | 1 | read book
* D | 0 | return book | June 6th
* E | 0 | project meeting | Aug 6th | 2-4pm
* T | 1 | join sports club
* A | 0 | eat eat eat
* */

public class DukeFileWriter {
    protected static String filePath;

    public DukeFileWriter(String filePath){
        this.filePath = filePath;
    }


    private static void addLineToFile(String line) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(line);
        fw.close();
    }

    /* called when adding a todo / deadline / event object. Input: new task object */
    public static void addNewObjectToFile(Task newObject) throws IOException {
        String line = getObjectLineString(newObject);
        addLineToFile(line);
    }


    /* Receive a Todo / Deadline / Event Object and turn it into a new LineString in the file. */
    private static String getObjectLineString(Task newObject){
        String line = null;
        String lineType = newObject.getClass().getTypeName();
        switch (lineType) {
            case("task.Task"):
            case("task.Todo"):{
                line = newObject.getLetter()+ "|" + newObject.getIntStatus() +
                        "|" + newObject.getDescription() + "\n";
                break;
            } case("task.Deadline"):{
                Deadline newDeadline = (Deadline) newObject;
                line = newDeadline.getLetter()+ "|" + newDeadline.getIntStatus() +
                        "|" + newDeadline.getDescription() + "|" + newDeadline.getBy() + "\n";
                break;
            } case("task.Event"): {
                Event newEvent = (Event) newObject;
                line = newEvent.getLetter() + "|" + newEvent.getIntStatus() +
                        "|" + newEvent.getDescription() + "|" + newEvent.getFrom() +
                        "|" + newEvent.getTo() + "\n";
                break;
            }
        }
        return line;
    }

    /* rewrite the whole tasksList in the memory to the disk */
    public void rewriteAllToFile(TaskList tasks) throws IOException {
        ArrayList<Task> tasksList = tasks.getTasks();
        FileWriter fw = new FileWriter(filePath, false);fw.close();
        for(Task task : tasksList){
            addNewObjectToFile(task);
        }
    }

}
