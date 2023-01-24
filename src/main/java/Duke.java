import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    private static String[] Tasks = new String[100];
    private static int numOfTask = 0;
    //Method to Add to the list of task
    public static void addTask(String args){
        numOfTask += 1;
        Tasks[numOfTask-1] = args;
        System.out.println("added: " + args);
    }

    public static void listTask(String[] args){
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + ". " + args[i-1]);
        }
    }
    /*
    public static Boolean Exit(String args){
        if(args.equals("bye")){
            return true;
        }else{
            return false;
        }
    }
     */
     public static boolean actionCommand(String args){
         switch(args){
             case "bye":
                 System.out.println("Bye. Hope to see you again soon!");
                 return true;
             case "list":
                 listTask(Tasks);
                 return false;
             default:
                 addTask(args);
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
        String in = sc.nextLine();

        while(actionCommand(in)==false) {
            in = sc.nextLine();
        }
    }
}
