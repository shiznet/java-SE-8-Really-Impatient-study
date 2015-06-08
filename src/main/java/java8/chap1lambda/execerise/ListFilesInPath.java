package java8.chap1lambda.execerise;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by shiznet3908@gmail.com on 15/5/25.
 */
public class ListFilesInPath {

    final String basedirector = "/Users/newcomer";

    @Test
    public void listFilesInPreJava8Style(){
        File file = new File("/Users/newcomer");
        FileFilter folderFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
        File[] subfolders = file.listFiles(folderFilter);
        for (File subfolder:subfolders){
            System.out.println(file.getPath());
        }
        System.out.println("debug");
    }


    @Test
    public void listFilesWithLambda(){
        File file = new File(basedirector);
        File[] subfolders = file.listFiles((checkedFile)->{return checkedFile.isDirectory();});
        for (File subfolder:subfolders){
            System.out.println(file.getPath());
        }
        System.out.println("debug");
    }

    @Test
    public void fileFilter(){
        File file = new File(basedirector);
        String extensionPattern = "rdb";
        File[] subfoldersWithoutSpecificExtension = file.listFiles((checkedFile)->{
            return checkedFile.isFile() && checkedFile.getPath().endsWith(extensionPattern);
        });
        for (File subfolder:subfoldersWithoutSpecificExtension){
            System.out.println(file.getPath());
        }
        System.out.println("debug");
    }


    /**
     * Q4
     * */
    @Test
    public void complexFilterCondition(){
        File file = new File(basedirector);
        //Directory first and then order by name.

    }
}
