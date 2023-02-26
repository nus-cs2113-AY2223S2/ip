import DukeFunctions.Storage;
import DukeFunctions.TaskList;
import DukeFunctions.Ui;
import DukeFunctions.Parser;
import Exceptions.DukeError;


import java.util.Scanner;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    //static ArrayList<Todo> TodoList = new ArrayList<>();


    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeError e) {
            ui.showError(e);
            tasks = new TaskList();
        }

    }

    public void run() throws DukeError {
        //System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？ (start)"); //becomes UI.welcome
        ui.welcome();
        boolean isExit = false;
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {

            //direct input to parser
            String input = scanner.nextLine();


            try {
                parser.parseCommand(input, tasks, ui);
                isExit = parser.isExit;
            } catch (DukeError e) {
                ui.showError(e);
            }

            //update file
            try {
                storage.save(tasks);
            } catch (DukeError e) {
                ui.showError(e);
            }

        }
        ui.bye();
    }


    public static void main(String[] args) throws DukeError {
        new Duke("memory.txt").run();

    }
}
