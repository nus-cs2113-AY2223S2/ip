import utility.Methods;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        DukeSession dukeSession = new DukeSession();
        Methods.printGreetings();
        dukeSession.setUpArrayList();
        dukeSession.execute();
    }
}

