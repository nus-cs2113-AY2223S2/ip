public class ToDoList {
    private static String[] tasks;
    private static int numOfTasks = 0;
    public void createTask(String taskName) {
        tasks[numOfTasks] = taskName;
        numOfTasks++;
    }

    public ToDoList() {
        tasks = new String[100]; //default to list size of 100
    }

    public ToDoList(int listSize) {
        tasks = new String[listSize];
    }

    public void listTasks() {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.print(Long.toString(i+1) + ". " + tasks[i]);
            System.out.println();
        }
    }
}
