import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        boolean isByeEntered = true;
        String outputs;

        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");

        while(isByeEntered) {
            Scanner scan = new Scanner(System.in);
            outputs = scan.nextLine();
            if(outputs.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }else{
            System.out.println(outputs);
            }

        }



    }
}
