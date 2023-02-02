public class TodoList {
    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    private Task[] tasks = new Task[MAXLISTNUM];
    private int listnum;

    TodoList(){
        listnum = 0;
    }

    public int getListnum(){
        return listnum;
    }

    public int addItem(Task task){
        if(listnum < MAXLISTNUM){
            tasks[listnum++] = task;
            System.out.println(SPLITTER);
            System.out.println("    " + "Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    " + "Now you have " + listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            Duke.printError("List overflow");
            return 0;
        }
    }

    public int markItem(int num, boolean mark){
        if(num < listnum && num >= 0){
            // marks[num] = mark;
            tasks[num].mark(mark);
            if(mark){
            System.out.println(SPLITTER);
            System.out.println("    Nice! I've marked this task as done:");
                System.out.print("      [X] ");
            }else{
            System.out.println(SPLITTER);
            System.out.println("    OK, I've marked this task as not done yet:");
                System.out.print("      [ ] ");
            }
            System.out.println(tasks[num].getDescription());
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            Duke.printError("Wrong index");
            return 0;
        }
    }

    public void showList(){
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++){
            System.out.println("    " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println(SPLITTER);
        System.out.println();
    }
}
