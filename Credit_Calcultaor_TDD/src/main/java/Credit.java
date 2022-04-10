public class Credit {

    private int requestedAmount;
    private int interestRate;
    private int creditLength;

    private int totalCost;
    private int overPayment;

    public Credit(int requestedAmount, int interestRate, int creditLength) {
        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.creditLength = creditLength;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setOverPayment(int overPayment) {
        this.overPayment = overPayment;
    }

    public int getTotalCost() {
        return this.totalCost;
    }

    public int getOverPayment() {
        return this.overPayment;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "requested Amount= " + requestedAmount +
                ", interest Rate= " + interestRate +
                ", credit Length= " + creditLength +
                ", total Cost= " + totalCost +
                ", over Payment= " + overPayment +
                '}';
    }
}
