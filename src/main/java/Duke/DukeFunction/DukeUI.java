package Duke.DukeFunction;

import java.util.Scanner;

public class DukeUI {
    private static final String LINE = "____________________________________________________________";
    private static final String DukeLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EditorLogo =  " _____ ____ ___ _____ ___  ____      _     ___ _   _ __________   __ _    _   _  ____\n" +
                                  "| ____|  _ \\_ _|_   _/ _ \\|  _ \\ _  | |   |_ _| | | |__  /_ _\\ \\ / // \\  | \\ | |/ ___|\n" +
                                  "|  _| | | | | |  | || | | | |_) (_) | |    | || | | | / / | | \\ V // _ \\ |  \\| | |  _\n" +
                                  "| |___| |_| | |  | || |_| |  _ < _  | |___ | || |_| |/ /_ | |  | |/ ___ \\| |\\  | |_| |\n" +
                                  "|_____|____/___| |_| \\___/|_| \\_(_) |_____|___|\\___//____|___| |_/_/   \\_\\_| \\_|\\____|\n";
    private static final String GoodbyeLogo =   "  ____  ___   ___  ____  ______   _______ _\n" +
                                    " / ___|/ _ \\ / _ \\|  _ \\| __ ) \\ / / ____| |\n" +
                                    "| |  _| | | | | | | | | |  _ \\\\ V /|  _| | |\n" +
                                    "| |_| | |_| | |_| | |_| | |_) || | | |___|_|\n" +
                                    " \\____|\\___/ \\___/|____/|____/ |_| |_____(_)\n";
    public Scanner in;
    public DukeUI() {
        in = new Scanner(System.in);
    }
    public String readCommand() {
        return in.nextLine();
    }
    public void printDukeLogo() { System.out.println("Hello from\n" + DukeLogo);}
    public void printEditorLogo(){
        System.out.println(EditorLogo);
    }
    public void printGreeting(){
        printDukeLogo();
        printEditorLogo();
        System.out.println("Hello! I'm DukeRobot, edited by liuziyang");
        System.out.println("What can I do for you?");
        printLine();
    }
    public void printGoodbyeLogo(){
        System.out.println(GoodbyeLogo);
    }
    public void printString(String s){
        System.out.println(s);
    }
    public void printError(String s){
        System.out.println("[>Error] " + s);
    }
    public void printLine(){
        System.out.println(LINE);
    }
}
