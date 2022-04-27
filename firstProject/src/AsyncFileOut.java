import java.util.*;
import java.util.concurrent.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.nio.charset.*;

class Data {
    Path path;                      //java.nio.file 패키지, java.io 패키지의 file 클래스를 대신한다.
    AsynchronousFileChannel file;   //java.nio.channels 패키지, FileChannel 클래스의 비동기 버전이다.

    Data(Path p, AsynchronousFileChannel aFile) {
        path = p;
        file = aFile;
    }
}

public class AsyncFileOut {

    public static void main(String[] args) throws Exception {
        int processors = Runtime.getRuntime().availableProcessors();  //현재 사용가능한 프로세서 저장
        ExecutorService exec = Executors.newFixedThreadPool(processors); // Executor!

        for (int i = 0; i < 10; i++) {
            Path path = Paths.get(".\\txt\\temp\\file" + i + ".txt"); //파일 경로 지정
            Files.createDirectories(path.getParent()); //파일의 부모 디렉토리 생성

            //옵션을 열거형으로 선언
            EnumSet<StandardOpenOption> ops = EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            //비동기 채널 열기
            AsynchronousFileChannel file  = AsynchronousFileChannel.open(path,ops,exec);

            //데이터 객체 생성
            Data data = new Data(path, file);


            Charset charset = Charset.defaultCharset();
            ByteBuffer buffer = charset.encode("안녕하세요. 반가워요. 감사합니다.");

            //작업완료 핸들러 선언, 익명클래스제작
            CompletionHandler<Integer,Data> handler = new CompletionHandler<Integer, Data>() {

                //완료시 실행되는 메소드
                public void completed(Integer result, Data data) {
                    System.out.println(data.path.getFileName() + " : " + result + " bytes written : " + Thread.currentThread().getName());
                    try {
                        data.file.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                public void failed(Throwable exc, Data data) {
                    exc.printStackTrace();
                    try {
                        data.file.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            };

            file.write(buffer, 0, data, handler);

        }
            exec.shutdown();
    }
}