public class Duke {

    /**
     * To draw horizontal separation lines in between lines of text
     * Use unicode box drawings light horizontal(0x2500)
     * Important to node the code is in hex
     * Character.toString(char c) converts a character to string to use print output the character
     * Otherwise, use print(char) method of PrintStream class to output a single character
     * @author: wenxin
     * @param[in]: length/number of characters of the horizontal separation line
     */
    public static void printSeparationLine(int length){
        String s = Character.toString(0x2500);
        for (int i=0; i<length; i+=1){
            System.out.print(s);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = " Hello! I'm Duke :D\n"
                + " What can I do for you?\n";

        String bye = " Bye. Hope to see you again soon!\n";

        System.out.println("Hello from\n" + logo);
        printSeparationLine(45);
        System.out.println(hello);
        printSeparationLine(45);
        System.out.println(bye);
        printSeparationLine(45);
    }
}
