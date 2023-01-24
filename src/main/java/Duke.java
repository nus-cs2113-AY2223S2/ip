import java.util.Scanner;

public class Duke {
    
    public static void greet() {
        String greet = "____________________________________________________________\n" +
                "Hello! I'm Bob\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);
    }
    
    public static void exit() {
        String exit = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exit);
    }
    public static void main(String[] args) {
        greet();
        
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.matches("bye")) {
            String horizontalLine = "____________________________________________________________\n";
            System.out.println(horizontalLine + line + '\n' + horizontalLine);
            line = in.nextLine();
        }
        
        exit();
    }
}
