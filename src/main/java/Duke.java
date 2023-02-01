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
            if(userInput.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(i + 1 + ".");
                    System.out.println(taskList.get(i).toString());
                }
            } else if (userInput.contains("todo")) {
                String info = userInput.substring(5).trim();
                taskList.add(new Todo(info));
                int finalTask = taskList.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.get(finalTask).toString());
                if (taskList.size() == 1){
                    System.out.println("Now you have " +taskList.size()+" task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            } else if (userInput.contains("deadline")) {
                String [] listArray = userInput.split("/",2);
                String description = listArray[0];
                String dueDate = listArray[1];
                String info = description.substring(8).trim();
                String due = dueDate.substring(3).trim();
                taskList.add(new Deadline(info, due));
                int finalTask = taskList.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.get(finalTask).toString());
                if (taskList.size() == 1){
                    System.out.println("Now you have " +taskList.size()+" task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            } else if (userInput.contains("event")) {
                String [] listArray = userInput.split("/",3);
                String description = listArray[0];
                String startTime = listArray[1];
                String endTime = listArray[2];
                String info = description.substring(6).trim();
                String start = startTime.substring(5).trim();
                String end = endTime.substring(3).trim();
                taskList.add(new Event(info, start, end));
                int finalTask = taskList.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.get(finalTask).toString());
                if (taskList.size() == 1){
                    System.out.println("Now you have " +taskList.size()+" task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            } else if(userInput.contains("unmark") || userInput.contains("mark")){
                Integer itemNumber;
                String [] IndexArr = userInput.split(" ",2);
                itemNumber = Integer.parseInt(IndexArr[1])-1;
                if (userInput.contains("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    taskList.get((int) itemNumber).isCompleted = false;
                    System.out.println(taskList.get(itemNumber).toString());
                }
                else {
                    System.out.println("Nice! I've marked this task as done:");
                    taskList.get(itemNumber).isCompleted = true;
                    System.out.println(taskList.get(itemNumber).toString());
                }
            } else {
                taskList.add(new Task(userInput,false));
                System.out.println("added: "+userInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}