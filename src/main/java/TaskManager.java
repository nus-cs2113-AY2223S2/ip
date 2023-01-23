/**
 * Task manager with private attribute string array to store tasks
 * Methods to add/print tasks
 */
public class TaskManager {
    private String[] tasks = new String[100];
    private int count;

    public TaskManager() {
        this.count = 0;
    }

    public void addTask(String task){
        this.tasks[count]=task;
        this.count+=1;
    }

    public void printTask(){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();

        formatter.drawSeparationLine(45);
        String[] indexedTasks = tool.addIndex(this.tasks, count);
        formatter.addIndentation(indexedTasks);
        tool.printStringArray(indexedTasks);
        formatter.drawSeparationLine(45);
    }
}
