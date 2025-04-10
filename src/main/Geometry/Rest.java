package Geometry;

/**
 * The {@code Rest} class represents a position of rest in a 2D space,
 * defined by its line and column coordinates.
 */
public class Rest {
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    Rest(int line, int column) {
        this.line = line;
        this.column = column;
    }
}
