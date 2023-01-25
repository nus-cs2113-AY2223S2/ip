import java.util.Scanner;

public class Duke {
    public static void greetUser(){
         String divider = "______________________________";
         String greeting = divider + System.lineSeparator() + "What can I do for you?"
                 + System.lineSeparator() + divider + System.lineSeparator();
         System.out.println(greeting);
    }
    public static void echoUserInput(String curr){
        String divider = "______________________________";
        String echo = divider +System.lineSeparator() + curr + System.lineSeparator()
                + divider + System.lineSeparator();
        System.out.println(echo);
    }
    public static void sayByeToUser(){
        String divider = "______________________________";
        String bye = divider + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + divider + System.lineSeparator();
        System.out.println(bye);
    }
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        greetUser();
        boolean end = false;

        while(!end){
            input = in.nextLine();
            if(input.equals("bye")){
                end = true;
            }
            else{
                echoUserInput(input);
            }
        }
        sayByeToUser();
    }
}
