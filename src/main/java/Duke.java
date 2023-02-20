import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a ChatBot that acts as a task tracker.
 */
public class Duke {
    private Storage storage;
    private TaskList listOfTasks;
    private UI ui;

    /**
     * Loads users saved tasks if any
     *
     * @param folderPath The folder where the user's saved file is kept.
     * @param filePath the file which contains the user's saved tasks.
     */
    public Duke(String folderPath, String filePath) {
        ui = new UI();
        storage = new Storage(folderPath, filePath);
        try {
            listOfTasks = new TaskList(storage.initialiseDuke());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Starts ChatBot and reads in user input
     */
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

    /**
     * Exits ChatBot and saves user's inputted task into a local file
     */
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
