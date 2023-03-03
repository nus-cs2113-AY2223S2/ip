import tasktypes.Todo;
import tasktypes.*;

import java.util.ArrayList;

public class Commands {

    public static final int DEADLINE_PARAM_INDEX = 1;
    static final String TASK_NO_EXIST = "Task does not exist!";
    static final String DELETE_TASK_MESSAGE = "Okay! I've deleted task: ";
    public static final String DEADLINE_USERINPUT_PREFIX = "/by";
    public static final boolean STATUSTYPE_DONE = true;
    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    public static final String SHOWLIST_HEADER = "Here's what we've gotta do:";
    public static final String SEARCH_NO_RESULT = "We don't have that in the list!";
    public static final String INVALID_COMMAND_MESSAGE = "I didn't get that!";
    public static final String EMPTY_TASKLIST_MESSAGE = "We are free! Let's go play!";
    public static final int USERINPUT_DUEDATE_INDEX = 1;
    public static final int USERINPUT_DESCRIPTION_INDEX = 0;
    /**
     * Prints out a message stating that the input command is not a valid command
     */
    public static void invalidCommand() {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

    /**
     * Shows the current tasklist contents
     * Prints out a unique message if the tasklist is empty
     */
    public static void showList() {
        if (TaskList.getNumTasks() == USERINPUT_DESCRIPTION_INDEX) {
            System.out.println(EMPTY_TASKLIST_MESSAGE);
        } else {
            String showList_footer = "We currently have " + TaskList.getNumTasks() + " tasks";
            Ui.printList(TaskList.getList(),SHOWLIST_HEADER,showList_footer);
        }
    }

    /**
     * Marks a given task in the tasklist as done or not done depending on parameter statusType
     *
     * @param userInput the input from the user
     * @param statusType input true for done, false for notDone
     */
    public static void markTask(String userInput, boolean statusType) {
        int itemNum = Ui.getItemNumber(userInput);
        if (itemNum > USERINPUT_DESCRIPTION_INDEX && itemNum <= TaskList.getNumTasks()) {
            if (statusType == STATUSTYPE_DONE) {
                TaskList.markDone(itemNum - USERINPUT_DUEDATE_INDEX);
                System.out.println("Okay I've marked item " + itemNum + " as done:");
                TaskList.printItem(itemNum - USERINPUT_DUEDATE_INDEX);
            } else {
                TaskList.markNotDone(itemNum - USERINPUT_DUEDATE_INDEX);
                System.out.println("Oh we aren't done with item " + itemNum + "?");
                TaskList.printItem(itemNum - USERINPUT_DUEDATE_INDEX);
            }
        } else {
            System.out.println(TASK_NO_EXIST);
        }
    }

    /**
     * Removes a given task from the tasklist based on the input message
     * @param userInput user's input
     */
    public static void deleteTask(String userInput) {
        int itemIndex = Ui.getItemIndex(userInput);
        if (itemIndex <= TaskList.getNumTasks() - USERINPUT_DUEDATE_INDEX) {
            TaskList.deleteTask(itemIndex);
            System.out.println(DELETE_TASK_MESSAGE + (itemIndex+ USERINPUT_DUEDATE_INDEX));
        } else {
            System.out.println(TASK_NO_EXIST);
        }
    }

    /**
     * takes in user's description and adds a Todo task to the tasklist
     * @param userInput description of the todo
     */
    public static void addTodoTask(String userInput) {
        String itemDescription = Ui.getItemDescription(userInput);
        Todo newTask = new Todo(itemDescription);
        TaskList.addItem(newTask);
    }

    /**
     * takes in user's description and adds a deadline task to the tasklist
     * if the user has not supplied a deadline, method will request for the deadline
     * @param userInput description of the deadline task
     */
    public static void addDeadlineTask(String userInput) {
        String itemDescription, dueDate;
        if (userInput.contains(DEADLINE_USERINPUT_PREFIX)) {
            itemDescription = userInput.split(" ", 2)[DEADLINE_PARAM_INDEX];
            itemDescription = itemDescription.split(DEADLINE_USERINPUT_PREFIX,2)[USERINPUT_DESCRIPTION_INDEX];
            dueDate = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[USERINPUT_DUEDATE_INDEX];
        } else {
            itemDescription = Ui.getItemDescription(userInput);
            dueDate = Ui.getDueDate(userInput);
        }
        Deadline newTask = new Deadline(itemDescription,dueDate);
        TaskList.addItem(newTask);
    }
    /**
     * takes in user's input and adds an event task to the tasklist
     * if the user has not supplied start and/or end dates, method will request for the dates
     * @param userInput description of the event task
     */
    public static void addEventTask(String userInput) {
        String itemDescription = Ui.getItemDescription(userInput);
        String[] StartEndDates = Ui.getStartEndDates(userInput);
        String startDate = StartEndDates[STARTDATE_INDEX];
        String endDate = StartEndDates[ENDDATE_INDEX];
        Event newTask = new Event(itemDescription,startDate,endDate);
        TaskList.addItem(newTask);
    }

    /**
     * Checks if searchTerm can be found inside taskDescription.
     * @param searchTerm String to be found in the taskDescription
     * @param taskDescription String where searchTerm is to be found in
     * @return true if taskDescription contains searchTerm
     */
    public static boolean containsSearchTerm(String searchTerm, String taskDescription) {
        return taskDescription.contains(searchTerm);
    }

    /**
     * Searches the current tasklist for a user-supplied String
     * Will print out the list of items that match the user input if exists.
     * Else will print out a no items found message
     * @param userInput the String to be searched for
     */
    public static void searchTask(String userInput) {
        String searchTerm = Ui.getItemDescription(userInput);
        ArrayList<Task> resultsList = new ArrayList<>();
        int searchResults = USERINPUT_DESCRIPTION_INDEX;
        for (int i = USERINPUT_DUEDATE_INDEX; i <= TaskList.getNumTasks(); ++i) {
            String taskDescription = TaskList.getItem(i- USERINPUT_DUEDATE_INDEX).getDescription();
            if (containsSearchTerm(searchTerm, taskDescription)) {
                resultsList.add(TaskList.getItem(i- USERINPUT_DUEDATE_INDEX));
                searchResults += USERINPUT_DUEDATE_INDEX;
            }
        }
        if (searchResults == USERINPUT_DESCRIPTION_INDEX) {
            System.out.println(SEARCH_NO_RESULT);
        } else {
            String resultsList_header = "We have " + searchResults + " results!";
            Ui.printList(resultsList, resultsList_header, "");
        }
    }
}
