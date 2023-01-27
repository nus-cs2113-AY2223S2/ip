public class TaskManager {
    private Task[] Tasks = new Task[100];
    private int TasksCount = 0;

    public void addTask(String name, int id) {
        Tasks[TasksCount] = new Task(name, false, id);
        TasksCount++;
    }

    public void markTask(int id) {
        Tasks[id].setIsDone(true);
        System.out.println("The task has been marked as done!");
        System.out.println("[X] " + Tasks[id].getName());
    }

    public void unmarkTask(int id) {
        Tasks[id].setIsDone(false);
        System.out.println("The task has been marked as NOT done!");
        System.out.println("[ ] " + Tasks[id].getName());
    }

    public void listTask() {
        int j = 1;
        for (Task i : Tasks) {
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
            if (j > TasksCount) {
                break;
            }
        }
    }
}
