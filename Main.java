import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class Main {
    // Task 1
    public static String formatNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(i -> (i + 1) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    // Task 2
    public static List<String> uppercaseAndSort(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // Task 3
    public static String extractAndSortNumbers(String[] inputArray) {
        String numbers = Arrays.stream(inputArray)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return numbers;
    }

    // Task 4
    public static Stream<Long> linearCongruentGenerator(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    // Task 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        Stream.Builder<T> resultBuilder = Stream.builder();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            resultBuilder.accept(iterator1.next());
            resultBuilder.accept(iterator2.next());
        }

        return resultBuilder.build();
    }

    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Peter", "John", "Alice");
        System.out.println(formatNames(names));

        List<String> strings = List.of("apple", "banana", "cherry");
        System.out.println(uppercaseAndSort(strings));

        String[] inputArray = {"1, 2, 0", "4, 5"};
        System.out.println(extractAndSortNumbers(inputArray));

        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = 42L;
        linearCongruentGenerator(a, c, m, seed)
                .limit(10)
                .forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5, 6, 7);
        zip(stream1, stream2)
                .forEach(System.out::println);
    }
}
