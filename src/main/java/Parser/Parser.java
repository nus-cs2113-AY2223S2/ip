package Parser;

import java.time.LocalDate;

public class Parser {

    public static String taskName(String userInput, int startIndex){
        return userInput.substring(startIndex);
    }

    public static String taskName(String userInput, int startIndex, int endIndex){
        return userInput.substring(startIndex, endIndex);
    }

    public static int taskIndex(String userInput, int index) {
        return Integer.parseInt(userInput.substring(index));
    }

    public static int indexOfSubstring(String userInput, String subString){
        return userInput.indexOf(subString);
    }

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

}
