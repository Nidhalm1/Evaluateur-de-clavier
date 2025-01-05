package Geometry;

public class Rest {
  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  private int line;

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  private int column;

  Rest(int line, int column) {
    this.line = line;
    this.column = column;
  }

}
