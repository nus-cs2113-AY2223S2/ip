package UI;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }
    public Scanner getScanner() {
        return this.scanner;
    }
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }
    public void showLoadingError() {
        System.out.println("Savefile not there or corrupted! Starting fresh with a blank list.");
    }
}
