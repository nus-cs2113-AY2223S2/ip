import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String barrier = "____________________________________________________________";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        System.out.println("Hello from\n" + logo);

        String greeting = barrier + "\nHello! I'm Duke\nWhat can I do for you?\n" + barrier + "\n";
        String exit = barrier + "\n\nBye. Hope to see you again soon!\n" + barrier;
        System.out.println(greeting);

        String input = console.nextLine();

        while(!input.equals("bye")){
            switch(input){
                case "list": {
                    System.out.println(barrier + "\n");
                    Task.printAllTasks();
                    System.out.println(barrier + "\n");
                    break;
                }
                default: {
                    System.out.println(barrier + "\n");
                    Task temp = new Task(input);
                    System.out.println("added: " + temp.printTask());
                    System.out.println(barrier + "\n");
                }
            }
            input = console.nextLine();
        }

        System.out.println(exit);
    }
}
