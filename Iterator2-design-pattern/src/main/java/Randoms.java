import java.util.Iterator;
import java.util.Random;


public class Randoms implements Iterable<Integer> {

    protected Random random;
    protected int min;
    protected int max;

    // define constructor params
    public Randoms(int min, int max) {
        this.min = min;
        this.max = max;
    }

    // implement iterator protocol
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                // because we want to iterate forever we must always have next element
                // so we must always return true
                return true;
            }

            @Override
            public Integer next() {
                // return next random value in a given range
                random = new Random();
                int i = random.nextInt((max - min) + 1) + min;;
                return i;
            }
        };
    }
}