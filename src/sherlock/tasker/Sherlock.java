import commands.Command;
import data.TasksList;
import data.exceptions.SherlockException;
import storage.Storage;
import ui.Ui;

public class Sherlock {
    private Storage storage;
    private TasksList tasksList;
    private Ui ui;
    public Sherlock(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TasksList(storage.loadTasks(ui));
        } catch (SherlockException e) {
            ui.showError(e.getMessage());
            tasksList = new TasksList();
        }
    }
//    public static void main(String[] args) {
//        TasksList tasksList = new TasksList();
//        Storage tasksLoader = new Storage(tasksList);
//        tasksLoader.loadTasks();;
//
//        TaskListener.greet();
//
//
//        TaskListener taskListener = new TaskListener(tasksList, tasksLoader);
//        taskListener.listen();
//    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser();
                Command c = parser.parse(fullCommand);
                c.execute(tasksList, ui, storage);
                isExit = c.isExit();
            } catch (SherlockException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Sherlock("data/tasks.txt").run();
    }

}
