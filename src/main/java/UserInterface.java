public class UserInterface {
    private static String DASH = "__________________________________";

    public static void greet(){
        String greetingMessage = DASH + "\nI'm Goot :3\nWhat's up?\n" + DASH;
        System.out.println(greetingMessage);
    }

    public static void farewellMessage(){
        System.out.println(DASH + "\nBye. Hope to see you again soon!\n" + DASH);
    }

    public static void savedMessage(){
        System.out.println(DASH+"\nSaved!\n"+DASH);
    }

    public static void deleteMessage(int indexToDelete){
        System.out.println(DASH+"\n Bye Bye task! It was nice meeting you :)\n"+Tasklist.taskArray[indexToDelete-1].description);
        System.out.println(" Now you have "+Integer.toString(Tasklist.lastIndex)+" tasks in the list.\n"+DASH);
    }

    public static void markMessage(int taskIndex){
        System.out.println(DASH+"\nNice! I've marked this task as done:\n"+Tasklist.taskArray[taskIndex-1].description+"\n"+DASH);
    }

    public static void printList(){
        System.out.println(DASH+"\nHere are the tasks in your list:");
        for(int index = 0;index<Tasklist.lastIndex;index++){
            System.out.println(Integer.toString(index+1)+"."+(Tasklist.taskArray[index].description).trim());
        }
        System.out.println(DASH + "\n");
    }

    public static void acknowledgeTaskAdded(){
        Tasklist latestTask = Tasklist.taskArray[Tasklist.lastIndex-1];
        String totalNumberOfTasks=Integer.toString(Tasklist.lastIndex);
        System.out.println(DASH+"\nGot it. I've added this task:\n"+latestTask.description+"\nNow you have "+
                totalNumberOfTasks+" tasks in the list.\n"+DASH);
    }
}
