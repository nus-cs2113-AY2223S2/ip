package IPChat;

import ipchatExceptions.IPChatExceptions;
import java.io.*;
import static IPChat.Parser.mySequence;
import static IPChat.Ui.start;

/**
 * Main class for activating and running the IPChat Box
 *
 * @author DeepanjaliDhawan
 */
public class IPChat {
    public static void main(String[] args) throws IPChatExceptions , IOException{
        start();
        mySequence();
    }
}
