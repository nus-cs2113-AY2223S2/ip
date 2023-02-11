package duke;

import duke.exceptions.DeleteEmptyTasks;
import duke.exceptions.DeleteIndexOutOfBound;
import duke.exceptions.MissingFileException;
import duke.exceptions.MissingParameterException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tools.UI;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Task manager with private attribute task array to store tasks.
 * Public methods to read/write tasks
 */
public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final static UI ECHO_BACK = new UI();
    private final static String MARKED_CAPTION = "      Nice! I've marked this task as done:";
    private final static String NEW_TASK_CAPTION = "      Got it. I've added this task:";
    private final static String DELETE_TASK_CAPTION = "      Noted! I've removed this task:c";
    private final static String UNMARKED_CAPTION = "      OK, I've marked this task as not done yet:";
    private final static String DATA_PATH = "duke_data.text";
    private final static File DATA_FILE = new File(DATA_PATH);




    public static void loadTask(Task task){
        tasks.add(task);
    }

    /**
     * Create new todoTask.
     *
     * @param taskDescription
     * @return
     */
    public Todo createNewTodo(String taskDescription) throws IOException{
        Todo newTodo = new Todo(taskDescription);
        String data = "T|0|"+taskDescription+"\n";
        ECHO_BACK.writeToFile(data, DATA_PATH);
        return newTodo;
    }

    /**
     * Create new deadline task.
     * Get deadline from command.
     *
     * @param taskDescription
     * @return
     */
    public Deadline createNewDeadline(String taskDescription) throws MissingParameterException, IOException {
        int index = taskDescription.indexOf("/by");
        if(index==-1){
            throw new MissingParameterException();
        }
        String deadlineContent = taskDescription.substring(0,index-1);
        String deadlineDate = taskDescription.substring(index+4);
        Deadline newDeadline = new Deadline(deadlineContent, deadlineDate);
        String data = "D|0|"+deadlineContent+"|"+deadlineDate+"\n";
        ECHO_BACK.writeToFile(data, DATA_PATH);
        return newDeadline;
    }

    /**
     * Create new event.
     * Get duration from input command.
     *
     * @param taskDescription
     * @return
     */
    public Event createNewEvent(String taskDescription) throws MissingParameterException, IOException {
        int indexStart = taskDescription.indexOf("/from");
        int indexEnd = taskDescription.indexOf("/to");
        if(indexStart==-1||indexEnd==-1){
            throw new MissingParameterException();
        }
        String eventContent = taskDescription.substring(0,indexStart-1);
        String eventStartTime = taskDescription.substring(indexStart+6, indexEnd-1);
        String eventEndTime = taskDescription.substring(indexEnd+4);
        Event newEvent = new Event(eventContent, eventStartTime, eventEndTime);
        String data = "E|0|"+eventContent+"|"+eventStartTime+"|"+eventEndTime+"\n";
        ECHO_BACK.writeToFile(data, DATA_PATH);
        return newEvent;
    }

    /**
     * Create new task obejcts based on the type of the task.
     *
     * @param taskType
     * @param taskDescription
     * @return
     */
    public Task generateNewTask(String taskType, String taskDescription) throws MissingParameterException, IOException{
        Task newTask;

        if(taskType.equals("todo")){
            newTask = createNewTodo(taskDescription);
        }else if(taskType.equals("deadline")){
            newTask = createNewDeadline(taskDescription);
        }else {
            newTask = createNewEvent(taskDescription);
        }
        return newTask;

    }

    /**
     * Add a new task into tasks array.
     *
     * @param taskType .
     * @param taskDescription
     */
    public void addTask(String taskType, String taskDescription) throws MissingParameterException, IOException{
        Task newTask = generateNewTask(taskType, taskDescription);
        tasks.add(newTask);
        ECHO_BACK.echoTask(tasks.size(),newTask, NEW_TASK_CAPTION);

    }

    /**
     * Mark tasks as done/not done.
     *
     * @param taskIndex index of the task to be edited.
     * @param status mark/unmark.
     */
    public void editTaskStatus(String taskIndex, String status) throws MissingFileException, IOException {
        if(!DATA_FILE.exists()){
            throw new MissingFileException();
        }
        int index = Integer.parseInt(taskIndex)-1;
        if(status.equals("mark")) {
            tasks.get(index).markDone();
            ECHO_BACK.updateData("mark",index);
        }else{
            tasks.get(index).undo();
            ECHO_BACK.updateData("unmark",index);
        }
        String caption;
        if(status.equals("mark")){
            caption = MARKED_CAPTION;
        }else{
            caption = UNMARKED_CAPTION;
        }
        ECHO_BACK.updateTaskStatus(tasks.get(index), caption);
    }

    /**
     * List all tasks.
     */
    public void listTask(){

        ECHO_BACK.listCurrentTasks(tasks, tasks.size());

    }

    public void deleteTask(String description) throws DeleteIndexOutOfBound, DeleteEmptyTasks {
        if(tasks.size()==0){
            throw new DeleteEmptyTasks();
        }
        //The command description is base 1
        int index = Integer.parseInt(description)-1;
        if(index<0 || index>=tasks.size()){
            throw new DeleteIndexOutOfBound();
        }
        ECHO_BACK.echoTask(tasks.size(), tasks.get(index), DELETE_TASK_CAPTION);
        tasks.remove(index);
    }
}
