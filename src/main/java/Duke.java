import java.util.Scanner;
import commandProcessor.CommandProcessor;
import exceptions.DukeException;
import storage.TaskStorage;
import task.*;
import static task.TaskList.*;
import static ui.UI.*;
import static commandProcessor.CommandProcessor.*;

public class Duke {
    private static TaskList tasks;

    public Duke(String dataPath){
        TaskStorage.dataPath = dataPath;
        tasks = new TaskList();
        TaskStorage.loadSaveData();
    }

    /**
     * Run the program Duke to read and process the input given by user
     * @exception DukeException addTodo error and runCommand error
     * @see DukeException
     */
    public void run() {
        start();
        String input, command;
        boolean isRunning = true;
        while (isRunning) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            command = input.split(" ")[0];
            try {
                CommandProcessor.runCommand(input, command, tasks);
                isRunning = getIsRunningStatus();
            } catch (DukeException e) {
                printError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}
