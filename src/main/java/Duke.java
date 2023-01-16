public class Duke {

    public static void PrintBorder(){
        System.out.println("____________________________________________________________");
    }

    public static void Exit(){
        System.out.println("Bye. Hope to see you again soon!");
        PrintBorder();
    }
    public static void Greet(){
        PrintBorder();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        PrintBorder();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        Exit();
    }
}
