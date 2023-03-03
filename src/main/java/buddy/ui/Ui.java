package buddy.ui;

import buddy.messages.Messages;
import buddy.storage.Storage;
import buddy.tasks.TaskList;

import java.io.FileNotFoundException;

public class Ui {

    /**
     * Prints a greeting to greet the user.
     */
    public static void greetUser(){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.GREETING);
        System.out.println(Messages.INTRODUCTION);
        System.out.println(Messages.DIVIDER);
    }

    /**
     * Prints the help message
     */
    public static void displayHelpMessage(){
        System.out.println(Messages.HELPMESSAGE);
    }

    /**
     * Prints the message when user types "bye" (Saying bye)
     */
    public static void sayByeToUser(){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.EXITMESSAGE);
        System.out.println(Messages.DIVIDER);
    }

    /**
     * Loads a file if there is an existing saved file with the list of tasks
     * If there is no existing file, create a new file
     *
     * @param taskList The list of tasks
     * @param storage  Storage object that handles the data
     */
    public static void loadFileOrCreateFile(TaskList taskList, Storage storage){
        try {
            storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            storage.createFile();
        }
    }
}
