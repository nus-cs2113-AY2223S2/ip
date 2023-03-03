
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Buddy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public static int taskCount = 0;



    public static void main(String[] args){
        TaskList taskList = new TaskList();
        try {
            Storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            Storage.createFile();
        }

        Ui.greetUser();
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        Parser processAllCommands = new Parser();

        while (! processAllCommands.isExit(input)) {
            processAllCommands.executeInput(taskList, input);
            input = in.nextLine();
        }
        Ui.sayByeToUser();
    }
}
