package limey.iohandler;

import java.util.Arrays;

public class Parser {
    /**
     * Returns the first word (space separated) from a given string in lower case
     *
     * @param line string of which the first word is to be extracted from
     * @return first word in a given string
     */
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
    /**
     * Returns the words (space separated) from a given string
     *
     * @param inLine string of which the words are to be extracted from
     * @return string array of space separated words from inLine
     */
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

}
