import Tasks.TaskList;
import UI.Conversation;
import UI.FileAccess;

import java.io.IOException;

public class Duke {
    //run duke 1
    public static void main(String[] args) {
        try {
            FileAccess.initFile();
        } catch(IOException e) {
            System.out.println("Error in accessing save file");
        }

        Conversation.intro();
        boolean isContinue = true;
        TaskList.runDuke(isContinue);

    }
}
