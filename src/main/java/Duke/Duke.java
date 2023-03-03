package Duke;

import java.util.Scanner;

/**
 * The Duke program implements several operations
 * that enable the user to manage his tasks.
 *
 * @author Zeng Ziqiu
 * @since 21/01/2023
 * @version V2.0 (final version)
 */
public class Duke {

    public static void main(String[] args) {
        run("data/tasks.txt");
    }

    public static void run(String path) {
        TaskList tasks = new TaskList(Storage.loadFile(path));
        Scanner in = new Scanner(System.in);
        boolean isBye = false;

        Ui.printHello();

        while (!isBye) {
            String line = in.nextLine();
            isBye = Parser.processTask(line, tasks, path);
        }

        Ui.printBye();
        in.close();
    }
}

