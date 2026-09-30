package main.java.pl.dominik.Day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

    List<Tile> tiles = new ArrayList<>();
    String segmentDisplay;
    IntcodeComputer intcodeComputer;
    char[][] map;

    int maxX;
    int maxY;

    public void execute() throws Exception {
        System.out.println("Day 13: ");
        System.out.println("Part One result: " + countBlockTiles());
        System.out.println("Part Two result: " + countGameScore());
    }

    public int countBlockTiles() throws Exception {
        intcodeComputer = new IntcodeComputer(readNumberFromFile());
        int blockCounter = 0;
        calculateTiles();

        for (Tile tile : tiles) {
            if (tile.getTileType() == TileType.BLOCK) {
                blockCounter++;
            }
        }
        return blockCounter;
    }

    public int countGameScore() throws Exception {
        intcodeComputer = new IntcodeComputer(readNumberFromFile());
        intcodeComputer.setMemoryAddress(0, 2L);
        setMapBoundaries();


        for(int i =0 ; i < 10; i++){

            if(getPaddleTile().getXPosition() < getBallTile().getXPosition()){
                makeMove(Joystick.RIGHT);
            }
            else if (getPaddleTile().getXPosition() > getBallTile().getXPosition()){
                makeMove(Joystick.LEFT);
            }
            else {
                makeMove(Joystick.NEUTRAL);
            }

            System.out.println("Ball:   x= " + getBallTile().getXPosition() + ", y= " + getBallTile().getYPosition());
            System.out.println("Paddle: x= " + getPaddleTile().getXPosition() + ", y= " + getPaddleTile().getYPosition());
        }



        return 0;
    }

    private void calculateTiles() throws Exception {
        List<Long> intcodeOutput = new ArrayList<>();
        tiles.clear();

        intcodeComputer.runIntCodeComputer();

        while (intcodeComputer.hasOutput()) {
            intcodeOutput.add(intcodeComputer.getOutput());
        }

        for (int i = 0; i < intcodeOutput.size(); i += 3) {
            if (isSegmentDisplayOutput(intcodeOutput, i)) {
                segmentDisplay = intcodeOutput.get(i + 2).toString();
            } else {
                tiles.add(new Tile(intcodeOutput.get(i).intValue(), intcodeOutput.get(i + 1).intValue(),
                        intcodeOutput.get(i + 2).intValue()));
            }
        }
    }

    private void playGame() throws Exception {
        if (getPaddleTile().getXPosition() > getBallTile().getXPosition()) {
            makeMove(Joystick.LEFT);
        } else if (getPaddleTile().getXPosition() < getBallTile().getXPosition()) {
            makeMove(Joystick.RIGHT);
        } else {
            makeMove(Joystick.NEUTRAL);
        }
    }

    private void makeMove(Joystick joystick) throws Exception {
        intcodeComputer.reset();
        tilte(joystick);
        calculateTiles();
        printMap();
        System.out.println("Segment display: " + segmentDisplay);
    }

    private Tile getBallTile() {
        return tiles.stream()
                .filter(tile -> tile.getTileType() == TileType.BALL)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No ball"));
    }

    private Tile getPaddleTile() {
        return tiles.stream()
                .filter(tile -> tile.getTileType() == TileType.PADDLE)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No paddle"));
    }

    private void tilte(Joystick joystick) {
        intcodeComputer.setInput(joystick.getInput());
    }

    private void printMap() {
        map = new char[maxX + 1][maxY + 1];

        for (Tile tile : tiles) {
            map[tile.getXPosition()][tile.getYPosition()] = tile.getTileType().getCharacter();
        }

        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
    }

    private void setMapBoundaries() {
        maxX = Collections.max(tiles, Comparator.comparing(Tile::getXPosition)).getXPosition() + 1;
        maxY = Collections.max(tiles, Comparator.comparing(Tile::getYPosition)).getYPosition() + 1;
    }

    private boolean isSegmentDisplayOutput(List<Long> intcodeOutput, int index) {
        int x = intcodeOutput.get(index).intValue();
        int y = intcodeOutput.get(index + 1).intValue();
        return x == -1 && y == 0;
    }

    private Long[] readNumberFromFile() throws IOException {
        Path path = Paths.get("src/main/resources/Day13/data.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();

        String[] lineWithoutCommas = line.trim().split(",");
        Long[] program = new Long[lineWithoutCommas.length];

        for (int i = 0; i < lineWithoutCommas.length; i++) {
            program[i] = Long.parseLong(lineWithoutCommas[i]);
        }
        return program;
    }
}
