public class Main {
    public static void main(String[] args) {
        // demo 1 -> sum
        BinOps bins = new BinOps();
        String sumA = "0010"; // int 2
        String sumB = "0010"; // int 2

        // expecting to get binary 0100 which equals to int 4
        System.out.println("The sum equals to: " + bins.sum(sumA, sumB));

        // demo 2 -> mult
        String multA = "0011"; // int 3
        String multB = "0011"; // int 3

        // expecting to get binary 1001 which equals to int 9
        System.out.println("The mult equals to: " + bins.mult(multA, multB));

    }
}

