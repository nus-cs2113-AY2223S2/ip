
import Onandon.command.*;
import Onandon.exception.*;
import Onandon.module.*;
import Onandon.ui.Ui;
import Onandon.checkpoint.Checkpoint;
import java.util.Scanner;

public class Onandon {
    private TaskList tasks;
    private ExceptionChecker error;

    public Onandon(){
        tasks = Checkpoint.recallCheckpoint();
        error = new ExceptionChecker();
    }

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
        Checkpoint.storeCheckpoint(tasks);
        Ui.printBye();
    }

    public static void main(String[] args) {
        new Onandon().run();
    }
}
