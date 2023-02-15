public class Sherlock {
    public static void main(String[] args) {
        TasksList tasksList = new TasksList();
        TasksLoader tasksLoader = new TasksLoader(tasksList);
        tasksLoader.loadTasks();;

        TaskListener.greet();


        TaskListener taskListener = new TaskListener(tasksList, tasksLoader);
        taskListener.listen();
    }
}
