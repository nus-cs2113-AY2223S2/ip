import java.util.Arrays;
/**
 * Task manager with private attribute task array to store tasks.
 * Public methods to read/write tasks
 */
public class TaskManager {
    private static Task[] tasks = new Task[100];
    private static int numTasks;
    private final static UI ECHO_BACK = new UI();
    private final static String MARKED_CAPTION = "      Nice! I've marked this task as done:";
    private final static String UNMARKED_CAPTION = "      OK, I've marked this task as not done yet:";


    public TaskManager() {

        this.numTasks = 0;
    }

    /**
     * Create new todoTask.
     *
     * @param taskDescription
     * @return
     */
    public Todo createNewTodo(String taskDescription){
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
    public Deadline createNewDeadline(String taskDescription) throws MissingParameterException{
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
    public Event createNewEvent(String taskDescription) throws MissingParameterException{
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
     * Create new task obejcts based on the type of the task.
     *
     * @param taskType
     * @param taskDescription
     * @return
     */
    public Task generateNewTask(String taskType, String taskDescription) throws MissingParameterException{
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
    public void addTask(String taskType, String taskDescription) throws MissingParameterException{
        Task newTask = generateNewTask(taskType, taskDescription);
        tasks[numTasks]=newTask;
        numTasks+=1;
        ECHO_BACK.echoNewTask(numTasks,newTask);

    }

    /**
     * Mark tasks as done/not done.
     *
     * @param taskIndex index of the task to be edited.
     * @param status mark/unmark.
     */
    public void editTaskStatus(String taskIndex, String status){
        int index = Integer.parseInt(taskIndex)-1;
        if(status.equals("mark")){
            tasks[index].markDone();
        }else{
            tasks[index].undo();
        }
        String caption;
        if(status.equals("mark")){
            caption = MARKED_CAPTION;
        }else{
            caption = UNMARKED_CAPTION;
        }
        ECHO_BACK.updateTaskStatus(tasks[index], caption);
    }

    /**
     * List all tasks.
     */
    public void listTask(){

        ECHO_BACK.listCurrentTasks(tasks, numTasks);

    }
}
