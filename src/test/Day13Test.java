import main.java.pl.dominik.Day13.Day13;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    private final Day13 day13 = new Day13();

    @Test
    void checkCountBlockTiles() throws Exception {
        assertEquals(260, day13.countBlockTiles());
    }

    @Test
    void checkCountGameScore() throws Exception {
        assertEquals(12952, day13.countGameScore());
    }
}