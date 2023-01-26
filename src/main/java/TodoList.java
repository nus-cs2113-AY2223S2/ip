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

    public int addItem(String line){
        if(listnum < MAXLISTNUM){
            // todoList[listnum++] = line;
            tasks[listnum++] = new Task(line);
            System.out.println(SPLITTER);
            System.out.println("    added: " + line);
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            System.out.println("    ERROR: List overflow!");
            System.out.println();
            return 0;
        }
    }

    public int markItem(int num, boolean mark){
        System.out.println(SPLITTER);
        if(num < listnum && num >= 0){
            // marks[num] = mark;
            tasks[num].mark(mark);
            if(mark){
                System.out.println("    Nice! I've marked this task as done:");
                System.out.print("      [X] ");
            }else{
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.print("      [ ] ");
            }
            System.out.println(tasks[num].getDescription());
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            System.out.println("    ERROR: Index wrong!");
            System.out.println(SPLITTER);
            System.out.println();
            return 0;
        }
    }

    public void showList(){
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++){
            System.out.println("    " + (i + 1) + ". ["
                               + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println(SPLITTER);
        System.out.println();
    }
}
