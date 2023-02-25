package keqing;

import keqing.exceptions.IllegalInputException;
import keqing.tasks.Deadline;
import keqing.tasks.Event;
import keqing.tasks.Task;
import keqing.tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Scanner;

import static keqing.tasks.Task.getTaskCount;

public class Keqing {

    public static final String LINE = "____________________________________________________________\n";

    public static void loopCommand() {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while(!text.equals("bye")){
            try {
                KeqingParser.doCommand(text);
            } catch (IllegalInputException e) {
                System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator()
                        + "if you need more instruction, please type 'menu'."+ System.lineSeparator() + LINE);
            }
            text = in.nextLine();
        }
    }


    public static void main(String[] args) throws IOException {
        KeqingUI.printStartingGreet();
        try {
            KeqingArrayList.tasks = KeqingStorage.loadFile();
        } catch (IOException e) {
            System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator());
            KeqingStorage.deleteStorage();
        }
        loopCommand();
        KeqingUI.printExitingGreet();
    }
}
