package duke.main;

import duke.commands.Ui;
import duke.save.FileOperation;
import duke.tasks.TaskList;

import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class Duke {

    public static void main(String[] args) {
        try{
            FileOperation.initFile();
        } catch (IOException e){
            System.out.println("Error with hard disk!");
            exit(1);
        }


        Ui applicationProcess = new Ui();
        TaskList newTaskList = new TaskList();
        FileOperation.loadFile(newTaskList);
        applicationProcess.greet();
        applicationProcess.run(newTaskList);
        applicationProcess.sayBye();
    }
}