package pl.kanthak;

public class Stage2 {

    static Integer noun = 0; //(0-99)
    static Integer verb = 0; //(0-99)

    public static void main(String[] args) {

        Integer[] integerArray = returnClearIntegerArray();

        for (noun = 0; noun < 100; noun++) {
            for (verb = 0; verb < 100; verb++) {
                for (int i = 0; i < integerArray.length; i += 4) {
                    switch (integerArray[i]) {
                        case 1: {
                            integerArray[integerArray[i + 3]] = integerArray[integerArray[i + 1]] + integerArray[integerArray[i + 2]];
                            break;
                        }
                        case 2: {
                            integerArray[integerArray[i + 3]] = integerArray[integerArray[i + 1]] * integerArray[integerArray[i + 2]];
                            break;
                        }
                        case 99:
                            break;
                    }
                    if (integerArray[0] == 19690720) {
                        System.out.println("noun= " + noun);
                        System.out.println("verb= " + (verb - 1));        // subtract 1, because in for loop verb is increased by 1
                        return;
                    }
                }
                integerArray = returnClearIntegerArray();
            }
        }
    }

    public static Integer[] returnClearIntegerArray() {
        return new Integer[]{1, noun, verb, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0,
                3, 2, 9, 1, 19, 1, 19, 6, 23, 2, 6, 23, 27, 2, 27, 9, 31, 1,
                5, 31, 35, 1, 35, 10, 39, 2, 39, 9, 43, 1, 5, 43, 47, 2, 47,
                10, 51, 1, 51, 6, 55, 1, 5, 55, 59, 2, 6, 59, 63, 2, 63, 6,
                67, 1, 5, 67, 71, 1, 71, 9, 75, 2, 75, 10, 79, 1, 79, 5, 83,
                1, 10, 83, 87, 1, 5, 87, 91, 2, 13, 91, 95, 1, 95, 10, 99, 2,
                99, 13, 103, 1, 103, 5, 107, 1, 107, 13, 111, 2, 111, 9, 115,
                1, 6, 115, 119, 2, 119, 6, 123, 1, 123, 6, 127, 1, 127, 9, 131,
                1, 6, 131, 135, 1, 135, 2, 139, 1, 139, 10, 0, 99, 2, 0, 14, 0
        };
    }
}