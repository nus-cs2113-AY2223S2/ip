public class ToDoList {
    private static Task[] tasks;
    private static int numOfTasks = 0;
    public void createTask(String taskName) {
        Task task = new Task(taskName);
        tasks[numOfTasks] = task;
        numOfTasks++;
    }

    public void markTask(int taskNum) {
        tasks[taskNum-1].markDone();
    }

    public void unmarkTask(int taskNum) {
        tasks[taskNum-1].unmarkDone();
    }
    public ToDoList() {
        tasks = new Task[100]; //default to list size of 100
    }

    public ToDoList(int listSize) {
        tasks = new Task[listSize];
    }

    public void listTasks() {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.print(Long.toString(i+1) + ". ");
            if (tasks[i].isDone() == true) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.print(tasks[i].getTaskName());
            System.out.println();
        }
    }
}
