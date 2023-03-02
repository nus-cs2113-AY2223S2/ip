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
    String filePath;
    public DukeFileReader(String filePath){
        this.filePath = filePath;
    }
    public ArrayList<Task> readFileToTaskList() throws FileNotFoundException, FileLineParseException {
        File f = new File(filePath);
        //System.out.println("Reading file to taskList...");
        Scanner s = new Scanner(f);

        ArrayList<Task>tasksList = new ArrayList<>();
        while(s.hasNext()){
            Task taskAdd = parseLineToTask(s.nextLine());
            //System.out.println("New task = "+ taskAdd.toString());
            tasksList.add(taskAdd);
        }
        return tasksList;
    }

    /*
    * Format:
    * A | isDone | [description]
    * T | isDone | [description]
    * D | isDone | [description] | [Deadline]
    * E | isDone | [description] | [from] | [to]
    * */

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
