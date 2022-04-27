import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintDirNDS {
    public static void main(String[] args) throws Exception {
        Path path =  Paths.get("C:\\users\\kimis\\");

        DirectoryStream<Path> ds = Files.newDirectoryStream(path);
        for(Path p : ds) {
            recursionDir(p);
        }
    }

    static void recursionDir(Path p) {
        try {
            if (Files.isDirectory(p)) {
                System.out.println("[디렉토리] " + p.getFileName());
                DirectoryStream<Path> nds = Files.newDirectoryStream(p);
                for(Path np : nds) {
                    recursionDir(np);
                }
            } else {
                System.out.print("[파일] " + p.getFileName());

                System.out.println(" (" + Files.size(p) + ")");
            }
        } catch(Exception e) {
            System.out.printf("\n %s \n",e);
        }
    }
}
