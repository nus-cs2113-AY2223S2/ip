package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public interface ArgumentValidator {
     void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException;
}
