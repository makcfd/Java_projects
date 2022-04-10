public class Main {

    /**
     *  credit amount
     *  time period
     *  rate
    * */

    public static void main(String[] args) {
        Credit credit = Calculator.produceCredit(10,10,10);
        System.out.println(credit);
    }

}
