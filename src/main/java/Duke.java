import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Duke{
    public static void main(String[] args) {
        String dash = "__________________________________";
        String greet = dash + "\nHello I'm Duke\nWhat can I do for you?\n" + dash;
        System.out.println(greet);

        ToDoList toDo = new ToDoList();

        while (true) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String[] Temp = input.split(" ");
            if(Temp[0].equals("mark")|Temp[0].equals("unmark")){
                int numberToMark = Integer.parseInt(Temp[1]);
                toDo.mark(toDo.at(numberToMark));
            }
            else if (input.equals("bye")) {
                System.out.println(dash + "\nBye. Hope to see you again soon!\n" + dash);
                break;
            }
            else if (input.equals("list")) {
                toDo.printList();
            }
            else {
                Task task= new Task(input, toDo.size()+1);
                toDo.addToList(task);
            }
        }
    }


}


