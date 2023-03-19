import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.Todo;
import duke.Parser;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();

        ui.showGreetingMessage();

        //Set up list to store user inputs
        ArrayList<Todo> tasks = new ArrayList<>();
        int counter = 0;

        //Initialize list with saved data if present on disk
        String absoluteFilePath = storage.findFilePath();
        counter = storage.initializeList(tasks, counter, absoluteFilePath);

        //Execute program
        parser.parse(tasks, counter);
    }
}
