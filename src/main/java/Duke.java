import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<String> todo = new ArrayList<String>();//Create an arraylist object to store commands

        while(true){
            String line = "";
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.equals("list")){
                for(int i = 0; i<todo.size(); i++){
                    System.out.println(String.valueOf(i+1)+". "+todo.get(i));
                }
            }
            else {
                todo.add(line);
                System.out.println("added: "+line);
            }
        }
    }

}
