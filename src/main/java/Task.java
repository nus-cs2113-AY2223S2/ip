import java.util.ArrayList;

public class Task {
    private String name;
    private boolean done;

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static void addToTasksList(String input, ArrayList<Task> listOfTasks) {
        System.out.println("added: "+input);
        Task task = new Task(input, false);
        listOfTasks.add(task);
    }

    public static void listTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).isDone()) {
                System.out.println((i + 1) + ".[X] " + listOfTasks.get(i).getName());
            } else {
                System.out.println((i + 1) + ".[ ] " + listOfTasks.get(i).getName());
            }
        }
    }
}
