public class Response {
    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    private void printSignature() {
        System.out.print("[Luke]: ");
    }

    public void sayHi() {
        LukeLogo logo = new LukeLogo();
        printLine();
        printSignature();
        System.out.println("Hello I'm\n" + logo.getRandLogo());
        System.out.println("What can I do for you?");
        printLine();
    }

    public void sayBye() {
        printLine();
        printSignature();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printString(String toPrint) {
        printLine();
        printSignature();
        System.out.println(toPrint);
        printLine();
    }

}
