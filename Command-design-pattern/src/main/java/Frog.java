public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() { position = 5; }

    public boolean jump(int steps) {
        // do jump, but if the jupm is too big
        // no jump will be made and method will return false
        if (position+steps>MAX_POSITION || position+steps < MIN_POSITION) {
            return false;
        }
        else {
            this.position = position + steps;
            return true;
        }
    }
}