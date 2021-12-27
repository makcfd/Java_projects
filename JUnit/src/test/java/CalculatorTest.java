import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    void testPlusMethodOfCalculator() {
        int a = 10;
        int b = 10;
        int expected = 20;
        int result = calc.plus.apply(a,b);
        assertEquals(expected, result);
    }

    @Test
    void testMinusMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        int expected = 0;
        // when:
        int result = calc.minus.apply(a,b);
        // then:
        assertEquals(expected, result);
    }

    @Test
    void testMultiplyMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        int expected = 100;
        // when:
        int result = calc.multiply.apply(a,b);
        // then:
        assertEquals(expected, result);
    }
    @Test
    void testDivideMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        int expected = 1;
        // when:
        int result = calc.devide.apply(a,b);
        // then:
        assertEquals(expected, result);
    }
    @Test
    void testPowerMethodOfCalculator() {
        // given:
        int a = 10;
        int expected = 100;
        // when:
        int result = calc.pow.apply(a);
        // then:
        assertEquals(expected, result);
    }
    @Test
    void testAbsMethodOfCalculator() {
        // given:
        int a = -10;
        int expected = 10;
        // when:
        int result = calc.abs.apply(a);
        // then:
        assertEquals(expected, result);
    }
}