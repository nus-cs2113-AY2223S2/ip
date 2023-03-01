package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class TaskIndexArgumentValidator extends IntegerArgumentValidator {
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        super.validateArgument(bunny, argument);

        int integerArgument = Integer.parseInt(argument);
        if (integerArgument <= 0 || integerArgument > bunny.getTasks().numTasks()) {
            throw new InvalidArgumentException("is not a valid task number");
        }
    }
}
