import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" + 
        "Hello! I'm Duke\n"+
        "What can I do for you?\n"
   );
   ArrayList<String> items = new ArrayList<String>();
   String input = "";
   while (!input.equals("bye"))
   {
    input = sc.next();
    System.out.println("____________________________________________________________\n" + "  " + "added: "+ input + "\n" + "____________________________________________________________\n");
    if (!input.equals("list")) {
        items.add(input);
    }
    else {
        System.out.println("____________________________________________________________\n");
        for(int i = 1; i <= items.size(); i++){
            System.out.println(" " + i + ". " + items.get(i-1));
        }
        System.out.println("\n");
        System.out.println("____________________________________________________________\n");
    }
   
    }
    
    
        System.out.println("____________________________________________________________\n" + "  " + "Bye. Hope to see you again soon!" + "\n" + "____________________________________________________________\n");
}
}
