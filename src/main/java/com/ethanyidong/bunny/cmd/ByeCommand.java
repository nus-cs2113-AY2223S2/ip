package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;

public class ByeCommand extends ExecutableCommand {
    public void execute(BunnySession bunny) {
        bunny.quit();
    }
}
