import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the main program. It contains the Storage class to load existing data from a
 * text file and save the data back within the file as the user terminates the program.
 * The instantiation of the Ui class is to facilitate user interaction while the TaskList class
 * is to function as the overall task manager for the user.
 */

public class Duke {
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        try {
            Storage.loadFile(taskList, "duke.txt");
            System.out.println("File loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found! The file will be created on termination of this program!");
        }

        Ui.printLogo();
        Ui.greetUser();
        Parser parser = new Parser();
        boolean isFinished = false;
        while (!isFinished) {
            String command = Ui.getUserCommand();
            parser.parse(command, taskList);
            isFinished = parser.checkProgrammeCompletion();
        }

        try {
            Storage.clearFile();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        Storage.updateFile(taskList);
        Ui.sayByeToUser();
    }
}
