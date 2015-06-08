package java8.chap1lambda;

/**
 * Created by shiznet3908@gmail.com on 15/5/25.
 */
public class NonFunctionalInterfaceContainsDefaultMethodTest {

}

interface nonfunctionInterface{
    public void methodA();

    default void methodB(){
        methodA();
    }
}
