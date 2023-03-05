import java.util.ArrayList;

/**
 * The TaskList class handles operations on the task list.
 */
public class TaskList {
    protected static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> newTaskList) {
        taskList = newTaskList;
    }

    /**
     * This method is used to add a new task to the task list.
     *
     * @param newTask New Task.
     */
    public static void addToTaskList(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * This method is used to list out tasks in the task list.
     */
    public static void list() {
        if (taskList.size() == 0) {
            System.out.println(StrIntLib.emptyList);
            return;
        }
        System.out.println(StrIntLib.listText);
        int index = 1;
        for (Task item : taskList) {
            System.out.println(index + "." + printIconStatus(item));
            index = increment(index);
        }
    }

    /**
     * This method is used to generate a String based on the type and status of a task.
     *
     * @param selectedTask Task selected.
     * @return String This returns type, status and description of task selected.
     */
    public static String printIconStatus(Task selectedTask) {
        String out = "[" + selectedTask.getIcon() + "][" +
                selectedTask.getStatusIcon() + "] " +
                selectedTask.description;
        if (selectedTask instanceof Events){
            out += " (from: " + selectedTask.getStart() + " to: " + selectedTask.getEnd() + ")";
        } else if (selectedTask instanceof Deadlines) {
            out += " (by: " + selectedTask.getDeadline() + ")";
        }
        return out;
    }

    /**
     * This method is used to create new Deadlines task to be added to the task list.
     *
     * @param parts Processed input
     */
    public static void addDeadline(String[] parts) {
        try{
            String content = parts[0].trim();
            String dueDate = parts[1].replace(StrIntLib.by, StrIntLib.byReplacement).trim();
            Deadlines newDeadline = new Deadlines(content, dueDate);
            addToTaskList(newDeadline);
            System.out.println(StrIntLib.addTaskText);
            System.out.println(printIconStatus(newDeadline));
            System.out.println(StrIntLib.taskCount1 + taskList.size() + StrIntLib.taskCount2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
        }
    }

    /**
     * This method is used to create new Events task to be added to the task list.
     *
     * @param parts Processed input
     */
    public static void addEvent(String[] parts) {
        try {
            String content = parts[0].trim();
            String start = parts[1].replace(StrIntLib.from, StrIntLib.fromReplacement).trim();
            String end = parts[2].replace(StrIntLib.to, StrIntLib.toReplacement).trim();
            Events newEvent = new Events(content, start, end);
            addToTaskList(newEvent);
            System.out.println(StrIntLib.addTaskText);
            System.out.println(printIconStatus(newEvent));
            System.out.println(StrIntLib.taskCount1 + taskList.size() + StrIntLib.taskCount2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
        }
    }

    /**
     * This method is used to create new ToDos task to be added to the task list.
     *
     * @param inputs Processed input
     */
    public static void addToDo(String[] inputs) {
        try {
            ToDos newToDo = new ToDos(inputs[1].trim());
            addToTaskList(newToDo);
            System.out.println(StrIntLib.addTaskText);
            System.out.println(printIconStatus(newToDo));
            System.out.println(StrIntLib.taskCount1 + taskList.size() + StrIntLib.taskCount2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
        }
    }

    /**
     * This method is used to update the status of a task to not done.
     * This method uses markAsNotDone method from Task class.
     * This method extracts an integer from params representing the index of task to be unmarked.
     *
     * @param stringsList Processed input
     */
    public static void uncheckDoneStatus(String[] stringsList) {
        try{
            int test = Integer.parseInt(stringsList[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
            return;
        } catch (NumberFormatException e) {
            System.out.println(StrIntLib.wrongFormatError);
            return;
        }
        if (Integer.parseInt(stringsList[1]) > taskList.size()){
            System.out.println(StrIntLib.noItemText);
        } else {
            taskList.get(Integer.parseInt(stringsList[1]) - 1).markAsNotDone();
            System.out.println(StrIntLib.unmarkText);
            Task item = taskList.get(Integer.parseInt(stringsList[1]) - 1
            );
            System.out.println(printIconStatus(item));
        }
    }

    /**
     * This method is used to update the status of a task to done.
     * This method uses markAsDone method from Task class.
     * This method extracts an integer from params representing the index of task to be marked.
     *
     * @param stringsList Processed input
     */
    public static void checkDoneStatus(String[] stringsList) {
        try{
            int test = Integer.parseInt(stringsList[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
            return;
        } catch (NumberFormatException e) {
            System.out.println(StrIntLib.wrongFormatError);
            return;
        }
        if (Integer.parseInt(stringsList[1]) > taskList.size()){
            System.out.println(StrIntLib.noItemText);
        } else {
            taskList.get(Integer.parseInt(stringsList[1]) - 1).markAsDone();
            System.out.println(StrIntLib.markText);
            Task item = taskList.get(Integer.parseInt(stringsList[1]) - 1);
            System.out.println(printIconStatus(item));
        }
    }

    /**
     * This method is used to create new Task class task to be added to the task list.
     * This method is used for debugging.
     *
     * @param parts Processed input
     */
    public static void addTask(String[] parts) {
        try {
            Task newTask = new Task(parts[0]);
            addToTaskList(newTask);
            System.out.println(StrIntLib.taskAddedDebug);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
        }
    }

    /**
     * This method is used to remove a task from the task list.
     * This method extracts an integer representing the index of the task to be deleted.
     *
     * @param parts Processed input
     */
    public static void deleteTask(String[] parts) {
        try{
            int test = Integer.parseInt(parts[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(StrIntLib.generalError);
            return;
        }
        if (Integer.parseInt(parts[1]) > taskList.size()){
            System.out.println(StrIntLib.noItemText);
        } else {
            System.out.println(StrIntLib.deleteText);
            Task deletedTask = taskList.remove(Integer.parseInt(parts[1]) - 1);
            System.out.println(printIconStatus(deletedTask));
            System.out.println(StrIntLib.taskCount1 + taskList.size() + StrIntLib.taskCount2);
        }
    }

    /**
     * This method is used to find tasks with descriptions that contain the searched term.
     *
     * @param term Search term
     */
    public static void findTasks(String term) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task item : taskList) {
            if (item.description.toLowerCase().contains(term.toLowerCase())) {
                matches.add(item);
            }
        }
        if (matches.size() == 0) {
            System.out.println(StrIntLib.noMatches);
        } else {
            System.out.println(StrIntLib.matchesListText);
            int index = 1;
            for (Task item : matches) {
                System.out.println(index + "." + printIconStatus(item));
                index = increment(index);
            }
        }
    }
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
    public static int increment(int num) {
        return num + 1;
    }
}
