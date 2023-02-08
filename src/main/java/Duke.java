import Tasks.TaskList;
import UI.Conversation;

public class Duke {
    //run duke
    public static void main(String[] args) {
        Conversation.intro();
        boolean isContinue = true;
        TaskList.runDuke(isContinue);

    }
}
