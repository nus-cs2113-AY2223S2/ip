package corefunctionalities;// branch level-5 update as merge was not successful


/**
 * Incorrect Tags have been resolved, all commits have been tagged appropriately
 */
public class Duke {

    private TaskList taskList;
    private Ui ui;
    private FileHandler fileOject;
    private ExceptionHandler exceptionHandler;
    public Duke() {
        ui = new Ui();
        fileOject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");
        exceptionHandler = new ExceptionHandler();
        taskList = new TaskList(fileOject);
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            ui.readCommand();
            ui.nullChecker();
            exceptionHandler.execute(ui.userInput, taskList, fileOject);
            isExit = exceptionHandler.isExit();
        }
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
