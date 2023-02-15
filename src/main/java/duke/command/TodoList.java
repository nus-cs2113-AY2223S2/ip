package duke.command;

import duke.task.Task;
import duke.*;
import java.util.ArrayList;

public class TodoList {
    
    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    // private Task[] tasks = new Task[MAXLISTNUM];
    private ArrayList<Task> tasks2 = new ArrayList<>();
    private int listnum;

    public TodoList(){
        listnum = 0;
    }

    public int getListnum(){
        return listnum;
    }

    public int addItem(Task task){
        if(listnum < MAXLISTNUM){
            // tasks[listnum++] = task;
            tasks2.add(task);
            listnum++;
            System.out.println(SPLITTER);
            System.out.println("    " + "Got it. I've added this task:");
            System.out.println("      " + task.toString());
            System.out.println("    " + "Now you have " + listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            Duke.printError("List overflow");
            return 0;
        }
    }

    public void deleteItem(int num) throws DukeException{
        if(tasks2.size() > num){
            System.out.println(SPLITTER);
            System.out.println("    " + "Noted. I've removed this task:");
            System.out.println("      " + tasks2.get(num));
            System.out.println("    " + "Now you have " + --listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            tasks2.remove(num);
        }else{
            throw new DukeException("This task does not exist!");
        }
    }

    public int markItem(int num, boolean mark) throws DukeException{
        if(num < listnum && num >= 0){
            // marks[num] = mark;
            // tasks[num].mark(mark);
            tasks2.get(num).mark(mark);
            if(mark){
                System.out.println(SPLITTER);
                System.out.println("    Nice! I've marked this task as done:");
            }else{
                System.out.println(SPLITTER);
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.print("      ");
            // System.out.println(tasks[num].getDescription());
            System.out.println(tasks2.get(num));
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            throw new DukeException("Index out of bound!");
        }
    }

    public void showList(){
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++){
            System.out.println("    " + (i + 1) + ". " + tasks2.get(i));
        }
        System.out.println(SPLITTER);
        System.out.println();
    }
}
