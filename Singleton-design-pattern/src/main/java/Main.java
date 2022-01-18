import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // get instance of logger
        Logger logger = Logger.getInstance();
        logger.log("Start the program...");

        // firts request of data input
        logger.log("Request first data input...");
        System.out.println("Please enter the size of an array: ");
        Scanner inputSize = new Scanner(System.in);
        int size = inputSize.nextInt();
        List<Integer> elementsToFilter = new ArrayList<Integer>(size);

        // second request of data input and fullfilling an array with random int values
        logger.log("Request second data input...");
        System.out.println("Please enter maximum possible number in the array: ");
        Scanner inputMaxNumber = new Scanner(System.in);
        int maxNumber = inputMaxNumber.nextInt();
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int value = random.nextInt(maxNumber);
            elementsToFilter.add(value);
        }

        // third request for data input

        Logger logger2 = Logger.getInstance();
        logger2.log("Request third data input...");

        System.out.println("Please enter a number to filter the array: ");
        Scanner inputFilter = new Scanner(System.in);
        int filterNum = inputFilter.nextInt();

        // filtering out and printing results
        Filter filter = new Filter(filterNum);
        System.out.println("Array to filter: " + elementsToFilter);
        List<Integer> filteredArray = filter.filterOut(elementsToFilter);
        logger.log("Print result to the console...");
        System.out.println("Filtered array: " + filteredArray);

        logger.log("Finishing the program execution...");
    }
}
