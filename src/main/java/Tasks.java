public class Tasks {
    protected String TaskName;
    protected boolean isDone;
    protected static int NumberOfTasks;

    public Tasks(String TaskName){
        this.TaskName = TaskName;
        this.isDone = false;
        NumberOfTasks++;
    }

    public void MarkTask(){
        this.isDone = true;
    }

    public void unMarkTask(){
        this.isDone = false;
    }

    public String TaskStatus(){
        return (isDone ? "x" : " ");
    }

    public static void ListTasks(Tasks[] TASK){
        System.out.println("---------------------------------------------------");
        System.out.println("These are the tasks in your list:");
        for(int i=0; i<NumberOfTasks; i++){
            System.out.println(i+1 + ". " + "[" + TASK[i].TaskStatus() + "] " + TASK[i].TaskName);
        }
        System.out.println("---------------------------------------------------");
    }
}
