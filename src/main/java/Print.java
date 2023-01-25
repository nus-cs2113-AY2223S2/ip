public class Print {
    private static String LINE = "____________________________________________________________";
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







    public static void PrintEditor(){
        System.out.println(EditorLogo);
    }
    public static void PrintGoodbye(){
        System.out.println(GoodbyeLogo);
        PrintLine();
    }
    public static void PrintString(String s){
        System.out.println(s);
        System.out.println(LINE);
    }
    public static void PrintLine(){
        System.out.println(LINE);
    }
}
