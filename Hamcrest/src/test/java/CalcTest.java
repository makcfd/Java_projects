import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {

    private Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void  testPlusMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        // when:
        int result = calc.plus.apply(a,b);
        // that:
        assertThat(result, comparesEqualTo(20));
    }

    @Test
    void testMinusMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        int expected = 0;
        // when:
        double result = calc.minus.apply(a,b);
        // then:
        // here is just playing around with different hamcrest matchers
        // for example, use 'closeTo', however it is not necessary
        assertThat(result, closeTo(0,0.5));
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
        // here again is playing with another hamcrest matcher
        assertThat(result, greaterThanOrEqualTo(expected));
    }

    @Test
    void testDivideMethodOfCalculator() {
        // given:
        int a = 10;
        int b = 10;
        int expected = 0;
        // when:
        int result = calc.devide.apply(a,b);
        // then:
        // again playing with another matcher
        assertThat(result, greaterThan(expected));
    }

    @Test
    void testPowerMethodOfCalculator() {
        // given:
        int a = 10;
        int expected = 200;
        // when:
        int result = calc.pow.apply(a);
        // then:
        // playing with 'lessThan' matcher from Hamcrest
        assertThat(result, lessThan(expected));
    }
    @Test
    void testAbsMethodOfCalculator() {
        // given:
        int a = -10;
        int expected = 8;
        // when:
        int result = calc.abs.apply(a);
        // then:
        assertThat(result, greaterThan(expected));
    }

}
