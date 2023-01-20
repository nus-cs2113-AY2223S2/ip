import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "___________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        ArrayList<Task> checkLists = new ArrayList<Task>();

        String input;
        while(in.hasNext()){
            input = in.nextLine();
            if(input.equals("bye")){
                break;
            }
            //Display checkLists
            if(input.equals("list")){
                System.out.println(line);
                for(int i = 0;i<checkLists.size();i++){
                    System.out.print(i+1+".");
                    System.out.println(checkLists.get(i).getStatusIcon()+checkLists.get(i).name);
                }
            }else if(input.contains("unmark")){
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                Integer indexOfItem;
                String [] IndexArr = input.split(" ",2);
                indexOfItem = Integer.parseInt(IndexArr[1])-1;
                checkLists.get((int)indexOfItem).isDone = false;
                System.out.println(checkLists.get(indexOfItem).getStatusIcon()+checkLists.get(indexOfItem).name);
            }else if(input.contains("mark")){
                Integer indexOfItem = new Integer(0);
                String [] IndexArr = input.split(" ",2);
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                indexOfItem = Integer.parseInt(IndexArr[1])-1;
                checkLists.get(indexOfItem).isDone = true;
                System.out.println(checkLists.get(indexOfItem).getStatusIcon()+checkLists.get(indexOfItem).name);
            }else{
                System.out.println(line);
                checkLists.add(checkLists.size(), new Task(input,false));
                System.out.println("added: "+input);
            }
            System.out.println(line);
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
