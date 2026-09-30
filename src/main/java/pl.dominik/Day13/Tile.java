package main.java.pl.dominik.Day13;

public class Tile {

    private int xPosition;
    private int yPosition;
    private TileType tileType;

    public Tile(int xPosition, int yPosition, int tileId) throws Exception {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileType = getTileType(tileId);
    }

    private TileType getTileType(long tileId) throws Exception {
        return switch ((int) tileId) {
            case 0 -> TileType.EMPTY;
            case 1 -> TileType.WALL;
            case 2 -> TileType.BLOCK;
            case 3 -> TileType.PADDLE;
            case 4 -> TileType.BALL;
            default -> throw new Exception("Unrecognized tile type");
        };
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
