import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    //Data
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        try {
            Storage.loadFile(taskList, "duke.txt");
            System.out.println("File loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found! File will be created on termination");
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
