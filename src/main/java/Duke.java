public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetLine();
        exitLine();
    }
    public static void greetLine(){
        System.out.println("How may I be of service?");
    }

    public static void exitLine(){
        System.out.println("Glad I could be of help!");
    }
}
