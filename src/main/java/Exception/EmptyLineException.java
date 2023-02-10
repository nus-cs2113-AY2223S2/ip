package Exception;

public class EmptyLineException {
    static void validate (String input){
        if(input.equals("")){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
