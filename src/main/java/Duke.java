import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static ArrayList<Task> addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        return taskList;
    }
    
    public static void welcomeMessage() {
        System.out.println(StrIntLib.welcomeText);
    }
    public static void byeMessage() {
        System.out.println(StrIntLib.byeText);
    }
    public static void list(ArrayList<Task> tasksList) {
        System.out.println(StrIntLib.listText);
        int ID = 1;
        for (Task item : tasksList) {
            System.out.println(ID + "." + printIconStatus(item));
            ID += 1;
        }
    }

    public static void uncheckDoneStatus(String[] stringsList, ArrayList<Task> taskList) {
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

    public static void checkDoneStatus(String[] stringsList, ArrayList<Task> taskList) {
        if (Integer.parseInt(stringsList[1]) > taskList.size()){
            System.out.println(StrIntLib.noItemText);
        } else {
            taskList.get(Integer.parseInt(stringsList[1]) - 1).markAsDone();
            System.out.println(StrIntLib.markText);
            Task item = taskList.get(Integer.parseInt(stringsList[1]) - 1);
            System.out.println(printIconStatus(item));
        }
    }

    public static String printIconStatus(Task selectedTask) {
        String out = "[" + selectedTask.getIcon() + "][" + selectedTask.getStatusIcon() + "] " + selectedTask.description;
        if (selectedTask instanceof Events){
            out += " (" + selectedTask.getStart() + " " + selectedTask.getEnd() + ")";
        } else if (selectedTask instanceof Deadlines) {
            out += " (" + selectedTask.getDeadline() + ")";
        }
        return out;
    }
    private static ArrayList<Task> addDeadline(ArrayList<Task> taskList, String[] parts) {
        String content = parts[0];
        String dueDate = parts[1].replace(StrIntLib.by, StrIntLib.byReplacement).trim();
        Deadlines newDeadline = new Deadlines(content, dueDate);
        ArrayList<Task> newTaskList = addTask(taskList, newDeadline);
        System.out.println(StrIntLib.addTaskText);
        System.out.println(printIconStatus(newDeadline));
        System.out.println(StrIntLib.taskCount1 + newTaskList.size() + StrIntLib.taskCount2);
        return newTaskList;
    }

    private static ArrayList<Task> addEvent(ArrayList<Task> taskList, String[] parts) {
        String content = parts[0];
        String start = parts[1].replace(StrIntLib.from, StrIntLib.fromReplacement).trim();
        String end = parts[2].replace(StrIntLib.to, StrIntLib.toReplacement).trim();
        Events newEvent = new Events(content, start, end);
        ArrayList<Task> newTaskList = addTask(taskList, newEvent);
        System.out.println(StrIntLib.addTaskText);
        System.out.println(printIconStatus(newEvent));
        System.out.println(StrIntLib.taskCount1 + newTaskList.size() + StrIntLib.taskCount2);
        return newTaskList;
    }
    private static ArrayList<Task> addToDo(ArrayList<Task> taskList, String[] inputs) {
        ToDos newToDo = new ToDos(inputs[1]);
        ArrayList<Task> newTaskList = addTask(taskList, newToDo);
        System.out.println(StrIntLib.addTaskText);
        System.out.println(printIconStatus(newToDo));
        System.out.println(StrIntLib.taskCount1 + newTaskList.size() + StrIntLib.taskCount2);
        return newTaskList;
    }

    private static ArrayList<Task> addTask(ArrayList<Task> taskList, String[] parts) {
        Task newTask = new Task(parts[0]);
        ArrayList<Task> newTaskList = addTask(taskList, newTask);
        System.out.println(StrIntLib.taskAddedDebug);
        return newTaskList;
    }
    private static void invalidCommand() {
        System.out.println(StrIntLib.invalidCmdText);
    }

    public static void main(String[] args) {
        welcomeMessage();
        String input;
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            String[] partsList = (inputs.length > 1) ? inputs[1].split("/") : null;
            switch (command) {
            case StrIntLib.cmdBye:
                byeMessage();
                return;
            case StrIntLib.cmdList:
                list(taskList);
                break;
            case StrIntLib.cmdMark:
                checkDoneStatus(inputs, taskList);
                break;
            case StrIntLib.cmdUnmark:
                uncheckDoneStatus(inputs, taskList);
                break;
            case StrIntLib.cmdAdd:
                taskList = addTask(taskList, partsList);
                break;
            case StrIntLib.cmdToDo:
                taskList = addToDo(taskList, inputs);
                break;
            case StrIntLib.cmdEvent:
                taskList = addEvent(taskList, partsList);
                break;
            case StrIntLib.cmdDeadline:
                taskList = addDeadline(taskList, partsList);
                break;
            default:
                invalidCommand();
                break;
            }
        }
    }
}
