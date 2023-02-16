package limey.iohandler;

import java.util.Arrays;

public class Parser {
    public static String getFirstWord(String line) {
        String firstWord;
        int firstSpace = line.indexOf(' ');
        if (firstSpace != -1) {
            firstWord = line.substring(0, firstSpace).toLowerCase();
        } else {
            firstWord = line.toLowerCase();
        }
        return firstWord;
    }
    public static String[] splitInput(String inLine){
        int nextSpace = inLine.indexOf(' ');
        int numStrings = 1;
        String[] wordList = new String[numStrings];
        wordList[numStrings - 1] = getFirstWord(inLine);
        while(nextSpace!= -1) {
            numStrings++;
            String[] copyWordList = Arrays.copyOf(wordList, numStrings);
            wordList = copyWordList;
            inLine = inLine.substring(nextSpace + 1);
            copyWordList[numStrings - 1] = getFirstWord(inLine);
            nextSpace = inLine.indexOf(' ');
        }
        return wordList;
    }

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
