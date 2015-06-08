package java8.chap1lambda;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by shiznet3908@gmail.com on 15/6/2.
 */
public class CollectionHelper<T> {

    public Collection<T> merge(Collection<T> ...collections){
        Stream stream = Stream.of(collections);
        return null;
    }

    public Collection<T> merge(Collection<T> t1,Collection<T> t2){
        t1.addAll(t2);
        return t1;
    }

}
