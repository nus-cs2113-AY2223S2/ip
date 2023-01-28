
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");

        String input = myObj.nextLine();
        Todos[] todo = new Todos[100];
        int c = 0;
        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("-------TODO-LIST------");
                System.out.println("----------------------");
                for (int i = 0; i < todo.length && todo[i] != null; i++) {
                    char x = ' ';
                    if(todo[i].marked) {
                        x = 'X';
                    }
                    System.out.println(i+1 + ". " + "[" + x + "] " + todo[i].item);
                }
                System.out.println("----------------------");
            } else if (input.startsWith("mark")){
                int i = Integer.parseInt(input.substring(5));
                todo[i-1].setMark();
                System.out.println("Item has been marked");
                System.out.println(i + ". " + "[X] " + todo[i-1].item);
            } else if (input.startsWith("unmark")){
                int i = Integer.parseInt(input.substring(7));
                todo[i-1].unMark();
                System.out.println("Item has been unmarked");
                System.out.println(i + ". " + "[ ] " + todo[i-1].item);
            }

            else {
                todo[c] = new Todos(input, false);
                System.out.println("Added: " + input);
            }
            input = myObj.nextLine();
            c++;

        }
        System.out.println("Bye! see you soon!");
    }
}