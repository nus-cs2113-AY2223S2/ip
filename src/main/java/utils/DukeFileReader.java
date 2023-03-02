package utils;

import exceptions.FileLineParseException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A tool for reading local file into the task list.
 */
public class DukeFileReader {
    private String filePath;
    public DukeFileReader(String filePath){
        this.filePath = filePath;
    }

    /**
     * Read the local file into the task list.
     * @return An array list containing all the task.
     * @throws FileNotFoundException Did not find the local file.
     * @throws FileLineParseException Confront errors when parsing the file.
     */
    public ArrayList<Task> readFileToTaskList() throws FileNotFoundException, FileLineParseException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        ArrayList<Task>tasksList = new ArrayList<>();
        while(s.hasNext()){
            Task taskAdd = parseLineToTask(s.nextLine());
            tasksList.add(taskAdd);
        }
        return tasksList;
    }


    /**
     * Function for parsing a line in the local file to a task object.
     * You can find the format of the lines in the local file in DukeFileWriter.java
     * @param fileLine A line in the local file.
     * @return A task object converted from the line.
     * @throws FileLineParseException
     */
    private Task parseLineToTask(String fileLine) throws FileLineParseException {
        //[have not done] : magic numbers
        String[] words = fileLine.split("\\|");

        if(words.length < 3 || words.length > 5){
            throw new FileLineParseException();
        }

        String lineType = words[0].trim();
        String description = words[2].trim();
        boolean isDone = (Integer.parseInt(words[1].trim()) == 1);

        switch (lineType){
            case("A"):{
                return new Task(description,isDone);
            } case("T"):{
                return new Todo(description,isDone);
            } case("D"):{
                if(words.length < 4){
                    throw new FileLineParseException();
                }
                String by = words[3].trim();
                return new Deadline(description,isDone,by);
            } case("E"):{
                if(words.length < 5){
                    throw new FileLineParseException();
                }
                String from = words[3].trim();
                String to = words[4].trim();
                return new Event(description,isDone,from,to);
            } default:{
                throw new FileLineParseException();
            }
        }
    }




}
