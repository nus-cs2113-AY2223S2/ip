package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class TaskIndexArgumentValidator extends IntegerArgumentValidator {
    public boolean isValidArgument(BunnySession bunny, String argument) {
        if (!super.isValidArgument(bunny, argument)) {
            return false;
        }

        int integerArgument = Integer.parseInt(argument);
        if (integerArgument <= 0 || integerArgument > bunny.numTasks()) {
            return false;
        }
        return true;
    }
}
