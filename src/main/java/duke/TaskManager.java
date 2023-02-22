package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tools.Formatter;

import duke.tools.Storage;
import duke.tools.UI;
import java.util.ArrayList;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Store all the input tasks.
 * Perform actions on tasks such as add/delete/find tasks or update task status.
 */
public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static UI print = new UI();
    private final static String MARKED_CAPTION = "      Nice! I've marked this task as done:";
    private final static String NEW_TASK_CAPTION = "      Got it. I've added this task:";
    private final static String DELETE_TASK_CAPTION = "      Noted! I've removed this task:c";
    private final static String UNMARKED_CAPTION = "      OK, I've marked this task as not done yet:";
    private final static String FOUND_CAPTION = "    Here are the matching tasks in your list:";


    public static void loadTask(Task task){
        tasks.add(task);
    }

    public static ArrayList<Task> getALLTasks(){
        return tasks;
    }

    /**
     * Create new todoTask.
     *
     * @param taskDescription
     * @return
     */
    public static Todo createNewTodo(String taskDescription) {
        Todo newTodo = new Todo(taskDescription);
        return newTodo;
    }

    /**
     * Create new deadline task.
     * Get deadline from command.
     *
     * @param taskDescription
     * @return
     */
    public static Deadline createNewDeadline(String taskDescription) throws MissingParameterException {
        int index = taskDescription.indexOf("/by");
        if(index==-1){
            throw new MissingParameterException();
        }
        String deadlineContent = taskDescription.substring(0,index-1);
        String deadlineDate = taskDescription.substring(index+4);
        Deadline newDeadline = new Deadline(deadlineContent, deadlineDate);
        return newDeadline;
    }

    /**
     * Create new event.
     * Get duration from input command.
     *
     * @param taskDescription
     * @return
     */
    public static Event createNewEvent(String taskDescription) throws MissingParameterException {
        int indexStart = taskDescription.indexOf("/from");
        int indexEnd = taskDescription.indexOf("/to");
        if(indexStart==-1||indexEnd==-1){
            throw new MissingParameterException();
        }
        String eventContent = taskDescription.substring(0,indexStart-1);
        String eventStartTime = taskDescription.substring(indexStart+6, indexEnd-1);
        String eventEndTime = taskDescription.substring(indexEnd+4);
        Event newEvent = new Event(eventContent, eventStartTime, eventEndTime);
        return newEvent;
    }

    /**
     * Create new task objects based on the type of the task requested.
     *
     * @param taskType
     * @param taskDescription
     * @return
     */
    public static Task generateNewTask(String taskType, String taskDescription) throws MissingParameterException, IOException{
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
     * Store this task in file and print output messages.
     *
     * @param taskType .
     * @param taskDescription
     */
    public static void addTask(String taskType, String taskDescription) throws MissingParameterException, IOException{
        Task newTask = generateNewTask(taskType, taskDescription);
        tasks.add(newTask);
        Storage.copyToFile();
        print.echoTask(tasks.size(),newTask, NEW_TASK_CAPTION);
    }

    /**
     * Mark a particular task as done/not done based on input command.
     * Update data file accordingly and print output messages.
     *
     * @param taskIndex index of the task to be edited.
     * @param status mark/unmark.
     */
    public static void editTaskStatus(String taskIndex, String status) throws EditEmptyTasks, IOException {
        if(tasks.size()==0){
            throw new EditEmptyTasks();
        }
        Storage.copyToFile();
        int index = Integer.parseInt(taskIndex)-1;
        if(status.equals("mark")) {
            tasks.get(index).markDone();
            Storage.updateData("mark",index);
        }else{
            tasks.get(index).undo();
            Storage.updateData("unmark",index);
        }
        String caption;
        if(status.equals("mark")){
            caption = MARKED_CAPTION;
        }else{
            caption = UNMARKED_CAPTION;
        }
        print.updateTaskStatus(tasks.get(index), caption);
    }

    public static void listTask(){

        print.listCurrentTasks(tasks, tasks.size());

    }

    /**
     * Delete a particular task based on user command.
     * Update data file and print output messages.
     *
     * @param description
     * @throws DeleteIndexOutOfBound
     * @throws DeleteEmptyTasks
     * @throws IOException
     */
    public static void deleteTask(String description) throws DeleteIndexOutOfBound, DeleteEmptyTasks, IOException {
        if(tasks.size()==0){
            throw new DeleteEmptyTasks();
        }
        Storage.copyToFile();
        //The command description is base 1
        int index = Integer.parseInt(description)-1;
        if(index<0 || index>=tasks.size()){
            throw new DeleteIndexOutOfBound();
        }
        print.echoTask(tasks.size()-1, tasks.get(index), DELETE_TASK_CAPTION);
        tasks.remove(index);
        Storage.copyToFile();

    }

    /**
     * Check each task description whether they contain the keyword string.
     * Print the matched tasks.
     *
     * @param keyword
     */
    public static void findTasksByKeyword(String keyword){
        int count = 1;
        Formatter.drawSeparationLine();
        System.out.println(FOUND_CAPTION);
        tasks.stream().forEach(x->{
            if (x.getTaskDescription().contains(keyword)){
                print.foundTasks(x, count);
            }
        });
        Formatter.drawSeparationLine();
    }

    /**
     * Check each deadline date whether they match the input date.
     * Print the matched deadlines.
     *
     * @param keyword
     */
    public static void findDeadlinesByDate(String keyword){
        LocalDate date = LocalDate.parse(keyword);
        int count = 1;
        Formatter.drawSeparationLine();
        System.out.println(FOUND_CAPTION);
        tasks.stream().forEach(x->{
            if (x instanceof Deadline && ((Deadline)x).getBy().equals(date)){
                print.foundTasks(x, count);
            }
        });
        Formatter.drawSeparationLine();
    }
}
