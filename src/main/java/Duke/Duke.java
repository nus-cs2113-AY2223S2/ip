package Duke;

import java.util.Scanner;

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

