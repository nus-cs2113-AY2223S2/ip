package Duke;

import java.util.Scanner;

public class Duke {
    public static final int LENGTH_DEADLINE = 8;
    public static final int LENGTH_TODO = 4;
    public static final int LENGTH_EVENT = 5;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------");
        Task[] lists = new Task[100];
        int index = 0;
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("--------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------");
                break;
            } else if (line.substring(0, 4).equals("mark")) {
                try{
                    String number = line.substring(5, 6);
                    int markIndex = Integer.parseInt(number);
                    lists[markIndex - 1].isDone = true;
                }catch(NumberFormatException e){
                    System.out.println("Oops! Mark should be followed by a number. " +
                            "(A valid index number should be separated by a space after the mark)");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Oops! Mark index out of bound! " +
                            "(A valid index number should be separated by a space after the mark)");
                }catch(NullPointerException e){
                    System.out.println("Oops! Mark index out of bound! " +
                            "(A valid index number should be separated by a space after the mark)");
                }
            } else if (line.contains("unmark")) {
                try{
                    String number = line.substring(7, 8);
                    int unMarkIndex = Integer.parseInt(number);
                    lists[unMarkIndex - 1].isDone = false;
                }catch(NumberFormatException e){
                    System.out.println("Oops! Unmark should be followed by a number. " +
                            "(A valid index number should be separated by a space after the unmark)");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Oops! Unmark index out of bound! " +
                            "(A valid index number should be separated by a space after the unmark)");
                }catch(NullPointerException e){
                    System.out.println("Oops! Unmark index out of bound! " +
                            "(A valid index number should be separated by a space after the unmark)");
                }
            } else if (line.equals("list")) {
                System.out.println("list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + "." + lists[i].toString());
                }
            } else if(line.startsWith("delete")){
                try{
                    String number = line.substring(7, 8);
                    int deleteIndex = Integer.parseInt(number);
                    if(deleteIndex==index){
                        printDeleteMessage(lists,deleteIndex-1,index);
                        index=index-1;
                    }
                    else if(deleteIndex>=1 && deleteIndex<=index-1){
                        printDeleteMessage(lists,deleteIndex-1,index);
                        for(int i=deleteIndex-1;i<index-1;i++){
                            lists[i] = lists[i + 1];
                        }
                        index=index-1;
                    }else{
                        System.out.println("Oops! Delete should be followed by a valid number. " +
                                "(A valid index number should be separated by a space after the delete)");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Oops! Unmark should be followed by a number. " +
                            "(A valid index number should be separated by a space after the delete)");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Oops! Unmark index out of bound! " +
                            "(A valid index number should be separated by a space after the delete)");
                }catch(NullPointerException e){
                    System.out.println("Oops! Unmark index out of bound! " +
                            "(A valid index number should be separated by a space after the delete)");
                }
            }else {
                if (line.substring(0, LENGTH_TODO).equals("todo")) {
                    lists[index] = new Todo(line.substring(LENGTH_TODO + 1));
                    printMessage(lists,index);
                    index++;
                } else if (line.substring(0, LENGTH_DEADLINE).equals("deadline")) {
                    int breakingPoint = line.indexOf("/by");
                    String description = line.substring(LENGTH_DEADLINE + 1, breakingPoint);
                    String by = line.substring(breakingPoint + 4);
                    lists[index] = new Deadline(description, by);
                    printMessage(lists,index);
                    index++;
                } else if (line.substring(0, LENGTH_EVENT).equals("event")) {
                    int breakingPoint_1 = line.indexOf("/from");
                    int breakingPoint_2 = line.indexOf("/to");
                    String description = line.substring(LENGTH_EVENT + 1, breakingPoint_1);
                    String start = line.substring(breakingPoint_1 + 6, breakingPoint_2);
                    String end = line.substring(breakingPoint_2 + 4);
                    lists[index] = new Event(description, start, end);
                    printMessage(lists,index);
                    index++;
                }else{
                    System.out.println("Sorry I can't get your message! Please try again.");
                }

            }

        }
    }
    public static void printMessage(Task[] Tasks, int taskIndex){
        System.out.println("--------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(Tasks[taskIndex].toString());
        System.out.println("Now you have "+(taskIndex+1)+" task(s) in the list");
        System.out.println("--------------------------------");
    }
    public static void printDeleteMessage(Task[] Tasks, int deleteIndex,int totalIndex){
        System.out.println("--------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(Tasks[deleteIndex].toString());
        System.out.println("Now you have "+(totalIndex-1)+" task(s) in the list");
        System.out.println("--------------------------------");
    }
}
