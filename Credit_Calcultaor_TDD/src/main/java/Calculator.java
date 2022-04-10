public class Calculator {

    public static Credit produceCredit(int requestedAmount, int interestRate, int creditLength) {
        Credit credit;
        if (creditLength < 1 || interestRate < 1 || requestedAmount <1) {
            throw new IllegalArgumentException("Illegal credit param value. cannot be less than 1");
        } else {
            credit = new Credit(requestedAmount,interestRate,creditLength);
            int totalCost = requestedAmount + requestedAmount * creditLength * interestRate/100;
           int overPayment = totalCost - requestedAmount;
           credit.setTotalCost(totalCost);
           credit.setOverPayment(overPayment);

        }
        return credit;
    }
}

