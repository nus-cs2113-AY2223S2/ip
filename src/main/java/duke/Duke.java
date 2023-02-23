package duke;

import java.io.IOException;

import parser.DukeParser;
import tasklist.TaskList;

public class Duke {
    public boolean hasEnteredBye;
    private final boolean WITH_SCANNER = true;
    public DukeUi ui;
    public TaskList taskList;
    public SavefileManager savefileManager;
    public DukeParser parser;

    public Duke() {
        hasEnteredBye = false;
        ui = new DukeUi(WITH_SCANNER);
        savefileManager = new SavefileManager();
        parser = new DukeParser(this);
    }

    public void exit() {
        ui.printLine();
        savefileManager.save(taskList);
        ui.closeScanner();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.greetUser();
        try {
            duke.savefileManager.checkSaveDir();
            duke.savefileManager.checkSavefile();
            duke.taskList = duke.savefileManager.parseSavefile();
            duke.ui.printLine();
        } catch (IOException e) {
            System.out.println("Savefile cannot be found, please delete the savefile yourself");
            System.out.println("cos I am too lazy to delete it for you for now.");
            return;
        } catch (Exception e) {
            return;
        }
        
        while (!duke.hasEnteredBye) {
            String line = duke.ui.getNextLine();
            duke.parser.parseUserInput(line);
            duke.ui.printLine();
        }
        duke.ui.closeScanner();
    }
}
