import java.util.List;
import java.util.Scanner;
public class Duke {
    public static List list=null;
    public static void main(String[] args) {
        String lineBreak = "-----------------";
        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n' + "What can I do for you?" + '\n' + lineBreak);
        Scanner myObj = new Scanner(System.in);
        String instruction = myObj.nextLine();
        if(instruction.equalsIgnoreCase("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }else if(instruction.equalsIgnoreCase("list")){
            System.out.println(list);
        }
    }
}
