import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> initTaskList() {
        ArrayList<Task> list = new ArrayList<Task>();
        return list;
    }
    public static void listOut(ArrayList<Task> listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < listOfTasks.size(); i += 1){
            System.out.println((i + 1) + ". " + listOfTasks.get(i).getStatusIcon() + listOfTasks.get(i).description);
        }
    }
    public static void mark(String command, ArrayList<Task> listOfTasks) {
        try {
            Integer.parseInt(command);
        } catch(NumberFormatException error) {
            TaskList.addToList("mark " + command, listOfTasks);
            return;
        }
        int taskNumber = Integer.parseInt(command) - 1;
        listOfTasks.get(taskNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(listOfTasks.get(taskNumber).getStatusIcon() + listOfTasks.get(taskNumber).description);
    }
    public static void unmark(String command, ArrayList<Task> listOfTasks) {
        try {
            Integer.parseInt(command);
        } catch(NumberFormatException error) {
            TaskList.addToList("unmark " + command, listOfTasks);
            return;
        }
        int taskNumber = Integer.parseInt(command) - 1;
        listOfTasks.get(taskNumber).markAsNotDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(listOfTasks.get(taskNumber).getStatusIcon() + listOfTasks.get(taskNumber).description);
    }
    public static void addToList(String command, ArrayList<Task> listOfTasks) {
        listOfTasks.add(new Task(command));
        System.out.print("added: ");
        Conversation.copy(command);
    }
}
