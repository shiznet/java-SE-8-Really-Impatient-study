package java8.newinterfacesyntax;

import org.testng.annotations.Test;

/**
 * Created by shiznet3908@gmail.com on 15/6/1.
 */
public class MultiInheritTest {

    @Test
    public void multiinherit(){
        concreteClass cs = new concreteClass();
        cs.func2();
    }

}
interface itf1{
    default public void func1(){
        System.out.println("func1 in itf1");
    }
}

interface itf2{
    default public void func2(){
        System.out.println("func2 in itf2");
    }
}

class concreteClass implements itf1,itf2{

}