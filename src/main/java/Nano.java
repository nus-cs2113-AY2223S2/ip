public class Nano {
    //print horizontal line of length = len * 10
    public static void print_hor_ln() {
        int len = 10;
        for (int i = 0; i < len; i += 1) {
            System.out.print("__________");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        //chatbot startup
        print_hor_ln();
        String logo = "| \\  ||   / \\   | \\  |||  __  |\n"
                    + "||\\\\ ||  / _ \\  ||\\\\ ||| |  | |\n"
                    + "|| \\\\|| //   \\\\ || \\\\||| |__| |\n"
                    + "||  \\_|//     \\\\||  \\_||______|\n";
        System.out.println(logo);
        System.out.println("Serial number: 034-4532-5893.....");
        System.out.println("Activating the 7th generation Nano Machine of the Chan Corporation.....");
        print_hor_ln();
        System.out.println("How may I assist you?");
        print_hor_ln();

        //chatbot exit message
        System.out.println("Sleep mode activated.");
    }
}
