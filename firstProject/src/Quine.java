import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Quine {
    public static void main(String[] args) {
        try {
            File file = new File(".\\FirstProject\\src\\Quine.java");
            InputStream is = new FileInputStream(file);
            int nData = is.read();
            while(nData != -1) {
                System.out.write(nData);
                nData = is.read();
            }
            is.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}