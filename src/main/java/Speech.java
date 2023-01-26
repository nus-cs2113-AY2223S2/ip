public class Speech {
    public static void sayHi(){
        System.out.println("\tHello! I am limey, What can I do for you?");
    }
    public static void sayBye() {
        System.out.println("\tBye! Hope to see you again soon. :)");
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printEcho(String inLine){
        printLine();
        System.out.println( "\t"+inLine);
        printLine();
    }
}
