package duke;
import duke.exceptions.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numOfTask = 0;
    // Method to list out the tasks stored in the task list
    public static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + "." +"[" + tasks.get(i-1).getType() + "]" + "[" + tasks.get(i-1).getStatusIcon() + "] "+ tasks.get(i-1).getDescription()
            + tasks.get(i-1).getDeadline() + tasks.get(i-1).getPeriod());
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
                case "delete":
                    return "delete";
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

     public static boolean shouldExit(String args) throws DukeException {
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
                 tasks.get(indexMark-1).markAsDone();
                 System.out.println("  [" + tasks.get(indexMark-1).getStatusIcon() + "] " + tasks.get(indexMark-1).getDescription());
                 return false;
             case "unmark":
                 int indexUnmark = Integer.parseInt(taskNumber);
                 tasks.get(indexUnmark).markAsUndone();
                 System.out.println("OK, I've marked this task as not done yet:");
                 System.out.println("  [" + tasks.get(indexUnmark-1).getStatusIcon() + "] " + tasks.get(indexUnmark-1).getDescription());
                 return false;
             case "todo":
                 try {
                     ToDo toDoTask = new ToDo(filterDescription(args));
                     numOfTask += 1;
                     tasks.add(toDoTask);
                     System.out.println(tasks.get(numOfTask-1));
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                 } catch (ToDoException e) {
                     System.out.println("The description of a todo cannot be empty!");
                 }
                 return false;
             case "deadline":
                 try {
                     String[] deadline = filterDescriptionAndDeadline(args);
                     Deadline deadlineTask = new Deadline(deadline[0], deadline[1]);
                     numOfTask += 1;
                     tasks.add(deadlineTask);
                     System.out.println(tasks.get(numOfTask - 1));
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                 }catch (StringIndexOutOfBoundsException e){
                     System.out.println("The description of deadline and/or date of deadline cannot be empty!");
                 }
                 return false;
             case "event":
                 try {
                     String[] event = filterDescriptionAndTimePeriod(args);
                     Event eventTask = new Event(event[0], event[1], event[2]);
                     numOfTask += 1;
                     tasks.add(eventTask);
                     System.out.println(tasks.get(numOfTask - 1));
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                 }catch(StringIndexOutOfBoundsException e){
                     System.out.println("The description of the event and/or period of it cannot be empty!");
                 }catch(EventException e){
                     System.out.println("To field is empty!");
                 }
                 return false;
             case "delete":
                 int index = Integer.parseInt(taskNumber);
                 System.out.println("Noted. I've removed this task");
                 System.out.println(index + "." +"[" + tasks.get(index-1).getType() + "]" + "[" + tasks.get(index-1).getStatusIcon() + "] "+ tasks.get(index-1).getDescription()
                         + tasks.get(index-1).getDeadline() + tasks.get(index-1).getPeriod());
                 tasks.remove(index-1);
                 numOfTask -= 1;
                 System.out.println("Now you have " + numOfTask + " task in the list.");
                 return false;
             default:
                 throw new DukeException();
         }
     }
    public static void main(String[] args) {
        Greetings welcome = new Greetings();
        System.out.println(welcome);
        try {
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            while (shouldExit(in) == false) {
                in = sc.nextLine();
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("This task number does not exist!");
        } catch (DukeException e){
            System.out.println("Sorry I don't understand what this means!");
        }
    }
}
