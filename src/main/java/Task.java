public class Task {
    private boolean isDone;
    private String name;

    public static int totalTasks = 0;
    Task(String name) {
        this.name = name;
        isDone = false;
        ++totalTasks;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone(){
        return isDone;
    }

    public String getName(){
        return name;
    }
}
