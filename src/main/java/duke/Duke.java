package duke;

import duke.boundary.Ui;
import duke.controller.TaskList;
import duke.model.Command;
import duke.utils.Parser;
import duke.utils.Storage;

import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Storage dataAccess = new Storage("data/duke.txt");
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        ui.printDuke();
        String input;
        while (true) {
            input = scanner.nextLine();
            Command command = Parser.parse(input);
            ui.readCommand(command);
            dataAccess.writeToFile("\t" + taskList.toString());
        }

    }


}
