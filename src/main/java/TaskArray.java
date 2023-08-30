/**
 * Represents an array of <code>Task</code> objects and serves as the database for Duke.
 * Contains a <code>storedTasks</code> array of 100 <code>Task</code> objects.
 * Initializes a <code>count</code> variable for index navigation of the aforementioned array.
 */
public class TaskArray {

    static Task[] storedTasks = new Task[100];

    static int count;


    public TaskArray(){
    }

    /**
     *Creates a <code>Todo</code> to store within the <code>storedTasks</code> array.
     *If the input contains no parameters for the <code>Todo</code> to be created,
     * outputs a messagee.
     * @param userInput Parameters which the user has entered into Duke.
     */
    public static void createToDo(String userInput){
        if (userInput == null){
            System.out.println(UI.LINE + "☹ OOPS!!! The description of a todo cannot be empty.\n" + UI.LINE);
        }else {
            storedTasks[count] = new Todo(userInput);
        }
    }

    /**
     *Creates a <code>Deadline</code> to store within the <code>storedTasks</code> array.
     *If the input does not follow a stipulated format, displays an error message.
     * @param userInput Parameters which the user has entered into Duke.
     */
    public static void createDeadline(String userInput){
        try {
            String[] parameters = userInput.split("by", 2);
            storedTasks[count] = new Deadline(parameters[0], parameters[1]);
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(UI.LINE + "Please follow the format:[deadline <Task Name> by <Deadline Date>]\n" + UI.LINE);
        }
    }

    public static int getCount(){
        return count;
    }

    /**
     *Navigates through the <code>storedTasks</code> array to delete a <code>Task</code> at a given index.
     * @param userInput Index from which task to be deleted.
     */
    public static void delete(String userInput) {
        String index = userInput.trim();
        int indexInt = Integer.parseInt(index) - 1;
        try {
            count--;
            System.out.println(UI.LINE + "Noted. I've removed this task:\n" + storedTasks[indexInt].toString());
            System.out.println("Now you have " + count + " tasks in the list.\n" + UI.LINE);
            Task[] temp = new Task[100];

            for (int i = 0, k = 0; i < storedTasks.length; i++) {
                if (i == indexInt) {
                    continue;
                }

                temp[k++] = storedTasks[i];
            }
            storedTasks = temp;
        }catch (NullPointerException e) {
            System.out.println(UI.LINE + "Task doesn't exist!\n" + UI.LINE);
        }
    }
    /**
     *Creates an <code>Event</code> to store within the <code>storedTasks</code> array.
     *If the input does not follow a stipulated format, displays an error message.
     * @param userInput Parameters which the user has entered into Duke.
     */
    public static void createEvent(String userInput){
        try {
            String[] temp = userInput.split(" from ", 2);
            String[] parameters = temp[1].split(" to ", 2);
            storedTasks[count] = new Event(temp[0], parameters[0], parameters[1]);
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(UI.LINE + "Please follow the format:[event <Task Name> from <Start Time> to <End Time>]\n" + UI.LINE);
        }
    }
    /**
     *Navigates through the <code>storedTasks</code> array to print out each <code>Task</code> in an appropriate format.
     */
    public static void print(){
        System.out.println(UI.LINE);
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + "." + storedTasks[i].toString());
        }
        System.out.println(UI.LINE);
    }

    /**
     *Navigates through the <code>storedTasks</code> array to indicate a <code>Task</code> has been completed.
     * @param userInput Index of the task in which user wishes to mark.
     */
    public static void mark(String userInput){
        try {
            String index = userInput.trim();
            int indexInt = Integer.parseInt(index) - 1;
            storedTasks[indexInt].markAsDone();
            System.out.println(UI.LINE + "     Nice! I've marked this task as done:\n [X] " + storedTasks[indexInt].description + "\n " + UI.LINE);
        }catch (NullPointerException e){
            System.out.println("No such task found!");
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Please input a number ranging from 1 to 100!");
        }
    }

    /**
     *Navigates through the <code>storedTasks</code> array to indicate a <code>Task</code> has not been completed.
     *@param userInput Index of the task in which user wishes to unmark.
     */
    public static void unmark(String userInput){
        try {
            String index = userInput.trim();
            int indexInt = Integer.parseInt(index) - 1;
            storedTasks[indexInt].unmarkTask();
            System.out.println(UI.LINE + "     OK, I've marked this task as not done yet:\n [ ] " + storedTasks[indexInt].description + "\n " + UI.LINE);
        }catch (NullPointerException e){
            System.out.println("No such task found!");
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Please input a number ranging from 1 to 100!");
        }
    }

    /**
     *Navigates through the <code>storedTasks</code> array to locate all <code>Task</code> objects that contain the given input.
     * These are then printed out in an appropriate format.
     *@param userInput Parameters that user wishes to locate tasks for in the <code>storedTasks</code> array.
     */
    public static void find(String userInput) {
        Task[] outputHolder = new Task[100];
        int tempCount = 0;
        for (int i = 0; i < count; i++) {
            if (storedTasks[i].description.contains(userInput)) {
                outputHolder[tempCount] = storedTasks[i];
                tempCount++;
            }
        }
        System.out.println(UI.LINE);
        if (tempCount > 0) {
            System.out.println("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tempCount; i++) {
                System.out.println(i + 1 + "." + outputHolder[i].toString());
            }
        }
        else{
            System.out.println("No tasks in your list that match!\n");
        }
        System.out.println(UI.LINE);


    }
}
