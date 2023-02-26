import Interface.Parser;
import Interface.Ui;
import Interface.Storage;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        try {
            Storage.initFile();
        } catch(IOException e) {
            System.out.println("Error while trying to access save file");
        }
        Ui.intro();
        Parser.runDude(true);

    }
}
