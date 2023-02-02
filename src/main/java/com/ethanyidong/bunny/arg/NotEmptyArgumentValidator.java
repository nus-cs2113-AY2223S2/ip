package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class NotEmptyArgumentValidator implements ArgumentValidator {
    public boolean isValidArgument(BunnySession bunny, String argument) {
        if(argument == null) {
            return false;
        }

        if(argument.isEmpty()) {
            return false;
        }

        return true;
    }
}
