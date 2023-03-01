import java.util.Scanner;
import commandParser.ParseCommand;
import exceptions.DukeException;
import storage.TaskStorage;
import task.*;
import static task.TaskList.*;
import static ui.UI.*;
import static commandParser.ParseCommand.*;

public class Duke {
    private static TaskList tasks;

    public Duke(String dataPath){
        TaskStorage.dataPath = dataPath;
        tasks = new TaskList();
        TaskStorage.loadSaveData();
    }

    public void run() {
        start();
        String input, command;
        boolean isRunning = true;
        while (isRunning) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            command = input.split(" ")[0];
            try {
                ParseCommand.runCommand(input, command, tasks);
                isRunning = getisRunningStatus();
            } catch (DukeException e) {
                printError(e);
            }

        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}
