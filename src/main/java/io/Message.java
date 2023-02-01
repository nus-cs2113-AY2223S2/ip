package io;

public enum Message {

    LINE("____________________________________________________________");


    private final String text;

    Message(String text) {
        this.text = text;
    }

    public String toString(){
        return text;
    }
}