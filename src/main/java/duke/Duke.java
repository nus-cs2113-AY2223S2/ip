package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {


    private Storage store;
    private Ui ui;
    private TaskList arrayLL;
    private Parser parser;

    public Duke() {
        this.store = new Storage();
        this.ui = new Ui();
        this.arrayLL = new TaskList();
        this.parser = new Parser(ui, store, arrayLL);
    }

    public static void main(String[] args) throws IOException {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String line;

        Task[] tasks = new Task[100];
        Duke duke = new Duke();
        duke.ui.printStart();
        Scanner sc = new Scanner(System.in);
        /**
         * This is the main function
         */
        while (true) {
            System.out.println("Please enter items you want to add to the list, if you want to quit enter bye");
            System.out.println("");
            System.out.print("Enter Your command: ");
            duke.parser.checkText("",0);
            line = sc.nextLine();
            System.out.println("");
            if (line.equals("bye") || line.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }
            duke.parser.checkText(line, 1);
        }
    }
}


