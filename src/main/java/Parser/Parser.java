package Parser;

import java.time.LocalDate;

public class Parser {

    /**
     * This is the first overloading method with method name taskName. It takes in the start index only
     *
     * @param userInput: input entered by user.
     * @param startIndex: substring to start from.
     *
     * @return substring of user input
     */
    public static String taskName(String userInput, int startIndex){
        return userInput.substring(startIndex);
    }


    /**
     * This is the first overloading method with method name taskName. It takes in the start index
     * and end index.
     *
     * @param userInput: input entered by user.
     * @param startIndex: substring to start from.
     * @param endIndex: substring to end at.
     *
     * @return substring of user input
     */
    public static String taskName(String userInput, int startIndex, int endIndex){
        return userInput.substring(startIndex, endIndex);
    }


    /**
     * This method is designed to extract the date and time for an object of
     * class 'DEADLINE' and display according to a certain format as show under @return.
     *
     * @param userInput: input entered by user.
     *
     * @return date and time in the following format: day + month + year + dayOfWeek + time(hh:mm)
     */
    public static String deadlineDate(String userInput){
        int indexOfSpace = userInput.indexOf(" ");
        String time = userInput.substring(indexOfSpace+1);
        String hh = time.substring(0,2);
        String mm = time.substring(2);
        String code;
        int x = Integer.parseInt(hh);
        if(x < 12){
            code = "AM";
        } else if(x == 12){
            code = "PM";
        } else{
            x = x-12;
            hh = String.valueOf(x);
            if(x<10){
                hh = "0" + x;
            }
            code = "PM";
        }
        time = hh + ":" + mm + code;
        LocalDate d1 = LocalDate.parse(userInput.substring(0,indexOfSpace));
        return d1.getDayOfMonth() + " " + d1.getMonth() + " " + d1.getYear() + ", " + d1.getDayOfWeek() + ", " + time;
    }


    /**
     * This method is designed to return the task index for various operations like
     * mark, unmark, delete.
     *
     * @param userInput: input entered by user
     * @param index: index of task to do operation
     *
     * @return task index
     */
    public static int taskIndex(String userInput, int index) {
        return Integer.parseInt(userInput.substring(index));
    }


    /**
     * This method is designed to extract the index of a substring from
     * an original string.
     *
     * @param userInput: input entered by user
     * @param subString: substring's index to be found
     *
     * @return index of substring from original string
     */
    public static int indexOfSubstring(String userInput, String subString){
        return userInput.indexOf(subString);
    }

}
