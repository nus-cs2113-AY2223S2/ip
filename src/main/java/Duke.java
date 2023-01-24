import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static String[] tasks = new String[100];
    private static String[] taskStatus = new String[100];
    private static int numOfTask = 0;
    //Method to Add to the list of task
    public static void addTask(String args){
        numOfTask += 1;
        tasks[numOfTask-1] = args;
        taskStatus[numOfTask-1] = " ";
        System.out.println("added: " + args);
    }

    public static void listTask(String[] args){
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + "." + "[" + taskStatus[i-1] + "] "+ args[i-1]);
        }
    }

    public static String filter(String sentence){
        String[] words = sentence.split(" ");
        for(String word: words){
            switch(word){
                case "bye":
                    return "bye";
                case "list":
                    return "list";
                case "mark":
                    return "mark";
                case "unmark":
                    return "unmark";
                default:
            }
        }
        return sentence;
    }
/*
    public static void processCommand(String args) {
        int dividerPosition = args.indexOf(" ");
        String command = args.substring(0, dividerPosition);
        command.trim();
        String taskNumber = args.substring(dividerPosition + 1, args.length());
        int index = Integer.parseInt(taskNumber);

    }

 */
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
         String command = filter(args);
         int dividerPosition = args.indexOf(" ");
         String taskNumber = args.substring(dividerPosition + 1, args.length());
         switch(command){
             case "bye":
                 System.out.println("Bye. Hope to see you again soon!");
                 return true;
             case "list":
                 listTask(tasks);
                 return false;
             case "mark":
                 int indexMark = Integer.parseInt(taskNumber);
                 taskStatus[indexMark - 1] = "X";
                 System.out.println("Nice! I've marked this task as done:");
                 System.out.println("  [" + taskStatus[indexMark-1] + "] " + tasks[indexMark-1]);
                 return false;
             case "unmark":
                 int indexUnmark = Integer.parseInt(taskNumber);
                 taskStatus[indexUnmark -1] = " ";
                 System.out.println("OK, I've marked this task as not done yet:");
                 System.out.println("  [" + taskStatus[indexUnmark-1] + "] " + tasks[indexUnmark-1]);
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
