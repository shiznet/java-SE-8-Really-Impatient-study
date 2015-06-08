package java8.chap2stream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shiznet3908@gmail.com on 15/5/25.
 */
public class StreamAPITest {


    @Test(dataProvider = "words")
    public void wordCount(List<String> words){
        int count = 0;
        for(String word:words){
            if(word.length()>12)count++;
        }
        System.out.println(count);
    }

    @Test(dataProvider = "words")
    public void wordCountWithLambda(List<String> words){
        long count = words.stream().filter((word)->word.length()>12).count();
    }

    @DataProvider(name = "words")
    public Object[][] getwordsFromFile(){
        try {
            //Utility class: Files Paths StandardCharsets
            String contents = new String(Files.readAllBytes(Paths.get("")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            return new Object[][]{
                    new Object[]{words}
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
