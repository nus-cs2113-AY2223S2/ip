
import java.util.Scanner;
public class Duke {
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner ReadInput = new Scanner(System.in);

        Tasks[] task = new Tasks[100];
        int taskCount = 0;

        while(true){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){
                printLines();
                System.out.println("Bye. Hope to see you again soon!\n");
                printLines();
                System.exit(0);
            }

            else if(UserInput.equalsIgnoreCase("list")){
                printLines();
                int indexNum = 1;
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i<taskCount; i++){
                    System.out.println(indexNum + ". " + task[i]);
                    indexNum++;
                }
                printLines();
            }

            else if(UserInput.startsWith("mark")){
                int index = Integer.parseInt(UserInput.substring(5));
                task[index-1].MarkTask();
            }

            else if(UserInput.startsWith("unmark")){
                int index = Integer.parseInt(UserInput.substring(7));
                task[index-1].unMarkTask();
            }

            else if(UserInput.startsWith("todo")){
                printLines();
                String todoName = UserInput.substring(5);
                task[taskCount] = new Todo(todoName);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(task[taskCount-1]);
                System.out.println("\nNow you have " + taskCount + " tasks in the list.");
                printLines();
            }

            else if(UserInput.startsWith("deadline")){
                printLines();
                int indexOfBy =   UserInput.indexOf("/by");
                String deadlineName = UserInput.substring(9,indexOfBy-1);
                String by = UserInput.substring(indexOfBy+3);
                task[taskCount] = new Deadline(deadlineName, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(task[taskCount-1]);
                System.out.println("\nNow you have " + taskCount + " tasks in the list.");
                printLines();

            }

            else if(UserInput.startsWith("event")){
                printLines();
                int indexOfFrom = UserInput.indexOf("/from");
                int indexOfTo = UserInput.indexOf("/to");
                String eventName = UserInput.substring(6,indexOfFrom-1);
                String startTime = UserInput.substring(indexOfFrom+6,indexOfTo-1);
                String endTime = UserInput.substring(indexOfTo+4);
                task[taskCount] = new Event(eventName, startTime, endTime);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(task[taskCount-1]);
                System.out.println("\nNow you have " + taskCount + " tasks in the list.");
                printLines();
            }
        }
    }
}