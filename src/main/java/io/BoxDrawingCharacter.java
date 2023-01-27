package io;

/**
 * The box drawing characters used to make decent boxes in the terminal
 * 
 * @see {@link https://en.wikipedia.org/wiki/Box-drawing_character}
 */
public class BoxDrawingCharacter {
    // ╭─╮
    // │ │
    // ╰─╯
    public static final char HORIZONTAL_LINE = '─';
    public static final char VERTICAL_LINE = '│';
    public static final char TOP_LEFT_CORNER = '╭';
    public static final char TOP_RIGHT_CORNER = '╮';
    public static final char BOTTOM_LEFT_CORNER = '╰';
    public static final char BOTTOM_RIGHT_CORNER = '╯';
}
