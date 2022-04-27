import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class FileTime {
    public class Main {
        public static void main(String[] args) {
            Path path = Paths.get(".\\src\\Main.java");

            System.out.println("C:\\Java\\FilesTest1.java");
            if(Files.isDirectory(path)) {
                System.out.println("It is Directory.");
            }
            if(Files.isRegularFile(path)) {
                System.out.println("It is Generic File.");
            }

            try {
                System.out.println("크기 : " + Files.size(path));
                System.out.println("소유자 : " + Files.getOwner(path).getName());
                if(Files.isHidden(path)) {
                    System.out.println("It is Hidden File.");
                }
                if(Files.isReadable(path)) {
                    System.out.println("It is readable File.");
                }
                if(Files.isWritable(path)) {
                    System.out.println("It is writable File.");
                }
                //FileTime을 LocalDateTime으로 변환하고, formatting함
                LocalDateTime ldt = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
                System.out.println("최종 수정 시간 : " + ldt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREAN)));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
