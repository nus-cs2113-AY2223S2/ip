import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Douph");
        System.out.println("What can I do for you?");
        String[] lists=new String[100];
        int index=0;
        while(true){
            String line=in.nextLine();
            if(line.equals("bye")){
                System.out.println("list:");
                for(int i=0;i<index;i++){
                    System.out.println(lists[i]);
                }
                System.out.println("bye");
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            lists[index]=line;
            index++;
            System.out.println(line);
            System.out.println("\t"+"added: "+line);
        }
    }
}
