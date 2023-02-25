import Ui.Print;
import duke.Task;
import Parser.Parser;
import java.util.ArrayList;
import java.util.Scanner;
import Storage.Storage;

import javax.annotation.processing.Processor;

public class Duke {
    public final static String filePath = "Duke.txt";

    public static void main(String[] args) throws Exception {

        ArrayList<Task> tasks = new ArrayList<>();

        //If file exists, load file contents into the session

        int i = 0;
        Storage Storage;
        Storage = new Storage(filePath);
        i=Storage.createFile(tasks,filePath, i);


        Print Print = new Print("");
        Print.Greeting();

        Scanner in = new Scanner(System.in);
        int index_for_mark = 1;

        while (true) {

            String line = in.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                Print.Bye();
                break;
            }

            //process input and obtain current index
            i=Parser.Processor(filePath,line,tasks, i);



        }

    }
}


