package java8.chap1lambda;

import org.testng.annotations.Test;

import java.lang.*;
import java.util.Comparator;

/**
 * Created by shiznet3908@gmail.com on 15/5/24.
 */
public class SimpleLambdaTest {

    ButtonListenerInterface listener;

    public static void main(String[] args) {
        //Compator is a functional interface.
        //(first,second) variable type could be ignored.For compiler can infer the type from the interface decleration.
//        Comparator<String> compator = (first,second)->Integer.compare(first.length(),second.length());
//        List<String> list = new ArrayList<>();//Type deduce.
//        Collections.sort(list,compator);
        SimpleLambdaTest test = new SimpleLambdaTest();
        test.addListener(event->{
            System.out.println("Process event:" + event.toString());
        });
        Event e =new Event();
        e.setEventName("test event");
        test.eventHappened(e);
    }

    private void addListener(ButtonListenerInterface buttonListenerInterface){
        this.listener = buttonListenerInterface;
    }

    private void eventHappened(Event e){
        listener.callback(e);
    }

    private void conditionExpression(){
        //Use brace to
        //Every condition should have its own return value.
        //anonymous function
        Comparator<String> comparator = (first,second)->{if(first==null || second==null){
            throw new NullPointerException("");
        }else{
            int min = first.length()<second.length()?first.length():second.length();
            for (int idx = 0;idx<min;idx++){
                if (first.charAt(idx)>second.charAt(idx)){
                    return 1;
                }else if(first.charAt(idx)<second.charAt(idx)){
                    return -1;
                }
            }
            if(first.length()>second.length()){
                return 1;
            }else if (first.length()<second.length()){
                return -1;
            }
            return 0;
        }};
    }

    private void funcA(FunctionalInterfaceA interfaceA){
        final String abc = "abc";
        try {
            interfaceA.func(abc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void funcB(FunctionalInterfaceB interfaceB){
        interfaceB.func("interfaceB");
    }

    @Test
    public void lambdaExpressionBindedWithFunctionalInterfaceTest(){
//        FunctionalInterfaceImpl func = (str)-> {System.out.println(str);Thread.sleep(1000);};
        FunctionalInterfaceA func = (str) -> {
            System.out.println(str);
            str = "new str";
            Thread.sleep(100);
        };
        funcA(func);
        System.out.println("debug");
    }


    @Test
    public void mutateTest(){
        int ct = 0;
        counter c = (cnt)->{
            cnt++;
            cnt = ct;
            //ct++; //Mutate an
            System.out.println(cnt);
        };
    }

}
@java.lang.FunctionalInterface
interface counter{
    public void count(int cnt);
}
