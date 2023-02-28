import Ui.Print;
import duke.Task;
import Parser.Parser;
import java.util.ArrayList;
import java.util.Scanner;
import Storage.Storage;

public class Duke {

    public static void main(String[] args) throws Exception {

        ArrayList<Task> tasks = new ArrayList<>();

        //If file exists, load file contents into the session
        int i = 0;
        Storage Storage;
        Storage = new Storage();
        i=Storage.loadFromFile(tasks, i);

        Print Print = new Print();
        Print.Greeting();

        Scanner in = new Scanner(System.in);

        while (true) {

            String line = in.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                Print.Bye();
                break;
            }

            //Processes input and obtains the current index
            i=Parser.Processor(line,tasks, i);

        }

    }
}


