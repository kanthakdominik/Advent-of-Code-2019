package main.java.pl.dominik.Day9;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class IntcodeComputer {

    private int pointer = 0;
    private int relativeBase = 0;
    private boolean isRunning = true;
    private final Long[] program = new Long[10000];
    private final Queue<Long> inputQueue = new LinkedList<>();
    private final Queue<Long> outputQueue = new LinkedList<>();

    public IntcodeComputer(Long[] pr) {
        Arrays.fill(program, 0L);
        System.arraycopy(pr, 0, program, 0, pr.length);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean hasOutput() {
        return !outputQueue.isEmpty();
    }

    public void setInput(long input) {
        inputQueue.add(input);
    }

    public long getOutput() throws Exception {
        if (outputQueue.peek() != null) {
            return outputQueue.poll();
        } else {
            throw new Exception("Output queue is empty");
        }
    }

    public void runIntCodeComputer() {
        boolean shouldPause = false;
        int[] instruction;
        while (isRunning && !shouldPause) {
            instruction = returnInstruction(program[pointer].intValue());
            switch (instruction[3]) {
                case 1: {
                    setProgramByParameter(instruction[0], pointer + 3, getValueByParameter(instruction[2], pointer + 1) + getValueByParameter(instruction[1], pointer + 2));
                    pointer += 4;
                    break;
                }
                case 2: {
                    setProgramByParameter(instruction[0], pointer + 3, getValueByParameter(instruction[2], pointer + 1) * getValueByParameter(instruction[1], pointer + 2));
                    pointer += 4;
                    break;
                }
                case 3: {
                    if (!inputQueue.isEmpty()) {
                        setProgramByParameter(instruction[2], pointer + 1, inputQueue.poll());
                        pointer += 2;
                    } else {
                        shouldPause = true;
                    }
                    break;
                }
                case 4: {
                    outputQueue.add(getValueByParameter(instruction[2], pointer + 1));
                    pointer += 2;
                    break;
                }
                case 5: {
                    int counter = 3;
                    if (getValueByParameter(instruction[2], pointer + 1) != 0) {
                        pointer = getValueByParameter(instruction[1], pointer + 2).intValue();
                        counter = 0;
                    }
                    pointer += counter;
                    break;
                }
                case 6: {
                    int counter = 3;
                    if (getValueByParameter(instruction[2], pointer + 1) == 0) {
                        pointer = getValueByParameter(instruction[1], pointer + 2).intValue();
                        counter = 0;
                    }
                    pointer += counter;
                    break;
                }
                case 7: {
                    long valueToSet = (getValueByParameter(instruction[2], pointer + 1) < getValueByParameter(instruction[1], pointer + 2)) ? 1L : 0L;
                    setProgramByParameter(instruction[0], pointer + 3, valueToSet);
                    pointer += 4;
                    break;
                }
                case 8: {
                    long valueToSet = (Objects.equals(getValueByParameter(instruction[2], pointer + 1), getValueByParameter(instruction[1], pointer + 2))) ? 1L : 0L;
                    setProgramByParameter(instruction[0], pointer + 3, valueToSet);
                    pointer += 4;
                    break;
                }
                case 9: {
                    relativeBase += getValueByParameter(instruction[2], pointer + 1);
                    pointer += 2;
                    break;
                }
                case 99: {
                    isRunning = false;
                    break;
                }
                default:
                    break;
            }
        }
    }

    void setProgramByParameter(int parameter, int pointer, long valueToSet) {
        if (parameter == 0) {                  // position mode
            program[program[pointer].intValue()] = valueToSet;
        }
        if (parameter == 2) {                  // relative mode
            program[relativeBase + program[pointer].intValue()] = valueToSet;
        }
    }

    Long getValueByParameter(int parameter, int pointer) {
        if (parameter == 0) {                  // position mode
            return program[program[pointer].intValue()];
        }
        if (parameter == 2) {                  // relative mode
            return program[relativeBase + program[pointer].intValue()];
        }
        return program[pointer];               // immediate mode
    }

    private int[] returnInstruction(int number) {
        int[] parameterModeArray = new int[5];
        int[] instruction = new int[4];
        StringBuilder stringBuilder = new StringBuilder();
        String arrayString = stringBuilder.append(number).reverse().toString();
        for (int i = 0; i < arrayString.length(); i++) {
            parameterModeArray[4 - i] = Character.getNumericValue(arrayString.charAt(i));
        }
        System.arraycopy(parameterModeArray, 0, instruction, 0, 3);
        instruction[3] = Integer.parseInt(String.valueOf(parameterModeArray[3]) + parameterModeArray[4]);
        return instruction;
    }
}