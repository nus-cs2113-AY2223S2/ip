public class TodoList {
    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    private String[] todoList = new String[MAXLISTNUM];
    private int listnum;

    TodoList(){
        listnum = 0;
    }

    public int getListnum(){
        return listnum;
    }

    public int addItem(String line){
        if(listnum < MAXLISTNUM){
            todoList[listnum++] = line;
            System.out.println(SPLITTER);
            System.out.println("   added: " + line);
            System.out.println(SPLITTER);
            System.out.println();
            return 1;
        }else{
            System.out.println("ERROR: List overflow!");
            System.out.println();
            return 0;
        }
    }

    public void showList(){
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++){
            System.out.println("    " + (i + 1) + ". " + todoList[i]);
        }
        System.out.println(SPLITTER);
        System.out.println();
    }
}
