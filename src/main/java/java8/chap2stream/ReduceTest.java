package java8.chap2stream;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by shiznet3908@gmail.com 15/6/8.
 */
public class ReduceTest {

    @Test
    public void paraReduceThreadUnsafe() {
        String words = "org.testng.annotations.Test";
        Stream<String> stream = Stream.of(words.split("\\.")).parallel();
        AtomicInteger result = stream.reduce(new AtomicInteger(0), (counter, str) -> {
                    System.out.println(
                            Thread.currentThread().getName() +
                                    "-counter:" + counter + " str:" + str);
                    if (str.length() > 3) {
                        counter.incrementAndGet();
                    }
                    return counter;
                },
                (counterA, counterB) -> {
                    //Shared between different threads.
                    System.out.println(
                            Thread.currentThread().getName() +
                                    "-counterA:" + counterA + " counterB:" + counterB);
                    return new AtomicInteger(counterA.get() + counterB.get());
                });
        System.out.println(result);
    }

    @Test
    public void paraReduce() {
        String words = "org.testng.annotations.Test";
        Stream<String> stream = Stream.of(words.split("\\.")).parallel();
        int result = stream.reduce(0, (counter, str) -> {
                    System.out.println(
                            Thread.currentThread().getName() +
                                    "-counter:" + counter + " str:" + str);
                    if (str.length() > 3) {
                        counter++;
                    }
                    return counter;
                },
                (counterA, counterB) -> {
                    //Shared between different threads.
                    System.out.println(
                            Thread.currentThread().getName() +
                                    "-counterA:" + counterA + " counterB:" + counterB);
                    return counterA + counterB;
                });
        System.out.println(result);
    }

}
