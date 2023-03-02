package duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        run("duke/data/duke.txt");
    }

    public static void run(String path) {
        TaskList tasks = new TaskList(Storage.loadFile(path));
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        Ui.printHello();

        while (!isEnd) {
            String command = in.nextLine();
            isEnd = Parser.parseOriginalCommand(command, tasks, path);
        }

        Ui.printBye();
        in.close();
    }
}
