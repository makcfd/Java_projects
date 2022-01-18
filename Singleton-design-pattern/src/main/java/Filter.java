import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    protected int f;

    public Filter (int f) {
        this.f = f;
    }

    public List<Integer> filterOut(List<Integer> source) {
        Logger logger = Logger.getInstance();
        logger.log("Start filtering the array...");

        List<Integer> result = source.stream()
                                      .filter(c -> {

                                          if (c < f) {
                                              logger.log("element " + String.valueOf(c) + " passed");
                                              return true;}
                                          else {
                                              logger.log("element " + String.valueOf(c) + " not passed");
                                              return false;}
                                      })
                                      .collect(Collectors.toList());

        // logging overall results of filtering process
        logger.log("Overall passed " + result.size() + " elements out of " + source.size());

        return result;
    }
}
