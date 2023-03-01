package duke;

public class Parser {

    public String command;

    public Parser(String command) {
        this.command = command;
    }

    public static String[] parser(String command) {
        String firstWord;
        String remainingWords;
        String[] wordList = new String[2];
        if (command.contains(" ")) {
            int firstSpaceIndex = command.indexOf(" ");
            firstWord = command.substring(0, firstSpaceIndex);
            remainingWords = command.substring(firstSpaceIndex + 1, command.length());
            wordList[0] = firstWord;
            wordList[1] = remainingWords;
        } else {
            firstWord = command;
            wordList[0] = firstWord;
        }
        return wordList;
    }

}
