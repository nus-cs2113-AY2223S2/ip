import java.util.Scanner;
public class Rolex {

    static Task[] task =  new Task[100];
    static int taskCount = 0;
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void RolexGreetsUser(){
        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n");
    }

    public static void InputIsBye(){
        printLines();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLines();
    }

    public static void InputIsList(){
        printLines();
        int indexNum = 1;
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i<taskCount; i++){
            System.out.println(indexNum + ". " + task[i]);
            indexNum++;
        }
        printLines();
    }

    public static void InputIsMark(String UserInput){
        int index = Integer.parseInt(UserInput.substring(5));
        task[index-1].MarkTask();
    }

    public static void InputIsUnmark(String UserInput){
        int index = Integer.parseInt(UserInput.substring(7));
        task[index-1].unMarkTask();
    }

    public static void Add_Print_Task(){
        printLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(task[taskCount-1]);
        System.out.println("\nNow you have " + taskCount + " tasks in the list.");
        printLines();
    }

    public static void InputIsTodo(String UserInput){
        String todoName = UserInput.substring(5);
        task[taskCount] = new Todo(todoName);
        taskCount++;
        Add_Print_Task();
    }

    public static void InputIsDeadline(String UserInput){
        int indexOfBy = UserInput.indexOf("/by");
        String deadlineName = UserInput.substring(9,indexOfBy-1);
        String by = UserInput.substring(indexOfBy+3);
        task[taskCount] = new Deadline(deadlineName, by);
        taskCount++;
        Add_Print_Task();
    }

    public static void InputIsEvent(String UserInput){
        int indexOfFrom = UserInput.indexOf("/from");
        int indexOfTo = UserInput.indexOf("/to");
        String eventName = UserInput.substring(6,indexOfFrom-1);
        String startTime = UserInput.substring(indexOfFrom+6,indexOfTo-1);
        String endTime = UserInput.substring(indexOfTo+4);
        task[taskCount] = new Event(eventName, startTime, endTime);
        taskCount++;
        Add_Print_Task();
    }

    public static void main(String[] args) {

        RolexGreetsUser();
        Scanner ReadInput = new Scanner(System.in);

        while(true){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){
                InputIsBye();
                System.exit(0);
            }
            else if(UserInput.equalsIgnoreCase("list")){
                InputIsList();
            }
            else if(UserInput.startsWith("mark")){
                InputIsMark(UserInput);
            }
            else if(UserInput.startsWith("unmark")){
                InputIsUnmark(UserInput);
            }
            else if(UserInput.startsWith("todo")){
                InputIsTodo(UserInput);
            }
            else if(UserInput.startsWith("deadline")){
                InputIsDeadline(UserInput);
            }
            else if(UserInput.startsWith("event")){
                InputIsEvent(UserInput);
            }
            else{
                System.out.println("Invalid Input! Please Try Again!");
            }

        } //while() ends here

    } // main() ends here

} // Rolex class ends here