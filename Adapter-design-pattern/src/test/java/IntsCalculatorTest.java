import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntsCalculatorTest {

    private Ints intsCalc;

    @BeforeEach
    void setUp() {
        intsCalc = new IntsCalculator();
    }

    @Test
    void intsCalcSumTest() {
        // given
        int arg1 = 10;
        int arg2 = 20;
        int expected = 30;
        // when
        int result = intsCalc.sum(arg1, arg2);
        // then
        assertEquals(expected, result);
    }

    @Test
    void intsCalcMultTest() {
        // given
        int arg1 = 10;
        int arg2 = 20;
        int expected = 200;
        // when
        int result = intsCalc.mult(arg1, arg2);
        // then
        assertEquals(expected, result);
    }

    @Test
    void intsCalcPowTest() {
        // given
        int arg1 = 10;
        int arg2 = 2;
        int expected = 100;
        // when
        int result = intsCalc.pow(arg1, arg2);
        // then
        assertEquals(expected, result);
    }
}
