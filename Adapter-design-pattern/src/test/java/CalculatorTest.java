import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    void addFirtsArgument() {
        // given
        Double arg1 = 15.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        // when
        Double result = form.a;
        // then
        assertEquals(arg1, result);
    }

    @Test
    void addSecondArgument() {
        // given
        Double arg1 = 15.0;
        Double arg2 = 15.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        // when
        Double result = form.b;
        // then
        assertEquals(arg2, result);
    }

    @Test
    void checkCalculateSum() {
        // given
        Double arg1 = 15.0;
        Double arg2 = 15.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        Double expected = 30.0;
        // when
        Double result = form.calculate(Calculator.Operation.SUM).result();
        // then
        assertEquals(expected, result);
    }


    @Test
    void checkCalculateSub() {
        // given
        Double arg1 = 15.0;
        Double arg2 = 15.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        Double expected = 0.0;
        // when
        Double result = form.calculate(Calculator.Operation.SUB).result();
        // then
        assertEquals(expected, result);
    }

    @Test
    void checkCalculatePow() {
        // given
        Double arg1 = 2.0;
        Double arg2 = 2.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        Double expected = 4.0;
        // when
        Double result = form.calculate(Calculator.Operation.POW).result();
        // then
        assertEquals(expected, result);
    }

    @Test
    void checkCalculateMul() {
        // given
        Double arg1 = 5.0;
        Double arg2 = 3.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        Double expected = 15.0;
        // when
        Double result = form.calculate(Calculator.Operation.MULT).result();
        // then
        assertEquals(expected, result);
    }

    @Test
    void checkCalculateDiv() {
        // given
        Double arg1 = 20.0;
        Double arg2 = 5.0;
        Calculator.Formula form = calc.newFormula().addOperand(arg1);
        form.addOperand(arg2);
        Double expected = 4.0;
        // when
        Double result = form.calculate(Calculator.Operation.DIV).result();
        // then
        assertEquals(expected, result);
    }

}
