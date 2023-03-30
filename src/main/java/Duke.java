import parser.DukeSession;
import utility.Ui;

/**
 * Represents the Main of the entire project, a chatbot named DUKE.
 * It is made simple to understand the flow of the whole project.
 */
public class Duke {

    /**
     * Calls the session to start and initialises the session.
     *
     * @param args Contains any information the user inputs when running the process in the terminal.
     */
    public static void main(String[] args) {
        DukeSession dukeSession = new DukeSession();
        Ui.printGreetings();
        dukeSession.setUpArrayList();
        dukeSession.execute();
    }
}

