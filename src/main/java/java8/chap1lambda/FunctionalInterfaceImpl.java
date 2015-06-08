package java8.chap1lambda;

/**
 * Created by shiznet3908@gmail.com on 15/5/25.
 */
public class FunctionalInterfaceImpl implements FunctionalInterfaceA {

    @Override
    public void func(String abc) throws InterruptedException {
        System.out.println(abc);
    }
}
