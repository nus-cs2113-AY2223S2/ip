public class Duke {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String HorizontalRule = "__________________________________________________";


    public static void StartUp(){
        System.out.println(logo);
        System.out.println(HorizontalRule);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        System.out.println(HorizontalRule);
    }

    public static void ShutDown(){
        System.out.println(HorizontalRule);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HorizontalRule);
    }

    public static void main(String[] args) {
        StartUp();
        ShutDown();
    }
}
