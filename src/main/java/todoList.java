public class todoList {
    static String lineBreak = "____________________\n";
    static String listing = "This is your todo list.\n";
    static String addBefore = "I have added ";
    static String addAfter = " to your todo list.\n";
    private static int tasksNumber = 0;
    private static String[] todo = new String[100];
    public static void add(String item) {
        todo[tasksNumber] = item;
        tasksNumber +=1;
        System.out.println(lineBreak + addBefore + item + addAfter + lineBreak);
    }
    public static void list(){
        System.out.print(lineBreak + listing + lineBreak);
        for(int i=0; i<tasksNumber; i+=1){
            System.out.println(i+1 + ". " + todo[i]);
        }
        System.out.print(lineBreak);
    }
}
