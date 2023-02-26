/**
 * this class is mainly in charge of printing messages for the user
 */
public class UserInterface {
    private static String DASH = "__________________________________";

    public static void greet(){
        String greetingMessage = DASH + "\nI'm Goot :3\nWhat's up?\n" + DASH;
        System.out.println(greetingMessage);
    }

    public static void farewellMessage(){
        System.out.println(DASH + "\nBye bye! see you soon!\n" + DASH);
    }

    /**
     * this is a function that is called whenever the tasks in the taskArray is saved into the text file.
     */
    public static void savedMessage(){
        System.out.println(DASH+"\nSaved!\n"+DASH);
    }

    /**
     * this function prints the acknowledgment message after a task has been deleted
     * @param indexToDelete the identifying number(AS SHOWN BY THE LIST COMMAND) of the task to be deleted
     */
    public static void deleteMessage(int indexToDelete){
        Tasklist taskToDelete = Tasklist.get(indexToDelete-1);
        System.out.println(DASH+"\n Bye Bye task! It was nice meeting you :)\n"+taskToDelete.description);
        System.out.println(" Now you have "+Integer.toString(Tasklist.lastIndex)+" tasks in the list.\n"+DASH);
    }

    /**
     * this function prints the acknowledgment message after a task has been marked
     * @param taskIndex the identifying number (AS SHOWN BY THE LIST COMMAND) of the task to be marked
     */
    public static void markMessage(int taskIndex){
        Tasklist markedTask = Tasklist.get(taskIndex-1);
        System.out.println(DASH+"\nOkie I've marked this task as done:\n"+markedTask.description+"\n"+DASH);
    }

    /**
     * this function iterates the taskArray and prints the description string of each task
     */
    public static void printList(){
        System.out.println(DASH+"\nThese are the tasks in your list:");
        for(int index = 0;index<Tasklist.lastIndex;index++){
            System.out.println(Integer.toString(index+1)+"."+(Tasklist.get(index).description).trim());
        }
        System.out.println(DASH + "\n");
    }

    public static void acknowledgeTaskAdded(){
        Tasklist latestTask = Tasklist.get(Tasklist.lastIndex-1);
        String totalNumberOfTasks=Integer.toString(Tasklist.lastIndex);
        System.out.println(DASH+"\nI've added this task:\n"+latestTask.description+"\nNow you have "+
                totalNumberOfTasks+" tasks in the list.\n"+DASH);
    }
}
