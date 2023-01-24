import java.util.Scanner;
public class Duke {
    public static Boolean Exit(String args){
        if(args.equals("bye")){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Mike\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner sc= new Scanner(System.in);
        String command = sc.nextLine();

        while(Exit(command)==false) {
            System.out.println(command);
            command = sc.nextLine();
        }
        
        System.out.println("Bye. Hope to see you again soon!");
    }
}
