package buddy.ui;

import buddy.messages.Messages;
import buddy.storage.Storage;
import buddy.tasks.TaskList;

import java.io.FileNotFoundException;

public class Ui {

    public static void greetUser(){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.GREETING);
        System.out.println(Messages.INTRODUCTION);
        System.out.println(Messages.DIVIDER);
    }
    public static void displayHelpMessage(){
        System.out.println(Messages.HELPMESSAGE);
    }
    public static void sayByeToUser(){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.EXITMESSAGE);
        System.out.println(Messages.DIVIDER);
    }
    public static void loadFileOrCreateFile(TaskList taskList, Storage storage){
        try {
            storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            storage.createFile();
        }
    }
}
