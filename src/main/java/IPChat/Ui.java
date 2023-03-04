package IPChat;
import ipchatExceptions.IPChatExceptions;
import java.io.FileNotFoundException;
import static IPChat.Storage.loadContent;

public class Ui {
    public static String line = "------------------------------------------------------------------------------------------\n";
    public static void start() throws IPChatExceptions {
        try {
            loadContent();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find Saved file. Sorry!");
        }
    }
}
