public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String name, int id) {
        tasks[tasksCount] = new Task(name, false, id);
        tasksCount++;
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
        int j = 1;
        for (Task i : tasks) {
            if (i.getIsDone() == true) {
                System.out.print(j);
                System.out.print(" [X] ");
                System.out.println(i.getName());
            } else {
                System.out.print(j);
                System.out.print(" [ ] ");
                System.out.println(i.getName());
            }
            j++;
            if (j > tasksCount) {
                break;
            }
        }
    }
}
