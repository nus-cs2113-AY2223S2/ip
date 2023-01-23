import java.util.Scanner;

public class Duke {

    public static void beginInput(){
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                return;
            }
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
    }
    public static void main(String[] args) {



        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        beginInput();

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
