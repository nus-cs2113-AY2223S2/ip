import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" + 
        "Hello! I'm Duke\n"+
        "What can I do for you?\n"
   );
   String input = sc.next();
   while (!input.equals("bye"))
   {
    System.out.println("____________________________________________________________\n" + "  " + input + "\n" + "____________________________________________________________\n");
    input = sc.next();;

        
    }
    System.out.println("____________________________________________________________\n" + "  " + "Bye. Hope to see you again soon!" + "\n" + "____________________________________________________________\n");
}
}
