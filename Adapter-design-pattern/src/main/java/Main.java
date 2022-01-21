public class Main {
    public static void main(String[] args) {

        // instantiated object by using default constructor
        // default because constructor was not explicitly defined within the Calculator class

        // original usage of Calculator class
        Calculator calc = new Calculator();
        System.out.println(
                calc.newFormula()
                        .addOperand(5)
                        .addOperand(15)
                        .calculate(Calculator.Operation.MULT)
                        .result()
        );

        // adapted usage of Calculator class
        /**
         * to implement adopted usage we create an adanper InrsCalculator
         * the difference is:
         *  - data type returned. double -> int casting
         *  - the way arguments passed: in adopted version arguments passed directly to calculation method
         *      while in ariginal arguments passed separately and stored in internal class
         *  - the way resulted calculation is invoked original version operation passed separately and calculation
         *      calculated afterwards so original version is more flexible, while adopted has predefined methods
         *      to compute result
         **/

        Ints intsCalc = new IntsCalculator();
        System.out.println(intsCalc.sum(2, 2));
        System.out.println(intsCalc.sum(10, 22));
        System.out.println(intsCalc.pow(2, 10));

    }
}
