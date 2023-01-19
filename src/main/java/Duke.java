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
        ArrayList<String> arr = new ArrayList<String>();

        String input;
        while(in.hasNext()){
            input = in.nextLine();
            if(input.equals("bye")){
                break;
            }

            if(input.equals("list")){
                System.out.println(line);
                for(int i = 0;i<arr.size();i++){
                    System.out.print(i+1+".");

                    System.out.println(arr.get(i));
                }

            }else if(input.contains("unmark")){
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                Integer index = new Integer(0);
                String [] IndexArr = input.split(" ",2);
                index = Integer.parseInt(IndexArr[1])-1;
                String name = arr.get((int)index);
                arr.remove((int)index);
                String [] NameArr = name.split(" ",2);
                arr.add(index,"[ ] "+NameArr[1]);
                System.out.println(arr.get(index));
            }else if(input.contains("mark")){
                Integer index = new Integer(0);
                String [] IndexArr = input.split(" ",2);
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                index = Integer.parseInt(IndexArr[1])-1;
                String name = arr.get((int)index);
                Task tasking = new Task(name,true);
                arr.remove((int)index);
                String [] NameArr = name.split(" ",3);
                arr.add(index,tasking.getStatusIcon()+NameArr[2]);
                System.out.println(arr.get(index));
            }else{

                arr.add("[ ] "+input);
                System.out.println(line);
                System.out.println("added: "+input);
            }
            System.out.println(line);
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
