import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String DEADLINE_CMD = "deadline";
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String ADD_PHRASE = " New %s I'll remember: ";
    private static final String SINGLE_TASK_ADDED_PHRASE = " You have %d task for now, all the best!";
    private static final String TASK_ADDED_PHRASE = " You have %d tasks for now, all the best!";

    private static ArrayList<Task> getTasks() {
        return TaskManager.tasks;
    }

    private static void addTask(Task newTask) {
        if (newTask != null) {
            TaskManager.getTasks().add(newTask);
        }
    }

    private static void insertTask(int indexOfTask, Task newTask) {
        if (newTask != null) {
            TaskManager.getTasks().add(indexOfTask, newTask);
        }
    }

    private static Task getTask(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= TaskManager.getTasks().size()) {
            return null;
        }
        return TaskManager.getTasks().get(indexOfTask);
    }

    private static void rmTask(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= TaskManager.getTasks().size()) {
            return;
        }
        TaskManager.getTasks().remove(indexOfTask);
    }

    /**
     * Mark a given task in the task list as done
     *
     * @param indexOfTask Index of given task in the task list
     * @return Task object representing the desired task being marked as done,
     * null if not an instance of Todo
     */
    public static Todo markDone(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= TaskManager.getTasks().size()) {
            printlnWithIndent(" You alright? I can't mark a task that doesn't exist as done xD");
            return null;
        }
        Task selectedTask = TaskManager.getTask(indexOfTask);
        if (!(selectedTask instanceof Todo)) {
            printlnWithIndent(" Erm I don't think this task can be marked done xD");
            return null;
        }
        Todo selectedTodo = (Todo) selectedTask;
        if (selectedTodo.getIsDone()) {
            printlnWithIndent(" Take a break maybe? Alright marked as done my friend:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        TaskManager.rmTask(indexOfTask);
        selectedTodo = selectedTodo.setDone(true);
        TaskManager.insertTask(indexOfTask, selectedTodo);
        printlnWithIndent(" Shall remember that this task is done:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    /**
     * Mark a given task in the task list as not done
     *
     * @param indexOfTask Index of desired task in the task list
     * @return Task object representing the desired task being marked as not done,
     * null if not an instance of Todo
     */
    public static Todo unmarkDone(int indexOfTask) {
        if (indexOfTask < 0 || indexOfTask >= TaskManager.getTasks().size()) {
            printlnWithIndent(" You alright? I don't think that task exists xD");
            return null;
        }
        Task selectedTask = TaskManager.getTask(indexOfTask);
        if (!(selectedTask instanceof Todo)) {
            printlnWithIndent(" Erm I don't think this task can be marked as not done? xD");
            return null;
        }
        Todo selectedTodo = (Todo) selectedTask;
        if (!selectedTodo.getIsDone()) {
            printlnWithIndent(" Getting a little ahead of yourself are you xD It's not even done:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        TaskManager.rmTask(indexOfTask);
        selectedTodo = selectedTodo.setDone(false);
        TaskManager.insertTask(indexOfTask, selectedTodo);
        printlnWithIndent(" (Why??) Anyway, I've marked this task as not done yet:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    private static boolean hasAnyTasks() {
        return !TaskManager.getTasks().isEmpty();
    }

    /**
     * Prints out the list of tasks added so far, or inform the user if no tasks have been added
     * yet
     */
    public static void printTasks() {
        if (!TaskManager.hasAnyTasks()) {
            printlnWithIndent(" Hope I'm not amnesiac, but I don't remember any tasks?");
        } else {
            ArrayList<Task> tasks = TaskManager.getTasks();
            printlnWithIndent(" I think you have these tasks:");
            for (int i = 1; i <= tasks.size(); i += 1) {
                printlnWithIndent(" " + i + "." + tasks.get(i - 1));
            }
        }
    }

    public void createTask(String command) {
        String[] parameters = command.split(" ");
        String typeOfTask = parameters[0];
        switch (typeOfTask) {
        case TaskManager.TODO_CMD:
            Todo newTodo = Todo.create(command);
            TaskManager.addTask(newTodo);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.TODO_CMD));
            printlnWithIndent("   " + newTodo.toString());
            break;
        case TaskManager.DEADLINE_CMD:
            Deadline newDeadline = Deadline.create(command);
            TaskManager.addTask(newDeadline);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.DEADLINE_CMD));
            printlnWithIndent("   " + newDeadline.toString());
            break;
        case TaskManager.EVENT_CMD:
            Event newEvent = Event.create(command);
            TaskManager.addTask(newEvent);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.EVENT_CMD));
            printlnWithIndent("   " + newEvent.toString());
            break;
        }
        int howManyTasks = TaskManager.getTasks().size();
        if (howManyTasks > 1) {
            printlnWithIndent(String.format(TaskManager.TASK_ADDED_PHRASE,
                    TaskManager.getTasks().size()));
        } else if (howManyTasks == 1) {
            printlnWithIndent(String.format(TaskManager.SINGLE_TASK_ADDED_PHRASE,
                    TaskManager.getTasks().size()));
        }
    }
}
