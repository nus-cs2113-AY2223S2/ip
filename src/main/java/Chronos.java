import inoutput.Input;
import inoutput.Output;
import tasktype.Stash;
import userinterface.TaskManager;
import userinterface.UserHandler;

public class Chronos {
    //dummy commit to level-6 branch
    private static Input inOut;
    private static Stash stash;
    private static TaskManager taskManager;
    private static UserHandler userHandler;
    public static void main(String[] args) {
        inOut = new Input();
        stash = new Stash();
        taskManager = new TaskManager(inOut, stash);
        userHandler = new UserHandler(inOut);
        Output.printWelcome();
        Output.printHelp();
        userHandler.getUserName();
        taskManager.inputCommands();
    }
}

