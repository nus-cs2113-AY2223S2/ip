package duke;
public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String name, int id) {
        tasks[taskCount] = new Task(name, false, id);
        taskCount++;
    }

    public void addDeadline(String name, String deadline, int id) {
        tasks[taskCount] = new Deadline(name, false, id, deadline);
        taskCount++;
    }

    public void addEvent(String eventName, String startTime, String finishTime, int id) {
        tasks[taskCount] = new Event(eventName, false, id, startTime, finishTime);
        taskCount++;
    }

    public void markTask(int id) {
        tasks[id].setIsDone(true);
        System.out.println("The task has been marked as done!");
        System.out.println("[X] " + tasks[id].getName());
    }

    public void unmarkTask(int id) {
        tasks[id].setIsDone(false);
        System.out.println("The task has been marked as NOT done!");
        System.out.println("[ ] " + tasks[id].getName());
    }

    public void listTask() {
        for (int i = 0; i < taskCount; i++) {
            tasks[i].print();
        }
    }
}
