package java8.chap2stream.exercise;

import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by shiznet3908@gmail.com on 15/6/1.
 */
public class StreamAPIExercise {

    /**
     * 2.10
     * Write a call to reduce that can be used to compute the average of a Stream<Double>.
     * Why can’t you simply compute the sum and divide by count()?
     */
    @Test
    public void averageByReduce() {
        double[] array = {123.2, 123.12, 12.0, 123.34, 21312.123};
        Stream<Double> stream = DoubleStream.of(array).boxed();
        //Calculating average without getting the sum pre
        //f() = (lastavg*(counter-1)+n)/counter;counter++;
        final AtomicInteger counter = new AtomicInteger(1);
        Double result = stream.reduce(0.0, (avg, element) -> {
            avg = (avg * (counter.get() - 1) + element) / counter.get();
            counter.incrementAndGet();
            return avg;
        });
        System.out.println("abc");
        DoubleStream doubleStream = DoubleStream.of(array);
        double result_d = doubleStream.average().getAsDouble();
        System.out.println("debug");

    }

    /**
     * 2.9
     * Join all elements in a Stream<ArrayList<T>> to one ArrayList<T>.
     * Show how to do this with the three forms of reduce.
     * *
     */
    @Test
    public void joinStreamListToList() {
        ArrayList<String> lista = new ArrayList<String>(Arrays.asList("abc1", "bcd1"));
        ArrayList<String> listb = new ArrayList<String>(Arrays.asList("abc2", "bcd2"));
        ArrayList<String> listc = new ArrayList<String>(Arrays.asList("abc3", "bcd3"));
        ArrayList<String> listd = new ArrayList<String>(Arrays.asList("abc4", "bcd4"));
        Stream<ArrayList<String>> stream = Stream.of(lista, listb, listc, listd);
        ArrayList<String> result = null;
        result = joinFormA(stream);
//        result = joinFormB(stream);
//        result = joinFormB(stream);
        System.out.println("Debug");
    }

    protected <T> ArrayList<T> joinFormA(Stream<ArrayList<T>> stream) {
        ArrayList<String> targetList = new ArrayList<String>(
                Arrays.asList("abc", "bcd", "asd"));
        ArrayList<String> sourceList = new ArrayList<String>(
                Arrays.asList("abc1", "bcd1", "asd1"));
        //Form one:
        //reduce(BinaryOperator) : reduce((x,y)->func(x,y))
        // func(x,y) return the same type of x and y.And the
        //last result of func would be the input argue of this call.

        return stream.reduce((l1, l2) -> {
            l1.addAll(l2);
            return l1;
        }).orElse(new ArrayList<T>());
    }

