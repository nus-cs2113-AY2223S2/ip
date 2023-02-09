import java.util.Scanner;
import java.util.ArrayList;
import java.security.InvalidAlgorithmParameterException;
import java.util.MissingFormatArgumentException;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String userInput;
        while(in.hasNext()){
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.contains("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(i + 1 + ".");
                    System.out.println(tasks.get(i).toString());
                }
            } else if (userInput.contains("todo")) {
                String info = userInput.substring(5).trim();
                tasks.add(new Todo(info));
                int finalTask = tasks.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(finalTask).toString());
                if (tasks.size() == 1) {
                    System.out.println("Now you have " +tasks.size()+" task in the list.");
                } else {
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (userInput.contains("deadline")) {
                String [] listArray = userInput.split("/",2);
                String description = listArray[0];
                String dueDate = listArray[1];
                String info = description.substring(8).trim();
                String due = dueDate.substring(3).trim();
                tasks.add(new Deadline(info, due));
                int finalTask = tasks.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(finalTask).toString());
                if (tasks.size() == 1) {
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (userInput.contains("event")) {
                String [] listArray = userInput.split("/",3);
                String description = listArray[0];
                String startTime = listArray[1];
                String endTime = listArray[2];
                String info = description.substring(6).trim();
                String start = startTime.substring(5).trim();
                String end = endTime.substring(3).trim();
                tasks.add(new Event(info, start, end));
                int finalTask = tasks.size() - 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(finalTask).toString());
                if (tasks.size() == 1){
                    System.out.println("Now you have " + tasks.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if(userInput.contains("mark")){
                Integer itemNumber;
                String [] commandIndex = userInput.split(" ",2);
                itemNumber = Integer.parseInt(IndexArr[1])-1;
                if (userInput.equals("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    tasks.get((int) itemNumber).isCompleted = false;
                    System.out.println(tasks.get(itemNumber).toString());
                }
                else {
                    System.out.println("Nice! I've marked this task as done:");
                    tasks.get(itemNumber).isCompleted = true;
                    System.out.println(tasks.get(itemNumber).toString());
                }
            } else {
                tasks.add(new Task(userInput, false));
                System.out.println("added: " + userInput);
            }
        }
    }
}