package java8.chap1lambda;

import org.testng.annotations.Test;

/**
 * Created by shiznet3908@gmail.com on 15/6/1.
 */
public class LambdaReuse {


    @Test
    public void reuse(){

    }
}
class clazz<T>{

    T t;

    clazz(T t) {
        this.t = t;
    }

    public void cal(func1<T> func){
        func.add(this.t);
    }
}
interface func1<T>{
    void add(T t);
}
interface func2<T>{
    void addnew(T t);
}
