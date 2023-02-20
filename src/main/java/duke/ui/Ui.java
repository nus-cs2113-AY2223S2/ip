package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public static final String DIVIDER = "_______________________________________________________________";

    public static final String WELCOME = "\"Hello! I'm Duke\\nWhat can I do for you?\"";
    private final Scanner in;
    private final PrintStream out;

    public Ui(){
        this(System.in, System.out);
    }
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean canIgnore(String inputLine){
        return inputLine.trim().isEmpty();
    }

    public String getUserCommand(){
        out.print("Enter Command: ");
        String fullInputLine = in.nextLine();
        while(canIgnore(fullInputLine)){
            fullInputLine = in.nextLine();
        }
        showToUser("[Command entered: " + fullInputLine + "]");
        return fullInputLine;
    }

    public void showToUser(String ... message) {
        for (String m : message){
            out.println(m);
        }
        out.println(DIVIDER);
    }

    public void showWelcome(){
        showToUser(DIVIDER, DIVIDER, LOGO, DIVIDER, DIVIDER, WELCOME, DIVIDER);
    }
}
