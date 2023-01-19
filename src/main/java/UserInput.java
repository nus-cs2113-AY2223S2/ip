import java.util.Scanner;
public class UserInput {
    private final Scanner in;

    public UserInput() {
        in = new Scanner(System.in);
    }

    private void printSignature() {
        System.out.print("[User]: ");
    }

    public void closeScanner() {
        in.close();
    }

    public String readUserInput() {
        printSignature();
        return in.nextLine();
    }
}
