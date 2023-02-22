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
        String jarPath = System.getProperty("java.class.path");
        File jarFile = new File(jarPath);
        String jarDirectory = jarFile.getParent();
        File chronosDirectory = new File(jarDirectory + File.separator + ".Chronos");

        if (!chronosDirectory.exists()){
            if (!chronosDirectory.mkdir()){
                System.err.println("Failed to create directory");
                return;
            }
        }
        File listFile = new File(chronosDirectory.getPath() + File.separator+ "taskList.txt");
        try {
            if(listFile.createNewFile()){
                System.out.println("Created taskList.txt file");
            }
            else {
                System.err.println("taskList.txt file already exists");
            }
        } catch (IOException e) {
            System.err.println("Failed to create taskList.txt file: " + e.getMessage());
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

