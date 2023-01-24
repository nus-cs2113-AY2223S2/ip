public class Psyduck {

    public static void linePrint(){
        for (long i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        linePrint();
        System.out.println("Psyduck! Psy Psy Psyduck! (Hello! How can I help you?)");
        linePrint();
        System.out.println("Psyduck! (Buh Bye!)");
        linePrint();
    }
}
