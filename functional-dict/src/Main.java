import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {

/*
    used final
    deterministic functions
    function goes as param to another function
*/

public static void main(String[] args) {
    final String phrase = "Hello my new friend. Welcome to our city.";

    Function<String, String> f1 = i -> i.replace(".", "");
    Function<String, String[]> f2 = i -> i.split(" ");

    String[] words = f2.compose(f1).apply(phrase);

    print(words);
    Arrays.sort(words);
    System.out.println("-- Sorted --");
    print(words);

    }

    public static void print(String[] words){
        for (String word: words) {
            System.out.println(word);
        }
    }
}
