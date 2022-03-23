package main.java.pl.dominik.Day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    private Asteroid stationAsteroid;
    int sizeOfMap;

    public void execute() throws IOException {
        System.out.println("Day 10: ");
        System.out.println("Part One result: " + getNumberOfDetectedAsteroids(readMapFromFile()));
        System.out.println("Part Two result: " + getThe200thVaporizedAsteroid());
    }

    public int getThe200thVaporizedAsteroid() {
        //tak mie moze byc bo odblokowywac trzeba dopiero jak usunie sie te blokujace asteoridy widok
        //mozna wykorzystac wektory, w moemencie usuwania atseody, mozna isc wektorem i odblokowac kolejna za nia (jedna)
        // czyli dodac do neighborposition ta asteroide
       // stationAsteroid.resetNeighborsPositions();
        stationAsteroid.move(sizeOfMap);

//        List<Position> vaporisedasteroidspositions = func get
//        Position the200thVaporisedAsteroidPosition =  vaporisedasteroidspositions. get(id == 200)
        Position the200thVaporisedAsteroidPosition =  new Position(0,0);
        return 100 * the200thVaporisedAsteroidPosition.getX() + the200thVaporisedAsteroidPosition.getY();
    }

    public int getNumberOfDetectedAsteroids(char[][] map) {
        List<Asteroid> asteroids = loadAsteroids(map);
        setNeighborAsteroidPositions(asteroids);
        computeVectors(asteroids);
        computeVisibleAsteroids(asteroids, map.length);
        return calculateNumberOfDetectedAsteroids(asteroids);
    }

    public int calculateNumberOfDetectedAsteroids(List<Asteroid> asteroids) {
        int maxNumberOfDetectedAsteroids = 0;

        for (Asteroid asteroid : asteroids) {
            int numberOfDetectedAsteroids = asteroid.getNumberOfVisibleNeighbors();
            if (numberOfDetectedAsteroids > maxNumberOfDetectedAsteroids) {
                maxNumberOfDetectedAsteroids = numberOfDetectedAsteroids;
                stationAsteroid = asteroid;
            }
        }
        return maxNumberOfDetectedAsteroids;
    }

    public void computeVisibleAsteroids(List<Asteroid> asteroids, int sizeOfMap) {
        for (Asteroid asteroid : asteroids) {
            asteroid.computeVisibleNeighbors(sizeOfMap);
        }
    }

    public void computeVectors(List<Asteroid> asteroids) {
        for (Asteroid asteroid : asteroids) {
            asteroid.computeVectors();
        }
    }

    public void setNeighborAsteroidPositions(List<Asteroid> asteroids) {
        for (Asteroid asteroid : asteroids) {
            List<Position> neighborsPositions = new ArrayList<>();
            for (Asteroid a : asteroids) {
                if (!asteroid.getPosition().equals(a.getPosition())) {
                    neighborsPositions.add(new Position(a.getPosition()));
                }
            }
            asteroid.setNeighborsPositions(neighborsPositions);
        }
    }

    public List<Asteroid> loadAsteroids(char[][] map) {
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    asteroids.add(new Asteroid(new Position(j, i)));
                }
            }
        }
        return asteroids;
    }

    private char[][] readMapFromFile() throws IOException {
        Path path = Paths.get("src/main/resources/Day10/data.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        List<String> listOfLines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            listOfLines.add(line);
        }
        char[][] map = new char[listOfLines.size()][listOfLines.size()];
        sizeOfMap = listOfLines.size();

        for (int i = 0; i < listOfLines.size(); i++) {
            for (int j = 0; j < listOfLines.get(i).length(); j++) {
                char ch = listOfLines.get(i).charAt(j);
                map[i][j] = ch;
            }
        }
        return map;
    }
}
