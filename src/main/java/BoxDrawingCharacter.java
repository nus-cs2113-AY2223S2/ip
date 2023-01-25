/**
 * The box drawing characters used to make decent boxes in the terminal
 * 
 * @see {@link https://en.wikipedia.org/wiki/Box-drawing_character}
 */
public class BoxDrawingCharacter {
    // ┌─┐
    // │ │
    // └─┘
    static final char HORIZONTAL_LINE = '─';
    static final char VERTICAL_LINE = '│';
    static final char TOP_LEFT_CORNER = '┌';
    static final char TOP_RIGHT_CORNER = '┐';
    static final char BOTTOM_LEFT_CORNER = '└';
    static final char BOTTOM_RIGHT_CORNER = '┘';
}
