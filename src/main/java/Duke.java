import parser.DukeSession;
import utility.Ui;

public class Duke {
    public static void main(String[] args) {
        DukeSession dukeSession = new DukeSession();
        Ui.printGreetings();
        dukeSession.setUpArrayList();
        dukeSession.execute();
    }
}

