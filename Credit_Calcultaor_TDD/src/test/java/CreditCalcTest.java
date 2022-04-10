import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class CreditCalcTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void CreditCalcSuccess() {
        // given
        int requestedAmount = 100;
        int interestRate = 5;
        int creditLength = 10;
        int expectedTotalCost = 150;
        int expectedOverPayment = 50;

        // when
        Credit credit1 = calculator.produceCredit(requestedAmount,interestRate,creditLength);
        int totalCost = credit1.getTotalCost();
        int OverPayment = credit1.getOverPayment();

        // then
        assertEquals(expectedTotalCost,totalCost);
        assertEquals(expectedOverPayment,OverPayment);
    }

    @Test
    public void IllegalRequestedAmount() {
        // given
        int requestedAmount = -1;
        int interestRate = 5;
        int creditLength = 10;

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.produceCredit(requestedAmount,interestRate,creditLength);
        });
    }

    @Test
    public void IllegalInterestRate() {
        // given
        int requestedAmount = 10;
        int interestRate = -5;
        int creditLength = 10;

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.produceCredit(requestedAmount,interestRate,creditLength);
        });
    }

    @Test
    public void IllegalCreditLength() {
        // given
        int requestedAmount = 10;
        int interestRate = 5;
        int creditLength = -10;

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.produceCredit(requestedAmount,interestRate,creditLength);
        });
    }
}
