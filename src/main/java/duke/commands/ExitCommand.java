package duke.commands;

import duke.tasks.Bye;

public class ExitCommand extends Command{
    public void execute(){
        Bye bye = new Bye();
        bye.printBye();
    }
}
