public class Marker {
    public void markTask(Task task){
        task.done = true;
    }
    public void unmarkTask(Task task){
        task.done = false;
    }
}
