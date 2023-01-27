public class Task {
    protected String taskName;
    protected static int indexCount = 0;
    protected boolean isDone;
    protected Types type;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        indexCount++;
    }

    public static int getIndexCount() {
        return indexCount;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Types getType(){
        return type;
    }

    public String listDescription(){
        return "";
    }

    public String checkBoxOutput(){
        String checkMark = isDone ? " [X] " : " [ ] ";
        String taskType = "";

        switch (type) {

        case DEADLINE:
            taskType = "[D]";
            break;

        case EVENT:
            taskType = "[E]";
            break;

        default:
            taskType = "[T]";
            break;
        }

        return  taskType + checkMark;
    }

    public void printAdded(){
        int taskCount = getIndexCount();
        String word1;
        String word2;
        if (taskCount == 1){
            word1 = "is ";
            word2 = " task";
        } else {
            word1 = "are ";
            word2 = " tasks";
        }
        System.out.println("Got it. I've added this task:\n " + listDescription());
        System.out.println("Now there " + word1 + getIndexCount() + word2 + " in your list");
    }
}
