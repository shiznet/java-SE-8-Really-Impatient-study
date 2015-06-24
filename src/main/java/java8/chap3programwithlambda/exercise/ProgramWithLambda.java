package java8.chap3programwithlambda.exercise;

import org.testng.annotations.Test;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Created by shiznet3908@gmail.com 15/6/16.
 */
public class ProgramWithLambda {

    /**
     * 3.1
     * Enhance the lazy logging technique by providing conditional logging.
     * A typical call would be
     * logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]).
     * Don’t evaluate the condition if the logger won’t log the message.
     */
    @Test public void deferedExecutionTest() {
        //logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]).
        //logIf((Level)->Level>effectiveLevelInt,(msg,arg)->concatenate msg)
        //logIf(Predicate<Integer> predict,BiConsumer<String,Object[]> biconsumer);
    }

    protected void logIf(int level,String msg,Object[] params, Predicate<Integer> predicate,
        BiConsumer<String, Object[]> biConsumer) {
        if (!predicate.test(level)) {
            return;
        }
        biConsumer.accept(msg,params);
    }
}


class Logger {

    /**
     * debug 5
     * info 4
     * warn 3
     * error 2
     **/
    public void info(String msg, Object[] vars) {
        logIf(4, msg, vars, (level) -> {
            return level < 4;
        }, (coarsemsg, params) -> {
        });
    }

    public void logIf(int level, String msg, Object[] vars, Predicate<Integer> predicate,
        BiConsumer<String, Object[]> biConsumer) {
        if (!predicate.test(level)) {
            return;
        }
        biConsumer.accept(msg, vars);
    }

}
