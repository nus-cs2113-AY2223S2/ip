package duke.data;

public class textImage {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static void printImage(int index) {
        switch (index) {
            case 1:
                System.out.println("        ___\n" +
                        "      _/ ..\\\n" +
                        "     ( \\  0/__\n" +
                        "      \\    \\__)\n" +
                        "      /     \\\n" +
                        "     /      _\\\n" +
                        "    `\"\"\"\"\"``");
                break;
            case 2:
                System.out.println("        ___\n" +
                        "      _/ oo\\\n" +
                        "     ( \\  -/__\n" +
                        "      \\    \\__)\n" +
                        "      /     \\\n" +
                        "     /      _\\\n" +
                        "    `\"\"\"\"\"``   ");
                break;
            case 3:
                System.out.println("        ___\n" +
                        "      _/ @@\\\n" +
                        "     ( \\  O/__\n" +
                        "      \\    \\__)\n" +
                        "      /     \\\n" +
                        "     /      _\\\n" +
                        "    `\"\"\"\"\"``");
                break;
            case 4:
                System.out.println("        ___\n" +
                        "      _/ 66\\\n" +
                        "     ( \\  ^/__\n" +
                        "      \\    \\__)\n" +
                        "      /     \\\n" +
                        "     /      _\\\n" +
                        "    `\"\"\"\"\"``");
                break;
            default:
                System.out.println("        ___\n" +
                        "      _/  \"\\\n" +
                        "     ( \\  ~/__\n" +
                        "      \\    \\__)\n" +
                        "      /     \\\n" +
                        "     /      _\\\n" +
                        "    `\"\"\"\"\"``");
        }
    }
}
