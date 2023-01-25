import java.util.Scanner;

public class Duke {
    public static void greetUser(){
         String divider = "______________________________";
         String greeting = divider + System.lineSeparator() + "Hello! I'm Alex"
                 + System.lineSeparator() + "What can I do for you?"
                 + System.lineSeparator() + divider + System.lineSeparator();
         System.out.println(greeting);
    }
    public static void echoUserInput(String curr){
        String divider = "______________________________";
        String echo = divider +System.lineSeparator() + "added: " + curr + System.lineSeparator()
                + divider + System.lineSeparator();
        System.out.println(echo);
    }
    public static void sayByeToUser(){
        String divider = "______________________________";
        String bye = divider + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + divider + System.lineSeparator();
        System.out.println(bye);
    }
    public static void addToList(String[] tasks, String curr, int taskSum){
        tasks[taskSum] = curr;
    }
    public static void printList(String[] tasks, int taskSum){
        String divider = "______________________________";
        System.out.println(divider);
        for (int i = 0; i < taskSum; i++){
            System.out.println((i+1) + ": " + tasks[i]);
        }
        System.out.println(divider);
    }
    public static void main(String[] args) {
        String input;
        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);
        greetUser();
        int taskSum = 0;
        boolean end = false;

        while(!end){
            input = in.nextLine();
            if(input.equals("bye")){
                end = true;
            } else if(input.equals("list")){
                /*System.out.println("______________________________");
                for (int i = 0; i < taskSum; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }*/
                printList(tasks, taskSum);
            } else{
                echoUserInput(input);
                //add to an array.
                //tasks[taskSum] = input;
                addToList(tasks, input, taskSum);
                taskSum++;
            }
        }
        sayByeToUser();
    }
}
