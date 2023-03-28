
import Onandon.command.*;
import Onandon.exception.*;
import Onandon.module.*;
import Onandon.ui.Ui;
import Onandon.storage.Storage;

/**
 * Main class of the Onandon chatbot.
 */
public class Onandon {
    private TaskList tasks;
    private ExceptionChecker error;

    public Onandon(){
        tasks = Storage.recallCheckpoint();
        error = new ExceptionChecker();
    }

    /**
     * Run the program.
     *
     * 1) Get the command from the user
     * 2) Parse the command and execute it.
     * 3) Print the right message.
     * 4) Repeat above process until 'exit' command is given by the user.
     */
    public void run(){
        Ui.printGreet();
        String fullCommand;

        while (true) {
            fullCommand = Ui.readCommand();
            try{
                error.checkException(fullCommand);
            }catch (OnandonException e){
                Ui.printException(e);
                continue;
            }
            Command c = Parser.parse(fullCommand);
            if(c.execute(tasks))
                break;
        }
        Ui.printBye();
    }

    public static void main(String[] args) {
        new Onandon().run();
    }
}
