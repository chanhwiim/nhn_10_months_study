import java.util.*;
import java.util.stream.*;

public class StreamSample {
    public static IntStream getInterger(int size) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return Arrays.stream(array);
    }

    public static Stream<Integer> getIntergerList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.stream();
    }

    public static Stream<Guiterist> getGuiterist() {
        List<Guiterist> list = new ArrayList<>();
        list.add(new Guiterist.Builder().no(1).name("Randy").teamName("Quite").guiter("Les paul").build());
        list.add(new Guiterist.Builder().no(2).name("Gimmy").teamName("Led").guiter("Les paul").build());
        list.add(new Guiterist.Builder().no(3).name("Ritch").teamName("Deep Purple").guiter("Stratocaster").build());

        return list.stream();
    }

}

class Test {
    public static void main(String[] args) {
        IntStream intStream = StreamSample.getInterger(100).filter(x -> x % 2 == 0).map(x -> x + 10);
        intStream.forEach(System.out::println);
        intStream.forEach(x -> System.out.println(x));

        Stream<Integer> list = StreamSample.getIntergerList(100).filter(x -> x % 2 == 0);
        list.forEach(System.out::println);

        Stream<Guiterist> list2 = StreamSample.getGuiterist().filter(x -> x.guiter == "Stratocaster");
        // .map(x -> x.guiter + " Type");
        list2.forEach(System.out::println);
    }
}
