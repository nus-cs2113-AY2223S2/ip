import java.util.Scanner;
public class Rolex {

    public static void main(String[] args) {

        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n\n");

        Scanner ReadInput = new Scanner(System.in);

        String[] books = new String[100];
        int BookCount = 0;

        while(true){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){

                System.out.println("--------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("--------------------------------------------------");
                System.exit(0);
            }

            else if(UserInput.equalsIgnoreCase("list")){
                System.out.println("--------------------------------------------------");
                for(int i=0; i<BookCount; i++) {
                    System.out.println(i+1 + ". " + books[i]);
                }
                System.out.println("--------------------------------------------------");
            }

            else{
                books[BookCount] = UserInput;
                BookCount++;
                System.out.println("--------------------------------------------------");
                System.out.println("added: " + books[BookCount-1]);
                System.out.println("--------------------------------------------------");
            }
        }

    }

}




