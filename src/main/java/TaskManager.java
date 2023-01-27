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

    public Todo generateNewTodo(String taskDescription){
        Todo newTodo = new Todo(taskDescription);
        return newTodo;
    }

    public Deadline generateNewDeadline(String taskDescription){
        int index = taskDescription.indexOf("/by");
        String deadlineContent = taskDescription.substring(0,index-1);
        String deadlineDate = taskDescription.substring(index+4);
        Deadline newDeadline = new Deadline(deadlineContent, deadlineDate);
        return newDeadline;
    }

    public Event generateNewEvent(String taskDescription){
        int indexStart = taskDescription.indexOf("/from");
        int indexEnd = taskDescription.indexOf("/to");
        String eventContent = taskDescription.substring(0,indexStart-1);
        String eventStartTime = taskDescription.substring(indexStart+6, indexEnd-1);
        String eventEndTime = taskDescription.substring(indexEnd-1);
        Event newEvent = new Event(eventContent, eventStartTime, eventEndTime);
        return newEvent;
    }

    public Task generateNewTask(String taskType, String taskDescription){
        Task newTask;
        if(taskType.equals("todo")){
            newTask = generateNewTodo(taskDescription);
        }else if(taskType.equals("deadline")){
            newTask = generateNewDeadline(taskDescription);
        }else{
            newTask = generateNewEvent(taskDescription);
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
        Parser parseEchoContent = new Parser();
        String[] echoContent = parseEchoContent.parseStringToArrayOneElement("      Added: "+newTask.taskDescription);
        String[] captions = {};
        UI echoAddedTask = new UI();
        echoAddedTask.printOutput(captions,echoContent);

    }

    /**
     * Mark tasks as done/not done.
     *
     * @param inputTasks splited input command.
     * @param status mark/unmark.
     */
    public void editTaskStatus(String inputTasks, String status){
        UI printEditedTasks = new UI();
        Parser parseEditedTask = new Parser();

        int index = Integer.parseInt(inputTasks)-1;
        if(status.equals("mark")){
            tasks[index].markDone();
        }else{
            tasks[index].undo();
        }

        String captions;
        String editedTaskContent="      "+ '[' + tasks[index].getTaskStatus()+"] "
                    +tasks[index].taskDescription;

        if(status.equals("mark")){
            captions = "      Nice! I've marked this task as done:";
        }else{
            captions = "      OK, I've marked this task as not done yet:";
        }

        String[] captionsArray = parseEditedTask.parseStringToArrayOneElement(captions);
        String[] editedTaskContentArray = parseEditedTask.parseStringToArrayOneElement(editedTaskContent);
        printEditedTasks.printOutput(captionsArray, editedTaskContentArray);
    }

    /**
     * List all tasks.
     */
    public void listTask(){
        Formatter formatter = new Formatter();
        UI printTasks = new UI();
        Parser parseListCaption = new Parser();
        String[] formattedTasks = formatter.formatTasks(this.tasks, this.count);
        String[] captions = parseListCaption.parseStringToArrayOneElement("      Here are the tasks in your list:");
        printTasks.printOutput(captions,formattedTasks);
    }
}
