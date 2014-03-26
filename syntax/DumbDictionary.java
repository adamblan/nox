import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class DumbDictionary {
    FileReader reader;

    public DumbDictionary() {
        try {
            reader = new FileReader("/usr/share/dict/words");
        }
        catch (IOException e) {
            
        Scanner scan = new Scanner(reader);
        String s = null;
        while (scan.hasNext()) {
            s = scan.next();
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        DumbDictionary d = new DumbDictionary();
    }
}
