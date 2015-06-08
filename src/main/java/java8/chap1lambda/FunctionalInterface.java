package java8.chap1lambda;

/**
 * Created by shiznet3908@gmail.com on 15/5/24.
 */
@java.lang.FunctionalInterface
public interface FunctionalInterface {

    //Must declare an abstract func,bud can only declare one abstract func
    public void abstractFunc();

    //Use default to declare concrete func
    default void concreteFunc(){
        System.out.println("concrete func");
    }

//    public void absFunc();

    //Object's func can be redeclared.
    boolean equals(java.lang.Object o);
}
