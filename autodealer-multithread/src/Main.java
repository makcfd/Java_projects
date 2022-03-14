import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> manufacturedCars = new ArrayList<>();
        int MAX_CAPACITY = 5;

        Thread manufacturer = new Thread(new Manufacturer(manufacturedCars, MAX_CAPACITY), "Toyota");

        Thread buyer1 = new Thread(new Buyer(manufacturedCars, 2), "Buyer 1 ");
        Thread buyer2 = new Thread(new Buyer(manufacturedCars,1), "Buyer 2 ");
        Thread buyer3 = new Thread(new Buyer(manufacturedCars,3), "Buyer 3 ");

        buyer1.start();
        buyer2.start();
        buyer3.start();
        manufacturer.start();

    }
}
