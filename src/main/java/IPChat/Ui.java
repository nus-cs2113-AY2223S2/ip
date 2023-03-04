package IPChat;
import ipchatExceptions.IPChatExceptions;
import java.io.FileNotFoundException;
import static IPChat.Storage.loadContent;

/**
 * Class to interact with the user
 *
 * @author DeepanjaliDhawan
 */
public class Ui {
    public static String line = "------------------------------------------------------------------------------------------\n";

    /**
     * Methods which starts the program
     * @throws IPChatExceptions throws exception if the file is not found
     */
    public static void start() throws IPChatExceptions {
        try {
            loadContent();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find Saved file. Sorry!");
        }
    }
}
