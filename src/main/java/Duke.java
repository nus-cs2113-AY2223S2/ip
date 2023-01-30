import java.util.Scanner;
public class Duke{
    public static void main(String[] args){
        String dash = "__________________________________";
        String greet = dash+"\nHello I'm Duke\nWhat can I do for you?\n"+dash;
        System.out.println(greet);

        while(true){
            Scanner scan = new Scanner(System.in);
            String message= scan.nextLine();
            if(message.equals("bye")){
                System.out.println(dash+"\nBye. Hope to see you again soon!\n"+dash);
                break;
            }
            else{
                System.out.println(dash+"\n"+message+"\n"+dash);
            }
        }
    }
}


