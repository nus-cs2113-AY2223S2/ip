package corefunctionalities;

/**
 * This is the main Duke Class which contains the main program.
 *
 * @author Muthya Narayanachary Akhil
 *
 */
public class Duke {

    private TaskList taskList;
    private Ui ui;
    private FileHandler fileOject;
    private ExceptionHandler exceptionHandler;

    /**
     * Constructor for an object of type <code>Duke</code>. Instantiates the various attributes of Duke such as
     * {@link Ui} ,{@link FileHandler}, {@link ExceptionHandler} and {@link TaskList}
     */
    public Duke() {
        ui = new Ui();
        fileOject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");
        exceptionHandler = new ExceptionHandler();
        taskList = new TaskList(fileOject);
    }

    /**
     * Runs the main functionality of <code>Duke</code>.
     */
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

    /**
     * Main runnable function of Duke. Makes a call to {@link Duke#run()} to run the program
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
