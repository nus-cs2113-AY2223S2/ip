package User;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import java.util.ArrayList;
import static User.UI.line;

public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<Task>(100);

    /**
     *Iterates through taskList and prints the task number, description, status and other
     * details such as start and end time for the relevant task types
     */
    public static void printList(){
        if (taskList.isEmpty()) {
            UI.listIsEmpty();
        } else {
            System.out.println("Here are the tasks in your list:\n");
            for (Task item : taskList) {
                System.out.print((taskList.indexOf(item) + 1) + ".");
                System.out.println(item.toString());
            }
            System.out.println(line);
        }
    }

    /**
     * Deletes a task from the array based on its index
     * @param userInput The index of the item that is to be deleted
     */
    public static void deleteItem(String userInput) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        if (itemNumber >= taskList.size()) {
            UI.indexNotFound();
        } else {
            System.out.println(line);
            System.out.println("Noted. I've removed this task:\n" + taskList.get(itemNumber).getSymbol()
                    + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription() + '\n');
            taskList.remove(itemNumber);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(line);
        }
    }

    /**
     * Accesses the index of the item in the array and updates its status to be done
     * @param userInput The index of the item to be marked as done
     */
    public static void markItem(String userInput) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        System.out.println(line);
        if (itemNumber >= taskList.size()){
            UI.indexNotFound();
        } else {
            if (taskList.get(itemNumber).isDone()){
                System.out.println("The task has already been completed!");
                System.out.println(line);
            } else {
                taskList.get(itemNumber).markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
                System.out.println(line);
            }
        }
    }
    /**
     * Accesses the index of the item in the array and updates its status to be not done
     * @param userInput The index of the item to be marked as not done
     */
    public static void unMarkItem(String userInput) {
        int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        //to check if an item is within the index of the list
        System.out.println(line);
        if (itemNumber >= taskList.size()) {
            UI.indexNotFound();
        } else {
            if (taskList.get(itemNumber).isDone()) {
                taskList.get(itemNumber).markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet: \n" + "    "
                        + taskList.get(itemNumber).getStatusIcon() + taskList.get(itemNumber).getDescription());
                System.out.println(line);
            } else {
                System.out.println("The task has not been completed!");
                System.out.println(line);
            }
        }
    }

    /**
     * Creates an Event-type task and adds it to the array list
     * The user input is parsed into the function and split into three parts: the description,
     * the start time and the end time.
     * A message at the end will inform the user what is the updated number of tasks that they have
     * @param userInput The user input to be parsed and split into its parts.
     */
    public static void createEvent(String userInput){
        String[] userInputParts;
        userInput = userInput.replace("event ","");
        userInputParts = userInput.split("/");
        userInputParts[1] = userInputParts[1].replace("from ", "");
        userInputParts[2] = userInputParts[2].replace("to ", "");
        Event event = new Event(userInputParts[0], userInputParts[1], userInputParts[2]);
        taskList.add(event);
        System.out.println("Got it. I've added this task: ");
        System.out.println(event.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
        System.out.println(line);
    }
    /**
     * Creates a Deadline-type task and adds it to the array list
     * The user input is parsed into the function and split into two parts: the description
     * and the deadline time.
     * A message at the end will inform the user what is the updated number of tasks that they have
     * @param userInput The user input to be parsed and split into its parts.
     */
    public static void createDeadline(String userInput){
        String[] userInputParts;
        userInput = userInput.replace("deadline ","");
        userInputParts = userInput.split("/");
        userInputParts[1] = userInputParts[1].replace("by ", "");
        Deadline deadline = new Deadline(userInputParts[0], userInputParts[1]);
        taskList.add(deadline);
        System.out.println(deadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
        System.out.println(line);
    }
    /**
     * Creates an todo-type task and adds it to the array list
     * The user input is parsed in the function
     * A message at the end will inform the user what is the updated number of tasks that they have
     * @param userInput The user input to be parsed
     */
    public static void createToDo(String userInput){
        userInput = userInput.replace("todo ","");
        String dummy = userInput.replace(" ","");
        //checks if description is empty and throws warning back to user
        if (dummy.isEmpty()){
            UI.emptyDescription();
        } else {
            Task task = new Task(userInput);
            taskList.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(task.getSymbol() + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
            System.out.println(line);
        }
    }
    /**
     * Prints out all items in the task list that contain the keyword and their respective indexes
     * If no items are found with the given keyword, a prompt will be given to the user
     * to use a different keyword
     * @param userInput The keyword the user will use to look for the item
     */
    public static void find(String userInput) {
        ArrayList<Task> findList = new ArrayList<Task>(100);
        ArrayList<Integer> indexOfItem = new ArrayList(100);
        userInput = userInput.replace("find ", "");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(userInput)) {
                findList.add(taskList.get(i));
                indexOfItem.add(i+1);
            }
        }
        if (!findList.isEmpty()) {
            System.out.println(line);
            System.out.println("Here are the matching tasks in your list:");
            for (int j = 0; j < indexOfItem.size(); j++) {
                System.out.print((indexOfItem.get(j)) + ".");
                System.out.println(findList.get(j).toString());
            }
            System.out.println(line);
        } else {
            System.out.println("Use another keyword!");
            System.out.println(line);
        }
    }


}
