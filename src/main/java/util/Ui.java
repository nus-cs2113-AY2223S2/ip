package util;

import java.util.Scanner;

public class Ui {
    public String getInput(){
        Scanner newScanner = new Scanner(System.in);
        String input = newScanner.nextLine();
        return input;
    }
}
