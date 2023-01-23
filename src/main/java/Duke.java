import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Douph");
        System.out.println("What can I do for you?");
        System.out.println("list:");
        while(true){
            String line=in.nextLine();

            System.out.println(line);
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }



    }
}
