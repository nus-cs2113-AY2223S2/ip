import java.util.Arrays;
/**
 * Task manager with private attribute task array to store tasks.
 * Public methods to read/write tasks
 */
public class TaskManager {
    private Task[] tasks = new Task[100];
    private int count;

    public TaskManager() {

        this.count = 0;
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
    public Deadline createNewDeadline(String taskDescription){
        int index = taskDescription.indexOf("/by");
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
    public Event createNewEvent(String taskDescription){
        int indexStart = taskDescription.indexOf("/from");
        int indexEnd = taskDescription.indexOf("/to");
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
    public Task generateNewTask(String taskType, String taskDescription){
        Task newTask;
        if(taskType.equals("todo")){
            newTask = createNewTodo(taskDescription);
        }else if(taskType.equals("deadline")){
            newTask = createNewDeadline(taskDescription);
        }else{
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
    public void addTask(String taskType, String taskDescription){
        Task newTask = generateNewTask(taskType, taskDescription);
        this.tasks[count]=newTask;
        this.count+=1;
        UI echoAddedTask = new UI();
        echoAddedTask.echoNewTask(count,newTask);

    }

    /**
     * Mark tasks as done/not done.
     *
     * @param taskIndex index of the task to be edited.
     * @param status mark/unmark.
     */
    public void editTaskStatus(String taskIndex, String status){
        UI printEditedTasks = new UI();
        int index = Integer.parseInt(taskIndex)-1;
        if(status.equals("mark")){
            tasks[index].markDone();
        }else{
            tasks[index].undo();
        }
        String caption;
        if(status.equals("mark")){
            caption = "      Nice! I've marked this task as done:";
        }else{
            caption = "      OK, I've marked this task as not done yet:";
        }
        printEditedTasks.updateTaskStatus(tasks[index], caption);
    }

    /**
     * List all tasks.
     */
    public void listTask(){

        UI printTasks = new UI();
        printTasks.listCurrentTasks(this.tasks, this.count);

    }
}
