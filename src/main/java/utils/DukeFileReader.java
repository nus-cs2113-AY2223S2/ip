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




public class DukeFileReader {
    /**
     * The LINE_LENGTH of each objects depends on the Format of the local file:
     * Todo task:     T | isDone | [description]
     * Deadline task: D | isDone | [description] | [Deadline]
     * Event task:    E | isDone | [description] | [from] | [to]
     */
    static final int TODO_LINE_LENGTH = 3;
    static final int DEADLINE_LINE_LENGTH = 4;
    static final int EVENT_LINE_LENGTH = 5;
    static final int BY_INDEX = 3;
    static final int FROM_INDEX = 3;
    static final int TO_INDEX = 4;
    static final int LINE_TYPE_INDEX = 0;
    static final int DESCRIPTION_INDEX = 2;
    static final int ISDONE_INDEX = 1;

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

        if(words.length < TODO_LINE_LENGTH || words.length > EVENT_LINE_LENGTH){
            throw new FileLineParseException();
        }

        String lineType = words[LINE_TYPE_INDEX].trim();
        String description = words[DESCRIPTION_INDEX].trim();
        boolean isDone = (Integer.parseInt(words[ISDONE_INDEX].trim()) == ISDONE_INDEX);

        switch (lineType){
            case("A"):{
                return new Task(description,isDone);
            } case("T"):{
                return new Todo(description,isDone);
            } case("D"):{
                if(words.length < DEADLINE_LINE_LENGTH){
                    throw new FileLineParseException();
                }
                String by = words[BY_INDEX].trim();
                return new Deadline(description,isDone,by);
            } case("E"):{
                if(words.length < EVENT_LINE_LENGTH){
                    throw new FileLineParseException();
                }
                String from = words[FROM_INDEX].trim();
                String to = words[TO_INDEX].trim();
                return new Event(description,isDone,from,to);
            } default:{
                throw new FileLineParseException();
            }
        }
    }




}
