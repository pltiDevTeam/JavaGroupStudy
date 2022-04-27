import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Transform {
    public static void main(String[] args) {
        String[] data = {
                "java.nio와 그것의 서브 패키지는 java.io 패키지를 개선한 새로운 입출력 패키지이다. \n",
                "java.nio.file 패키지의 Path 인터페이스는 java.io 패키지의 File 클래스보다 풍부한 기능을 제공한다. \n",
                "Files 클래스는 실제 파일(또는 디렉터리) 조작과 읽기/쓰기 기능을 수행하는 static 메소드를 제공한다. \n",
                "java.nio.file의 FileChannel은 입출력을 모두 제공하고 기본적으로 버퍼링을 사용하며 멀티 스레드 환경에서도 안전하도록 설계되어 있다. \n",
                "java.nio.file 패키지의 WatchService는 디렉터리에 발생하는 이벤트를 감시하는 서비스이다. \n",
                "AsynchronousFileChannel 클래스를 이용하면 논-블로킹 방식 입출력을 지원하여 read(), write) 작업이 완료되지 않더라도 다른 작업을 동시에 수행할 수 있다. \n"
        };

        //운영체제가 사용하는 기본 문자 세트 객체 생성
        Charset cs = Charset.defaultCharset();
        //ByteBuffer 초기화 / 파일 채널은 ByteBuffer를 사용한다.
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);

        //Charset의 encode메소드는 String 데이터를 ByteBuffer 타입으로 리턴해준다.
        for(int i = 0; i < data.length; i++) {
            ByteBuffer tmp = cs.encode(data[i]);
            buffer = buffer.put(tmp);
        }

        //byte배열을 buffer의 크기로 초기화한다.
        //filp()메소드는 limit를 현재 position으로 설정하고, position을 0으로 초기화한다.
        byte[] b_total = new byte[buffer.flip().limit()];

        int index = 0;
        while(buffer.position() < buffer.limit()) {
            byte b = buffer.get();
            //fill(목적지, 시작인덱스, 끝인덱스, 데이터)
            Arrays.fill(b_total, index, index+1, b);
            index++;
        }

        StringBuffer sb = new StringBuffer();
        //wrap() 데이터를 ByteBuffer형으로 감싼다.
        //decode() ByteBuffer를 String으로 변환한다.
        sb.append(cs.decode(ByteBuffer.wrap(b_total)));
        System.out.println(sb);
    }
}
