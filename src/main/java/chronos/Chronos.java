package chronos;

import chronos.inoutput.Input;
import chronos.inoutput.Output;

import chronos.tasktype.Stash;

import chronos.userinterface.TaskManager;
import chronos.userinterface.UserHandler;

import java.io.File;
import java.io.IOException;


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
        fileInit();
        fileWriter();
        taskManager.inputCommands();
    }

    private static void fileInit() {
        File file = new File("./taskList.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileWriter() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                stash.saveTasksToFile();
            }
        });
    }
}