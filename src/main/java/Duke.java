import java.util.Scanner;

public class Duke {
    public static boolean continueRunning = true;

    public Duke(String filePath){
        FileSaver fileSaver = new FileSaver(filePath);
        fileSaver.load();
    }
    public static void main(String[] args) {
        new Duke ("./data/taskList.txt").run();
    }

    public static void run() {
        UI.greet();
        while (continueRunning) {
            Scanner newScanner = new Scanner(System.in);
            String userInput = newScanner.nextLine();
            String[] userInputSplit = userInput.split(" ", 2);
            Parser controller = new Parser(userInputSplit);
            controller.processInput();
        }
    }
}


