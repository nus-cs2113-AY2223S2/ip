

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

    public static void main(String[] args) {

        Greet hello = new Greet();

        CommandManager commandManager = new CommandManager();

        hello.printHello();
        commandManager.manageCommand();

    }
}
