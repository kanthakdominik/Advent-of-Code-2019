package main.java.pl.dominik.Day13;

public enum TileType {

    EMPTY(' '),
    WALL('▒'),
    BLOCK('□'),
    PADDLE('—'),
    BALL('●');

    private final char character;

    TileType(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
