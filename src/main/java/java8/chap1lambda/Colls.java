package java8.chap1lambda;

import java.util.*;
import java.util.function.Function;

public abstract class Colls {
    public static <T, R> List<R> map(Collection<T> coll, Function<T, R> transformer) {
        List<R> result = new ArrayList<>(coll.size());
        for (T t : coll) {
            result.add(transformer.apply(t));
        }
        return result;
    }

    @SafeVarargs
    public static <T> List<T> copy(Collection<T>... colls) {
        List<T> list = new ArrayList<>();
        for (Collection<T> coll : colls) {
            list.addAll(coll);
        }
        return list;
    }

    @SafeVarargs
    public static <T> List<T> copySort(Comparator<T> comparator, Collection<T>... colls) {
        List<T> list = copy(colls);
        Collections.sort(list, comparator);
        return list;
    }


}
class merge{
    public Collection mergeCollection(Collection c1,Collection c2){
        if (c1!=null ){
            if (c2!=null){
                c2.addAll(c1);
                return c2;
            }
            return c1;
        }
        return c2;
    }
}