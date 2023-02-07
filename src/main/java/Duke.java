import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numOfTask = 0;
    // Method to list out the tasks stored in the task list
    public static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + "." +"[" + tasks[i-1].getType() + "]" + "[" + tasks[i-1].getStatusIcon() + "] "+ tasks[i-1].getDescription()
            + tasks[i-1].getDeadline() + tasks[i-1].getPeriod());
        }
    }

    // Method used to filter the command word out of the input sentence
    public static String filterCommand(String sentence){
        String[] words = sentence.split(" ");  // splits into words
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
                case "todo":
                    return "todo";
                case "deadline":
                    return "deadline";
                case "event":
                    return "event";
                default:
            }
        }
        return sentence;
    }

    // Method to remove the command word from the input and return the description
    public static String filterDescription(String sentence) {
        String command = filterCommand(sentence);
        String description = sentence.replaceAll(command,"");
        return description.trim();
    }
    // Method to remove the command word, deadline and return the description
    public static String[] filterDescriptionAndDeadline(String sentence){
        String[] output = new String[2];
        String str = filterDescription(sentence);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0,dividerPosition-1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1,str.length());
        String byDate = deadline.replaceAll("by", "");
        output[1] = byDate.trim();
        return output;
    }
    // Method to remove the command word, time period and return the description
    public static String[] filterDescriptionAndTimePeriod(String sentence){
        String[] output = new String[3];
        String str = filterDescription(sentence);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0,dividerPosition-1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1,str.length());
        String fromPeriod = deadline.replaceAll("from", "");
        String fromDate = fromPeriod.substring(0,fromPeriod.indexOf("/"));
        output[1] = fromDate.trim();
        String toPeriod = fromPeriod.substring(fromPeriod.indexOf("/")+1, fromPeriod.length());
        String toDate = toPeriod.replaceAll("to", "");
        output[2] = toDate.trim();
        return output;
    }

     public static boolean shouldExit(String args){
         String command = filterCommand(args);
         int dividerPosition = args.indexOf(" ");
         String taskNumber = args.substring(dividerPosition + 1, args.length());  //Used for mark and unmark command
         switch(command){
             case "bye":
                 System.out.println("Bye. Hope to see you again soon!");
                 return true;
             case "list":
                 listTasks();
                 return false;
             case "mark":
                 int indexMark = Integer.parseInt(taskNumber);
                 tasks[indexMark-1].markAsDone();
                 System.out.println("Nice! I've marked this task as done:");
                 System.out.println("  [" + tasks[indexMark-1].getStatusIcon() + "] " + tasks[indexMark-1].getDescription());
                 return false;
             case "unmark":
                 int indexUnmark = Integer.parseInt(taskNumber);
                 tasks[indexUnmark-1].markAsUndone();
                 System.out.println("OK, I've marked this task as not done yet:");
                 System.out.println("  [" + tasks[indexUnmark-1].getStatusIcon() + "] " + tasks[indexUnmark-1].getDescription());
                 return false;
             case "todo":
                 numOfTask += 1;
                 tasks[numOfTask-1] = new ToDo(filterDescription(args));
                 System.out.println(tasks[numOfTask-1]);
                 System.out.println("Now you have " + numOfTask + " task in the list.");
                 return false;
             case "deadline":
                 numOfTask += 1;
                 String[] deadline = filterDescriptionAndDeadline(args);
                 tasks[numOfTask-1] = new Deadline(deadline[0],deadline[1]);
                 System.out.println(tasks[numOfTask-1]);
                 System.out.println("Now you have " + numOfTask + " task in the list.");
                 return false;
             case "event":
                 numOfTask += 1;
                 String[] event = filterDescriptionAndTimePeriod(args);
                 tasks[numOfTask-1] = new Event(event[0],event[1],event[2]);
                 System.out.println(tasks[numOfTask-1]);
                 System.out.println("Now you have " + numOfTask + " task in the list.");
                 return false;
             default:
                 Task t = new Task(args);
                 numOfTask += 1;
                 tasks[numOfTask-1] = t;
                 System.out.println(t);
                 return false;
         }
     }
    public static void main(String[] args) {
        Greetings welcome = new Greetings();
        System.out.println(welcome);

        Scanner sc= new Scanner(System.in);
        String in = sc.nextLine();

        while(shouldExit(in)==false) {
            in = sc.nextLine();
        }
    }
}
