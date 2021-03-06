package main.java.pl.dominik.Day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day4 {

    public void execute() throws IOException {
        Integer[] criteriaNumbers = readCriteriaNumbers();
        System.out.println("Day 1: ");
        System.out.println("Part One result: " + calculateNumberOfDifferentPasswordsWithFirstCriteria(criteriaNumbers));
        System.out.println("Part Two result: " + calculateNumberOfDifferentPasswordsWithSecondCriteria(criteriaNumbers));
    }

    public int calculateNumberOfDifferentPasswordsWithFirstCriteria(Integer[] criteriaNumbers) {
        boolean isDouble = false;                  // isDouble=true;    to meet the conditions
        int compareNumber = 0;                     // compareNumber=5   to meet the conditions
        int howManyPasswords = 0;

        for (int i = criteriaNumbers[0]; i <= criteriaNumbers[1]; i++) {
            String password = Integer.toString(i);

            for (int j = 1; j < password.length(); j++) {
                if (password.charAt(j - 1) <= password.charAt(j)) {
                    if (password.charAt(j - 1) == password.charAt(j)) {
                        isDouble = true;
                    }
                    compareNumber++;
                }
                if (compareNumber == 5 && isDouble) {
                    howManyPasswords++;
                }
            }
            isDouble = false;
            compareNumber = 0;
        }
        return howManyPasswords;
    }

    public int calculateNumberOfDifferentPasswordsWithSecondCriteria(Integer[] criteriaNumbers) {
        boolean isDouble = false;                   // isDouble=true;                      to meet the conditions
        boolean hasPasswordDoubledDigits;           // hasPasswordDoubledDigits = true;    to meet the conditions
        int compareNumber = 0;                      // compareNumber=5;                    to meet the conditions
        int howManyPasswords = 0;

        for (int i = criteriaNumbers[0]; i <= criteriaNumbers[1]; i++) {
            String password = Integer.toString(i);
            hasPasswordDoubledDigits = hasPasswordDoubledDigits(password);

            for (int j = 1; j < password.length(); j++) {
                if (password.charAt(j - 1) <= password.charAt(j)) {
                    if (password.charAt(j - 1) == password.charAt(j)) {
                        isDouble = true;
                    }
                    compareNumber++;
                }
                if (compareNumber == 5 && isDouble && hasPasswordDoubledDigits) {
                    howManyPasswords++;
                }
            }
            isDouble = false;
            compareNumber = 0;
        }
        return howManyPasswords;
    }

    private boolean hasPasswordDoubledDigits(String password) {
        char[] ch = new char[password.length()];
        int[] count = new int[100];
        boolean check = false;

        for (int i = 0; i < password.length(); i++) {
            count[password.charAt(i)]++;
        }
        for (int i = 0; i < password.length(); i++) {
            ch[i] = password.charAt(i);
            int find = 0;
            for (int j = 0; j <= i; j++) {
                if (password.charAt(i) == ch[j]) {
                    find++;
                }
            }
            if (find == 1) {
                if (count[password.charAt(i)] == 2) {
                    check = true;
                }
            }
        }
        return check;
    }

    private Integer[] readCriteriaNumbers() throws IOException {
        Path path = Paths.get("src/main/resources/Day4/data.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();

        String[] lineWithoutCommas = line.trim().split("-");
        Integer[] integerArray = new Integer[lineWithoutCommas.length];

        for (int i = 0; i < lineWithoutCommas.length; i++) {
            integerArray[i] = Integer.parseInt(lineWithoutCommas[i]);
        }
        return integerArray;
    }
}
