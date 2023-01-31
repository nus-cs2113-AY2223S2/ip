import java.util.Scanner;

public class Duke {

    public static final int MAX_INT = 100;

    public static void greeting() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");

    }



    public static void add() {
        boolean isByeEntered = true;
        String outputs;
        String[] storeList;
        storeList = new String[MAX_INT];
        int i = 0;
        int a = 1;

        while (isByeEntered) {
            Scanner scan = new Scanner(System.in);
            outputs = scan.nextLine();
            if (outputs.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if(outputs.equals("list")){
                for(String list : storeList){
                    if(list!=null) {
                        System.out.println(a + ". " + list);
                        a++;
                    }else{
                        break;
                    }
                }

            } else {
                System.out.println("added: " + outputs);
                storeList[i] = outputs;
                i++;
            }
        }
    }


    public static void main(String[] args) {

        greeting();
        add();



    }
}
