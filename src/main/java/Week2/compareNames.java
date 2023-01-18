package Week2;

public class compareNames {

    public static void main(String[] args) {
        String first = args[0];
        String second = args[1];
        System.out.println("Words given: " + first + ", " + second);

        System.out.print("They are the same: ");
        if (first.equals(second)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
