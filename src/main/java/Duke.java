import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println ("____________________________________________________________");
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");
        System.out.println ("____________________________________________________________");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String exit = "bye";
        String list = "list";
        Task tasks[ ]= new Task[100];
        int count = 0;

        while( !(input.equalsIgnoreCase (exit)) && !(input.isEmpty()) ) {
            if(input.equalsIgnoreCase (list)){
                System.out.println ("____________________________________________________________");
                System.out.println ("Here are the tasks in your list:");
                for(int i=0;i<count;i++){
                    System.out.println ((i+1) + ". [" + tasks[i].getStatusIcon () +"] "+ tasks[i].description);
                }
                System.out.println ("____________________________________________________________");
            }else if(input.startsWith ("mark") || input.startsWith ("unmark")){
                String[] arrInput = input.split(" ", 2);
                int index = Integer.parseInt (arrInput[1]);
                index -=1;
                if(input.startsWith ("mark")){
                    tasks[index].isDone = true;
                }else {
                    tasks[index].isDone = false;
                }
                System.out.println ("____________________________________________________________");
                System.out.println ("Nice! I've marked this task as done:");
                System.out.println ("  [" + tasks[index].getStatusIcon () + "] "+ tasks[index].description);
                System.out.println ("____________________________________________________________");
            }else{
                System.out.println ("____________________________________________________________");
                System.out.println ("added: " + input);
                tasks[count] = new Task(input);
                count++;
                System.out.println ("____________________________________________________________");
            }
            input = scan.nextLine ();
        }

        System.out.println ("____________________________________________________________");
        System.out.println ("Bye. Hope to see you again soon!");
        System.out.println ("____________________________________________________________");
    }
}


