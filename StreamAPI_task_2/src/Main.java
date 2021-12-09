import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // 1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).


        int youngCount = (int) persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Number of teenagers in the collection: " + youngCount);

        // 2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).


        List<Person> recruits = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .collect(Collectors.toList());

        System.out.println("Number of recruits: " + recruits.size());
        System.out.println("Example of recruit: " + recruits.get(3));

        // 3. Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        //    в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).

        List<Person> potentialWorker = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex().equals(Sex.MAN) || person.getSex().equals(Sex.WOMAN) && person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Number of potential workers: " + potentialWorker.size());
        System.out.println("Example of potential worker" + potentialWorker.get(3));
    }
}
