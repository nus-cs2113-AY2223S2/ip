package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public interface ArgumentValidator {
     boolean isValidArgument(BunnySession bunny, String argument);
}
