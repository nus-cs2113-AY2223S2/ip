import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList listOfTasks;
    private UI ui;

    public Duke(String folderPath, String filePath) {
        ui = new UI();
        storage = new Storage(folderPath, filePath);
        try {
            listOfTasks = new TaskList(storage.initialiseDuke());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        UI.printHelloMessage();
        String input;
        do {
            ui.printHorizontalLine();
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            ui.printHorizontalLine();
            Parser.checkInput(input, listOfTasks.getTaskList());
        } while (!input.equals("bye"));
    }

    public void exit() {
        try {
            storage.saveTasks(listOfTasks.getTaskList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        ui.printGoodbyeMessage();
        ui.printHorizontalLine();
    }

    public static void main(String[] args) {
        String homePath = System.getProperty("user.home") + "/Desktop";
        String folderPath = homePath + "/data";
        String filePath = folderPath + "/duke.txt";
        Duke ChatBot = new Duke(folderPath, filePath);
        ChatBot.run();
        ChatBot.exit();
    }
}