    protected <T> ArrayList<T> joinFormB(Stream<ArrayList<T>> stream) {
        //Form two
        //reduce(FirstVal,BinaryOperator) : reduce(default,(x,y)->func(x,y))
        // func(x,y) return the same type of x and y.And the
        //last result of func would be the input argue of this call.
        //First round default and the first element will pass to func.
        return stream.reduce(new ArrayList<T>(), (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
    }

    protected <T> ArrayList<T> joinFormC(Stream<ArrayList<T>> stream) {
        //Form two
        //reduce(FirstVal,BinaryOperator) : reduce(default,(x,y)->func(x,y))
        // func(x,y) return the same type of x and y.And the
        //last result of func would be the input argue of this call.
        //First round default and the first element will pass to func.
        //There are two funcion.The first would handle different type of data.
        //The second one would process the reducing in parallel.
        return stream.reduce(new ArrayList<T>(),
                (a, b) -> {
                    a.addAll(b);
                    return a;
                },
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }


    /**
     * 2.8
     * Write a method
     * public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
     * that alternates elements from the streams first and second,
     * stopping when one of them runs out of elements.
     * *
     */
    @Test
    public void alternateStream() {

    }


    /**
     * 2.7
     * Your manager asks you to write a method
     * public static <T> boolean isFinite(Stream<T> chap2stream).
     * Why isn’t that such a good idea? Go ahead and write it anyway.
     * *
     */
    @Test
    public void checkFinite() {
        Stream stream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        boolean isFinit = checkFinite.isFinit(stream);
        Stream stream2 = Stream.of(1, 2, 3, 4, 5);
        isFinit = checkFinite.isFinit(stream2);
        System.out.println("debug");
    }

    /**
     * 2.6
     * The characterStream method in Section 2.3,
     * “The filter, map, and flatMap Methods,” on page 25,
     * was a bit clumsy, first filling an array list and
     * then turning it into a chap2stream. Write a chap2stream-based one-liner instead.
     * One approach is to make a chap2stream of integers from 0 to s.length() - 1 and
     * map that with the s::charAt method reference.
     */
    @Test
    public void onelineStream() {
        String str = "absdfsdfsd";
        final int counter = 0;
        //str -> int
        Stream<Character> charstream = IntStream.range(0, str.length()).
                mapToObj(Integer::new).map(str::charAt);
        Iterator<Character> chars = charstream.iterator();
        while (chars.hasNext()) {
            System.out.println(chars.next());
        }
        System.out.println("debug");
    }


    /**
     * 2.5
     * Infinite generator
     */
    @Test
    public void infiniteGenerator() {
        //xn + 1 = (a xn + c) % m
        // Try out a = 25214903917, c = 11, and m = 248.
        long c = 11L;
        long m = 1L << 48;
        long a = 25214903917L;
        Stream<Long> generator = Stream.iterate(0L, x -> (a * x + c) % m);
        Iterator<Long> iterator = generator.iterator();
        for (int i = 0; i < 20; i++) {
            System.out.println(iterator.next());
        }
        System.out.println("debug");

    }

    /*
    * 2.4
    * Primitive type Stream
    * Treat primitiveArray as one object!
    * **/
    @Test
    public void primitiveStream() {
        int[] intarray = {1, 2, 3, 4, 5};
        Stream stream = Stream.of(intarray);
        Iterator iterator = stream.iterator();
        System.out.println("debug");
        IntStream intStream = IntStream.of(intarray);
        IntSummaryStatistics summaryStatistics = intStream.summaryStatistics();
        iterator = intStream.iterator();
        System.out.println("debug");
    }

    /**
     * 2.3
     * Parallel vs Sequential
     * About 30
     * 1433153408808
     * 1433153408921:32908 8921-8808=113
     * 1433153408922
     * 1433153408955:32908 8955-8922=33
     * Core size = 4
     * *
     */
    @Test
    public void paravsseq() throws IOException {
        String filepath = "/Users/newcomer/Desktop/test.txt";
        Stream<String> lines = Files.lines(Paths.get(filepath), StandardCharsets.ISO_8859_1);
        System.out.println(System.currentTimeMillis());
        long wordcount = lines.filter(w -> w.length() > 5).count();
        System.out.println(System.currentTimeMillis() + ":" + wordcount);
        lines = Files.lines(Paths.get(filepath), StandardCharsets.ISO_8859_1);
        System.out.println(System.currentTimeMillis());
        wordcount = lines.parallel().filter(w -> w.length() > 5).count();
        System.out.println(System.currentTimeMillis() + ":" + wordcount);
        System.out.println("debug");
    }


    /**
     * 2.2
     * *
     */
    @Test
    public void lazyIterator() {
        String content = "Write a parallel version of the for loop in Section 2.1";
        Stream<String> stream = Stream.of(content.split(" "));
        //No output.
//      chap2stream.peek(System.out::println).limit(5);
        //Peek defines the action when element peeked from chap2stream.
        String[] first = stream.peek(System.out::println).limit(5).toArray(String[]::new);
        //Sout triggered by toArray method.
        System.out.println("debug");
    }


    /**
     * 2.1
     * Write a parallel version of the for loop in Section 2.1,
     * “From Iteration to Stream Operations,” on page 22.
     * Obtain the number of processors.
     * Make that many separate threads, each working on
     * a segment of the list, and total up the results as they come in.
     * (You don’t want the threads to update a single counter. Why?)
     * **
     */
    @Test
    public void parallelVersionOfLoop() throws InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        String contents = new String("Write a parallel version of the for loop in Section 2.1,"); // Read file into string
        List<String> list = Arrays.asList(contents.split(" "));
        int length = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(processors);
        for (int i = 0; i < processors; i++) {
            if (i == processors - 1) {
                executorService.execute(
                        new wordcounter(
                                list, i * length / processors,
                                length, counter,
                                latch
                        )
                );
            } else {
                executorService.execute(
                        new wordcounter(
                                list, i * length / processors,
                                (i + 1) * length / processors, counter
                                , latch
                        )
                );
            }
        }
        latch.await();
        System.out.println(counter);
        executorService.shutdown();
    }
}

class wordcounter implements Runnable {

    List<String> list;
    int start;
    int end;
    AtomicInteger counter;
    CountDownLatch latch;

    wordcounter(List<String> list, int start, int end, AtomicInteger counter, CountDownLatch latch) {
        this.list = list;
        this.start = start;
        this.end = end;
        this.counter = counter;
        this.latch = latch;
        if (start < 0 || start >= end || list == null || list.size() < end) {
            throw new IllegalArgumentException("Illegal param.start=" + start + ",end=" + end);
        }
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (list.get(i) != null && list.get(i).length() > 2) {
                counter.incrementAndGet();
            }
        }
        latch.countDown();
    }
}


class checkFinite {

    public static ThreadLocal<Integer> counter = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static <T> boolean isFinit(Stream<T> stream) {
        long count = stream.count();
        Integer c = counter.get();
        stream.peek(checkFinite::isFinita).limit(2L);
        c = counter.get();

        return c > 0;
    }

    public static <T> void isFinita(T t) {
        Integer c = counter.get();
        c++;
        counter.set(c);
    }
}

class zipper {
    //Ugly way to alternatively iterate two Stream.
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iteratorfirst = first.iterator();
        Iterator<T> iteratorsecond = second.iterator();
        Collection<T> collection = Collections.EMPTY_LIST;
        for (int i = 0; ; i++) {
            if ((i & 1) == 0) {
                if (iteratorfirst.hasNext()) {
                    collection.add(iteratorfirst.next());
                    continue;
                }
                if (iteratorsecond.hasNext()) {
                    collection.add(iteratorsecond.next());
                    continue;
                }
                break;
            }
            if (iteratorsecond.hasNext()) {
                collection.add(iteratorsecond.next());
                continue;
            }
            if (iteratorfirst.hasNext()) {
                collection.add(iteratorfirst.next());
                continue;
            }
            break;
        }
        return collection.stream();
    }

    public static <T> Stream<T> zipWithStream(Stream<T> first, Stream<T> second) {
        return null;
    }
}