import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    final static String FILE_PATH = "data/duke.txt";
    private static Ui ui = new Ui();
    private static TaskList taskList;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String userInput;
        Scanner in = new Scanner(System.in);
        try {
            taskList = Storage.retrieveExistingTasksFromFile(FILE_PATH);
            System.out.println("File Found and successfully read");
            ui.executeListCommand(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            isContinue = ui.executeUserCommand(taskList, userInput);
        }
    }
}





