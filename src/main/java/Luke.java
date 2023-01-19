import java.util.Scanner;
public class Luke {
    public static void main(String[] args) {
        Response response = new Response();
        //Scanner read = new Scanner(System.in);
        UserInput userInput = new UserInput();
        response.sayHi();
        while (true) {
            String input = userInput.readUserInput();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            response.printString(input);
        }
        userInput.closeScanner();
        response.sayBye();
    }
}
