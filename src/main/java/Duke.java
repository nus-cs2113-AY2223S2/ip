import User.Parser;
import User.Storage;
import User.TaskList;
import User.UI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {
        try{
            TaskList.taskList = Storage.readFile("duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, a new one will be created shortly!");
        }
        UI.printLogo();
        Scanner myObj = new Scanner(System.in);
        String userInput;
        userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            Parser.parsing(userInput);
            System.out.println("What would you like to do?");
            userInput = myObj.nextLine();
        }
        Storage.clearFile();
        Storage.updateFile(TaskList.taskList);
        UI.printBye();
    }
}