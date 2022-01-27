public class Main {

    public static void main(String[] args) {
        for (int r : new Randoms(90, 100)) {
            System.out.println("We got rundom number: " + r);
            if (r == 100) {
                System.out.println("100 is our match, let's end the game...");
                break;
            }
        }
    }
}
