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
     * Add a new task into tasks array.
     *
     * @param task to be added.
     */
    public void addTask(Task task){
        Echo echo = new Echo();
        this.tasks[count]=task;
        this.count+=1;
        echo.echoInput(task.taskDescription);
    }

    /**
     * Mark tasks as done/not done.
     *
     * @param inputWords splited input command.
     * @param status mark/unmark.
     */
    public void editTaskStatus(String[] inputWords, String status){
        Tool tool = new Tool();
        Formatter formatter = new Formatter();
        formatter.drawSeparationLine();
        String[] editedArray = new String[inputWords.length-1];
        for(int i=1; i<inputWords.length; i+=1){
            int index = Integer.parseInt(inputWords[i])-1;
            if(status.equals("mark")){
                tasks[index].markDone();
            }else{
                tasks[index].undo();
            }
            editedArray[i-1]="  "+ '[' + tasks[index].getTaskStatus()+"] "
                    +tasks[index].taskDescription;
        }
        if(status.equals("mark")){
            System.out.println("      Nice! I've marked this task as done:");
        }else{
            System.out.println("      OK, I've marked this task as not done yet:");
        }
        formatter.addStringIndentation(editedArray);
        tool.printStringArray(editedArray);
        formatter.drawSeparationLine();
    }

    /**
     * List all tasks.
     */
    public void listTask(){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        String[] indexedTasks = tool.addIndex(this.tasks, count);
        formatter.addStringIndentation(indexedTasks);

        formatter.drawSeparationLine();
        System.out.println("      Here are the tasks in your list:");
        tool.printStringArray(indexedTasks);
        formatter.drawSeparationLine();
    }
}
