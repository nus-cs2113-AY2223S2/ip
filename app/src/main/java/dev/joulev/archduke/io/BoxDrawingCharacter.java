package dev.joulev.archduke.io;

/**
 * The box drawing characters used to make decent boxes in the terminal
 * 
 * @see {@link https://en.wikipedia.org/wiki/Box-drawing_character}
 */
public class BoxDrawingCharacter {
    // ╭─╮
    // │ │
    // ╰─╯
    public static final String HORIZONTAL_LINE = "─";
    public static final String VERTICAL_LINE = "│";
    public static final String TOP_LEFT_CORNER = "╭";
    public static final String TOP_RIGHT_CORNER = "╮";
    public static final String BOTTOM_LEFT_CORNER = "╰";
    public static final String BOTTOM_RIGHT_CORNER = "╯";
}
