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
        List list = new List();
        
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        
        while (!line.equalsIgnoreCase("bye")) {
            String horizontalLine = "____________________________________________________________\n";
            System.out.print(horizontalLine);
            
            if (line.equalsIgnoreCase("list")) {
                list.printList();
            } else if (line.startsWith("mark ")) {
                String[] words = line.split(" ");
                list.markTaskDone(Integer.parseInt(words[1]));
            } else if (line.startsWith("unmark ")) {
                String[] words = line.split(" ");
                list.markTaskUndone(Integer.parseInt(words[1]));
            } else {
                list.addList(line);
                System.out.println("added: " + line);
            }
            
            System.out.println(horizontalLine);
            line = in.nextLine();
        }
        
        exit();
    }
}
