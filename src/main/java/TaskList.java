public class TaskList {
    private Task[] taskArray = new Task[100];
    private int totalTaskNum = 0;

    public void addTask(String userInput){
        Task newTask = new Task(userInput);
        taskArray[totalTaskNum++] = newTask;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i=1; i<totalTaskNum+1; i++){
            str = str.concat(i + "." + taskArray[i-1] + "\n");
        }
        return str;
    }

    public Task[] getTaskArray(){
        return taskArray;
    }

    public int getTotalTaskNum(){
        return totalTaskNum;
    }
}

class Task{
    private String contents;
    private Boolean isDone = false;

    Task(String userInput){
        contents = userInput;
    }


    @Override
    public String toString(){
        if(isDone) return "[O] " + contents;
        return "[ ] " + contents;
    }

    public String getContents(){
        return contents;
    }

    public Boolean getIsDone(){
        return isDone;
    }

    public void mark(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }
}
