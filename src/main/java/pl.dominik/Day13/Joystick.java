package main.java.pl.dominik.Day13;

public enum Joystick {

    NEUTRAL(0),
    LEFT(-1),
    RIGHT(1);

    private final int input;

    Joystick(int input){
        this.input = input;
    }

    public int getInput(){
        return input;
    }
}
