package duke.task;

public class Task {
    protected String taskName;
    protected static int indexCount = 0;
    protected boolean isDone;
    protected String type;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        indexCount++;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static int getIndexCount(){
       return indexCount;
    }

    @Override
    public String toString(){
        return "";
    }

    public String checkBoxOutput(){
        String checkMark = isDone ? " [X] " : " [ ] ";
        String taskType = type;

        return  taskType + checkMark;
    }

    public void printAdded(){
        int taskCount = indexCount;
        String word1;
        String word2;
        if (taskCount == 1){
            word1 = "is ";
            word2 = " task";
        } else {
            word1 = "are ";
            word2 = " tasks";
        }
        System.out.println("Got it. I've added this task:\n " + this);
        System.out.println("Now there " + word1 + indexCount + word2 + " in your list");
    }
}
