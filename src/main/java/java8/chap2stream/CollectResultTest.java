package java8.chap2stream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by shiznet3908@gmail.com on 15/6/1.
 */
public class CollectResultTest {

    Stream<String> stream;

    @BeforeClass
    public void setup() {
        String[] words = "org.testng.annotations.BeforeClass".split("\\.");
        stream = Stream.of(words).parallel();
        stream = stream.filter((str) -> str.length() > 3);
    }

    @Test
    public void toArray() {
        String[] result = stream.toArray(String[]::new);
        Stream.of(result).forEach(System.out::println);
    }


    @Test
    public void toHashSet() {
        HashSet<String> result = stream.collect(HashSet<String>::new, HashSet::add, HashSet::addAll);
        System.out.println(result);
    }
}
