import Tasks.TaskList;
import UI.Conversation;

public class Duke {
    //run duke 1
    public static void main(String[] args) {
        Conversation.intro();
        boolean isContinue = true;
        TaskList.runDuke(isContinue);

    }
}
