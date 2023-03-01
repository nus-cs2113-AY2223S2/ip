package com.ethanyidong.bunny;

public class Bunny {
    public static final String DISABLE_SAVE = "nosave";

    public static void main(String[] args) {
        final boolean saveEnabled = args.length < 1 || !args[0].equals(DISABLE_SAVE);
        BunnySession bunny = new BunnySession(saveEnabled);

        bunny.runBunny();
    }
}
