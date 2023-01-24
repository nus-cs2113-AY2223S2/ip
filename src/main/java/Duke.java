import java.util.Scanner;
public class Duke {
    public static void greet() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm Duke\nWhat can I do for you?\n"
                + "____________________________________________________________\n");
    }

    public static void exit(){
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");
    }

    public static void echo(String input){
        System.out.println("____________________________________________________________\n"
                + input + "\n____________________________________________________________\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        greet();
        while (true){
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            echo(userInput);
        }
        exit();
    }
}
