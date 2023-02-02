public class DukePrinter {
    private static String LINE = "____________________________________________________________";
    private static String DukeLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String EditorLogo =  " _____ ____ ___ _____ ___  ____      _     ___ _   _ __________   __ _    _   _  ____\n" +
                                  "| ____|  _ \\_ _|_   _/ _ \\|  _ \\ _  | |   |_ _| | | |__  /_ _\\ \\ / // \\  | \\ | |/ ___|\n" +
                                  "|  _| | | | | |  | || | | | |_) (_) | |    | || | | | / / | | \\ V // _ \\ |  \\| | |  _\n" +
                                  "| |___| |_| | |  | || |_| |  _ < _  | |___ | || |_| |/ /_ | |  | |/ ___ \\| |\\  | |_| |\n" +
                                  "|_____|____/___| |_| \\___/|_| \\_(_) |_____|___|\\___//____|___| |_/_/   \\_\\_| \\_|\\____|\n";
    private static String GoodbyeLogo =   "  ____  ___   ___  ____  ______   _______ _\n" +
                                    " / ___|/ _ \\ / _ \\|  _ \\| __ ) \\ / / ____| |\n" +
                                    "| |  _| | | | | | | | | |  _ \\\\ V /|  _| | |\n" +
                                    "| |_| | |_| | |_| | |_| | |_) || | | |___|_|\n" +
                                    " \\____|\\___/ \\___/|____/|____/ |_| |_____(_)\n";
    public static void printDukeLogo() { System.out.println("Hello from\n" + DukeLogo);}
    public static void printEditorLogo(){
        System.out.println(EditorLogo);
    }
    public static void printGreeting(){
        printDukeLogo();
        printEditorLogo();
        System.out.println("Hello! I'm Duke, edited by liuziyang");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void printGoodbyeLogo(){
        System.out.println(GoodbyeLogo);
        printLine();
    }
    public static void printStringln(String s){
        System.out.println(s);
        System.out.println(LINE);
    }
    public static void printString(String s){
        System.out.println(s);
    }
    public static void printErrorln(String s){
        System.err.println(s);
        System.out.println(LINE);
    }
    public static void printLine(){
        System.out.println(LINE);
    }
}
