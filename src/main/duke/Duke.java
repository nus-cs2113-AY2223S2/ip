package duke;

import duke.parser.Parser;
import duke.tasklist.DataManager;
import duke.ui.DukeMessages;
import duke.util.DukeException;

public class Duke {

    private final DataManager dm;
    private final DukeMessages ui;
    private final Parser parser;
    //    static private final HashMap<String, String> helpOutputs = new HashMap<>();

//    public static void generateHelp() {
//        helpOutputs.put("list", "");
//        helpOutputs.put("todo", "");
//        helpOutputs.put("deadline", "");
//        helpOutputs.put("event", "");
//        helpOutputs.put("mark", "");
//        helpOutputs.put("unmark", "");
//        helpOutputs.put("help", "");
//    }

//    public static void printHelp(String str) {
//        for (String i:helpOutputs.keySet()) {
//            System.out.println(i + ": " + helpOutputs.get(i));
//        }
//    }
    public Duke(String path) {
        ui = new DukeMessages();
        parser = new Parser(ui);
        dm = new DataManager(path, ui, parser);
        try {
            dm.initialize();
        } catch (DukeException e) {
            ui.printFileFatalError();
        }
        run();
    }

    public void run() {
        dm.run();
        do {
            String checkCmd = parser.run();
            if (checkCmd.equals("bye")) {
                break;
            }
            ui.printDiv();
            String next;
            try {
                next = parser.check();
            } catch (DukeException e) {
                ui.printError();
                continue;
            }
            dm.command(checkCmd, next);
            ui.printDiv();
        } while (true);
        ui.printBye();
    }

    public static void main(String[] args) {
        String path = "data\\tasks.txt";
        //        generateHelp();
        new Duke(path);
    }
}