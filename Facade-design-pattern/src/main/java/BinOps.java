public class BinOps {
    public String sum(String a, String b) {

        // binary to int
        int numA = Integer.parseInt(a, 2);
        int numB = Integer.parseInt(a, 2);
        // execute math operation
        int numC = numA + numB;
        // int to binary
        String c = Integer.toBinaryString(numC);

        return c;
    }

    public String mult(String a, String b) {

        // binary to int
        int numA = Integer.parseInt(a, 2);
        int numB = Integer.parseInt(a, 2);
        // execute math operation
        int numC = numA * numB;
        // int to binary
        String c = Integer.toBinaryString(numC);

        return c;
    }
}