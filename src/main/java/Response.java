public class Response {
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void sayHi() {
        LukeLogo logo = new LukeLogo();
        printLine();
        System.out.println("Hello I'm\n" + logo.getRandLogo());
        System.out.println("What can I do for you?");
        printLine();
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
