public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        print_line();
        greet();
        print_line();
        say_bye();
        print_line();
    }
    public static void greet(){
        System.out.println("Hello! I am limey, What can I do for you?");
    }
    public static void say_bye() {
        System.out.println("Bye! Hope to see you again soon. :)");
    }
    public static void print_line() {
        System.out.println("____________________________________________________________");
    }
}
