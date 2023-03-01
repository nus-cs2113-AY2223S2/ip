package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

/**
 * Implementation of <code>ArgumentValidator</code> which checks that an argument can be parsed as an integer
 * Further checks that the argument represents a valid index pointing to an existing task
 */
public class TaskIndexArgumentValidator extends IntegerArgumentValidator {
    /**
     * @param bunny    the current Bunny session
     * @param argument the value of the argument to validate
     * @throws InvalidArgumentException if the passed argument cannot be parsed as an integer,
     *                                  or that integer is not a valid index pointing to a task
     */
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        super.validateArgument(bunny, argument);

        int integerArgument = Integer.parseInt(argument);
        if (integerArgument <= 0 || integerArgument > bunny.getTasks().numTasks()) {
            throw new InvalidArgumentException(bunny.getUI().TASK_INDEX_ARGUMENT_ERROR);
        }
    }
}
