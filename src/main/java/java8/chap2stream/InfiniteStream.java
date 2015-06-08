package java8.chap2stream;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by hzliwenjia@corp.net ease.com on 15/5/27.
 */
public class InfiniteStream {

    @Test
    public void infititeStreamByGenerate(){
        Stream<String> generator = Stream.generate(()->"hello world");
        Iterator<String> iterator = generator.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void infitinieStreamWithIterate(){
        //first parameter is seed,second is the formula.f(now)=f(before) f(init)=seed
        Stream<BigInteger> generator = Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));
        Iterator<BigInteger> iterator = generator.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
