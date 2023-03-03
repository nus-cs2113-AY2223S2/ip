import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> newTaskList) {
        taskList = newTaskList;
    }
    public static void addToTaskList(Task newTask) {
        taskList.add(newTask);
    }
    public static void list() {
        if (taskList.size() == 0) {
            System.out.println(StrIntLib.emptyList);
            return;
        }
        System.out.println(StrIntLib.listText);
        int ID = 1;
        for (Task item : taskList) {
            System.out.println(ID + "." + printIconStatus(item));
            ID += 1;
        }
    }
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
    public static void addTask(String[] parts) {
        try {
            Task newTask = new Task(parts[0]);
            addToTaskList(newTask);
            System.out.println(StrIntLib.taskAddedDebug);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(StrIntLib.missingInputsError);
        }
    }
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
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
