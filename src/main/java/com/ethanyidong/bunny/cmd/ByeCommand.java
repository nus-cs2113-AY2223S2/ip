package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'bye' command
 */
public class ByeCommand extends ExecutableCommand {
    /**
     * Quits the current Bunny session
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        bunny.quit();
    }
}
