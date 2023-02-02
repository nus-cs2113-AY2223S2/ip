package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class IntegerArgumentValidator implements ArgumentValidator {
    public boolean isValidArgument(BunnySession bunny, String argument) {
        if(argument == null) {
            return false;
        }

        try {
            Integer.parseInt(argument);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
