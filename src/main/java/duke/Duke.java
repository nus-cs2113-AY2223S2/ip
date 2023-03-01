package duke;

import duke.commands.Parser;
import duke.exceptions.IncorrectDeadlineException;
import duke.exceptions.IncorrectEventException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.commands.Ui;

public class Duke {
    public static String FILE = "./duke.txt";
    public static String DIRECTORY = "data";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            EditFile.checkFile();
            EditFile.loadFile(TaskList.getTaskList());
        } catch (FileNotFoundException e) {
            System.out.println("Folder not found");
        } catch (IOException e) {
            System.out.println("Incorrect input");
        }
        Ui.showWelcome();
        boolean isActive = true;
        while (isActive) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                Ui.showBye();
                isActive = false;
                break;
            }
            Parser.parseCommand(command);
        }
        try {
            EditFile.writetoFile(DIRECTORY + FILE, TaskList.getTaskList());
        } catch (IOException E) {
            System.out.println("Unable to safe file, please try again");
        }
        input.close();
    }
}


