import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int currentIndex = 0;

        while (true) {
            String input;
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (String item : list){
                    if (item != null) {
                        System.out.println(item);
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                list[currentIndex] = (currentIndex + 1) + ". " + input;
                currentIndex++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
