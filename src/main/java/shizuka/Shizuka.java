package shizuka;

import java.io.IOException;
import java.util.Scanner;

public class Shizuka {
    static boolean isExit = false;
    public static void main(String[] args) {
        UI.intro();
        Scanner in = new Scanner(System.in);
        String line;
        TodoList list0 = new TodoList();
        try {
            Storage.load(list0);
            UI.fileLoaded();
        } catch (IOException e) {
            UI.fileNotFound();
        }

        while (!isExit) {
            line = in.nextLine();
            Command c = Parser.parseCommand(line);
            c.execute(list0);
        }
        UI.exit();
    }
}
