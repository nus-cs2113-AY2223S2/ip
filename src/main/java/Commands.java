import java.util.Scanner;

public class Commands {
    static final String TASK_NO_EXIST = "Task does not exist!";
    static final String DELETE_TASK_MESSAGE = "Okay! I've deleted task: ";
    public static final String DEADLINE_USERINPUT_PREFIX = "/by";
    public static final int STATUSTYPE_DONE = 1;
    public static final int STATUSTYPE_NOTDONE = 0;
    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    public static void invalidCommand() {
        System.out.println("I didn't get that!");
    }
    public static void showList() {
        if (TaskList.getNumItems() == 0) {
            System.out.println("We are free! Let's go play!");
        } else {
            System.out.println("Here's what we've gotta do:");
            TaskList.viewList();
            System.out.println("We currently have " + TaskList.getNumItems() + " tasks");
        }
    }

    public static void markTask(String userInput, int statusType) {
        int itemNum = Ui.getItemNumber(userInput);
        if (itemNum > 0 && itemNum <= TaskList.getNumItems()) {
            if (statusType == 1) {
                TaskList.markDone(itemNum - 1);
                System.out.println("Okay I've marked item " + itemNum + " as done:");
                TaskList.printItem(itemNum - 1);
            } else {
                TaskList.markNotDone(itemNum - 1);
                System.out.println("Oh we aren't done with item " + itemNum + "?");
                TaskList.printItem(itemNum - 1);
            }
        } else {
            System.out.println("Item does not exist!");
        }

    }

    public static void deleteTask(String userInput) {
        int itemIndex = Ui.getItemIndex(userInput);
        if (itemIndex <= TaskList.getNumItems() - 1) {
            TaskList.deleteTask(itemIndex);
            System.out.println(DELETE_TASK_MESSAGE + (itemIndex+1));
        } else {
            System.out.println(TASK_NO_EXIST);
        }
    }

    public static void addTodoTask(String userInput) {
        String itemDescription = Ui.getItemDescription(userInput);
        Todo newTask = new Todo(itemDescription);
        TaskList.addItem(newTask);
    }

    public static void addDeadlineTask(String userInput) {
        String itemDescription, dueDate;
        if (userInput.contains(DEADLINE_USERINPUT_PREFIX)) {
            itemDescription = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[STATUSTYPE_NOTDONE];
            dueDate = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[STATUSTYPE_DONE];
        } else {
            itemDescription = Ui.getItemDescription(userInput);
            dueDate = Ui.getDueDate(userInput);
        }
        Deadline newTask = new Deadline(itemDescription,dueDate);
        TaskList.addItem(newTask);
    }

    public static void addEventTask(String userInput) {
        String itemDescription = Ui.getItemDescription(userInput);
        String[] StartEndDates = Ui.getStartEndDates(userInput);
        String startDate = StartEndDates[STARTDATE_INDEX];
        String endDate = StartEndDates[ENDDATE_INDEX];
        Event newTask = new Event(itemDescription,startDate,endDate);
        TaskList.addItem(newTask);
    }
}
