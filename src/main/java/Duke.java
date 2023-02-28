import command.Command;
import exceptions.DeadlineParamsFormatException;
import exceptions.EventParamsFormatException;
import exceptions.FileLineParseException;
import exceptions.TaskIndexNotFoundException;
import task.*;
import parser.Parser;
import ui.Ui;
import utils.DukeFileReader;
import utils.DukeFileWriter;
import utils.Storage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(FileLineParseException e){
            ui.showFileLineParseException();
            tasks = new TaskList();
        }catch (FileNotFoundException e){
            ui.showFileNotFoundException();
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            finally{
                ui.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke("Data/duke.txt").run();
    }


//    static ArrayList<Task> tasksList = new ArrayList<Task>();
//
//    static String filePath = "Data/duke.txt";
//    static DukeFileReader dukeFileReader = new DukeFileReader(filePath);
//    static DukeFileWriter dukeFileWriter = new DukeFileWriter(filePath);


//    public static void main2(String[] args) {
//        sendLogo();
//        sendGreeting();
//
//        String initialInfo = initializeTaskFromFile(dukeFileReader);
//        showResultToUser(initialInfo);
//
//        while(true){
//            String userCommand = getUserInput();
//            if(userCommand.toLowerCase().equals("bye")){
//                break;
//            }
//            String feedback = executeCommand(userCommand);
//            showResultToUser(feedback);
//        }
//    }
}
