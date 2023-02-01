import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        String userInput;
        while(in.hasNext()){

            userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            if(userInput.equals("list")){
                for(int i = 0; i<taskList.size(); i++){
                    System.out.print(i+1+".");
                    System.out.println(taskList.get(i).markTask()+taskList.get(i).name);
                }
            } else if(userInput.contains("unmark") || userInput.contains("mark")){
                Integer itemNumber = new Integer(0);
                String [] IndexArr = userInput.split(" ",2);
                itemNumber = Integer.parseInt(IndexArr[1])-1;
                if (userInput.contains("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    taskList.get((int) itemNumber).isCompleted = false;
                    System.out.println(taskList.get(itemNumber).markTask() + taskList.get(itemNumber).name);
                }
                else {
                    System.out.println("Nice! I've marked this task as done:");
                    taskList.get(itemNumber).isCompleted = true;
                    System.out.println(taskList.get(itemNumber).markTask() + taskList.get(itemNumber).name);
                }
            } else {
                taskList.add(taskList.size(), new Task(userInput,false));
                System.out.println("added: "+userInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}