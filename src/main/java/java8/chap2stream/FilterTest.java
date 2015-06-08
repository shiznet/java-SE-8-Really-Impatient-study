package java8.chap2stream;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by shiznet3908@gmail.com on 15/5/27.
 */
public class FilterTest {

    @Test
    public void filterwords(){
        List<String> words = new ArrayList<String>();
        Stream<String> stream = words.stream();
        Stream<String> filteredStream = stream.filter((word)-> word!=null && word.trim().length()>12);
        System.out.println(filteredStream.count());
    }
}
