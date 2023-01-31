import java.util.ArrayList;

public class ToDoList {
    private static final ArrayList<Task> TaskList = new ArrayList<>(10);
    private static int NumTasks = 0;
    public static void addItem (String in) {
        Task task = new Task(in);

        TaskList.add(task);
        NumTasks += 1;
        System.out.println("added: " + in);
    }
    public static int getNumItems() {
        return NumTasks;
    }
    public static void viewList () {
        for (int i = 0; i < TaskList.size(); ++i) {
            System.out.print(i+1 + ". ");

            System.out.println(TaskList.get(i).getTask());
        }
    }
    public static void markDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals(" ")) {
            TaskList.get(index).markDone();
        }
    }
    public static void markNotDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals("X")) {
            TaskList.get(index).markNotDone();
        }
    }
    public static Task getItem (int index) {
        return TaskList.get(index);
    }
    public static void printItem (int index) {
        System.out.print(index+1 + ". ");
        System.out.println(TaskList.get(index).getTask());
    }


}
