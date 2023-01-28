import java.util.Scanner;
public class Rolex {

    //method to print bye and exit program.
    public static void printBye(){
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("_______________________________________");
        System.exit(0);

    }

    //method to echo input given by user
    public static void printEcho(String UserInput){
        System.out.println("_______________________________________");
        System.out.println(UserInput);
        System.out.println("_______________________________________");
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n");

        Scanner ReadInput = new Scanner(System.in);
        int count = 0;

        while(count==0){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){
                printBye();
                count++;
            }
            else{
                printEcho(UserInput);
            }

        }

    }

}




