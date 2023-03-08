import java.io.IOException;
import java.util.Scanner;

import static messages.operationalMessages.MESSAGE_OPENING;

public class Duke {

    public static void main(String[] args) throws IOException {

        Storage storage = new Storage();
        Ui ui = new Ui();

        storage.loadFromFile(TaskList.items);

        System.out.println(MESSAGE_OPENING);
        Scanner response = new Scanner(System.in);
        String line = "";

        ui.readInCommand(TaskList.items, response, line);
    }
}