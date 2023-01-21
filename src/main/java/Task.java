import java.util.ArrayList;

public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return isCompleted;
    }

    public void setStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public static void addToTasksList(String input, ArrayList<Task> listOfTasks) {
        System.out.println("added: "+input);
        Task task = new Task(input, false);
        listOfTasks.add(task);
    }

    public static void listTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getStatus()) {
                System.out.println((i + 1) + ".[X] " + listOfTasks.get(i).getName());
            } else {
                System.out.println((i + 1) + ".[ ] " + listOfTasks.get(i).getName());
            }
        }
    }
}
