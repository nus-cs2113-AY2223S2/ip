import java.util.ArrayList;
public class Ui {
    private static final String OOPS = "OOPS! ";
    private static final String LINEDIVIDER = "___________________________________________";

    //marktask

    //deletetask
    public static void printResponse(ArrayList<String> responseList) {
        for (String s : responseList) {
            System.out.println(s);
        }
    }

    //task creation
    public static void printErrorMessage(Exception e) {
        System.out.println(Ui.OOPS + e.getMessage());
    }

    public static void printLineDivider() {
        System.out.println(Ui.LINEDIVIDER);
        System.out.println("");
    }

}
