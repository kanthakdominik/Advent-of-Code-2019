package main.java;

import main.java.pl.dominik.Day1.Day1;
import main.java.pl.dominik.Day10.Day10;
import main.java.pl.dominik.Day11.Day11;
import main.java.pl.dominik.Day12.Day12;
import main.java.pl.dominik.Day13.Day13;
import main.java.pl.dominik.Day2.Day2;
import main.java.pl.dominik.Day3.Day3;
import main.java.pl.dominik.Day4.Day4;
import main.java.pl.dominik.Day5.Day5;
import main.java.pl.dominik.Day6.Day6;
import main.java.pl.dominik.Day7.Day7;
import main.java.pl.dominik.Day8.Day8;
import main.java.pl.dominik.Day9.Day9;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\nType day: (1-24)");
            switch (in.next()) {
                case "1": {
                    executeDay1();
                    break;
                }
                case "2": {
                    executeDay2();
                    break;
                }
                case "3": {
                    executeDay3();
                    break;
                }
                case "4": {
                    executeDay4();
                    break;
                }
                case "5": {
                    executeDay5();
                    break;
                }
                case "6": {
                    executeDay6();
                    break;
                }
                case "7": {
                    executeDay7();
                    break;
                }
                case "8": {
                    executeDay8();
                    break;
                }
                case "9": {
                    executeDay9();
                    break;
                }
                case "10": {
                    executeDay10();
                    break;
                }
                case "11": {
                    executeDay11();
                    break;
                }
                case "12": {
                    executeDay12();
                    break;
                }
                case "13": {
                    executeDay13();
                    break;
                }
                case "q":
                    return;
                default:
                    break;
            }
        }
    }

    private static void executeDay1() throws IOException {
        Day1 day1 = new Day1();
        day1.execute();
    }

    private static void executeDay2() throws IOException {
        Day2 day2 = new Day2();
        day2.execute();
    }

    private static void executeDay3() throws IOException {
        Day3 day3 = new Day3();
        day3.execute();
    }

    private static void executeDay4() throws IOException {
        Day4 day4 = new Day4();
        day4.execute();
    }

    private static void executeDay5() throws IOException {
        Day5 day5 = new Day5();
        day5.execute();
    }

    private static void executeDay6() throws IOException {
        Day6 day6 = new Day6();
        day6.execute();
    }

    private static void executeDay7() throws Exception {
        Day7 day7 = new Day7();
        day7.execute();
    }

    private static void executeDay8() throws IOException {
        Day8 day8 = new Day8();
        day8.execute();
    }

    private static void executeDay9() throws Exception {
        Day9 day9 = new Day9();
        day9.execute();
    }

    private static void executeDay10() throws IOException {
        Day10 day10 = new Day10();
        day10.execute();
    }

    private static void executeDay11() throws Exception {
        Day11 day11 = new Day11();
        day11.execute();
    }

    private static void executeDay12() throws Exception {
        Day12 day12 = new Day12();
        day12.execute();
    }

    private static void executeDay13() throws Exception {
        Day13 day13 = new Day13();
        day13.execute();
    }
}
