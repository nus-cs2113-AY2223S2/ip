public class DukeList {
    private static String[] list = new String[100];
    private static int listSize = 0;
    public static void added(String s){
        if(listSize == 100){
            Print.PrintString("Sorry, the list is full!");
            return;
        }
        list[listSize++] = s;
        Print.PrintString("added: " + s);
    }
    public static void list(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < listSize; i++){
            System.out.println((i+1) + ". " + list[i]);
        }
        Print.PrintLine();
    }
}
