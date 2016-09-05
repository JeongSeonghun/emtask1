package wgl.example.com.emtask1;
//http://mainia.tistory.com/1093 참고

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class OSInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView SYSinfo = (TextView) findViewById(R.id.ostext);
        SYSinfo.setText(ReadSYSinfo());
    }
    //System.getProperty() 이용, 터미널에서 시스템 명령어를 실행하는 것과 같은 효과,
    //getProperty() 만든 함수, 텍스트뷰에 출력할 text로 만듬
    private static StringBuffer SYSinfoBuffer;// 저장버퍼

    private String ReadSYSinfo()
    {
        SYSinfoBuffer = new StringBuffer();

        getProperty("os.name", "os.name", SYSinfoBuffer);
        getProperty("os.version", "os.version", SYSinfoBuffer);

        getProperty("java.vendor.url", "java.vendor.url", SYSinfoBuffer);
        getProperty("java.version", "java.version", SYSinfoBuffer);
        getProperty("java.class.path", "java.class.path", SYSinfoBuffer);
        getProperty("java.class.version", "java.class.version", SYSinfoBuffer);
        getProperty("java.vendor", "java.vendor", SYSinfoBuffer);
        getProperty("java.home", "java.home", SYSinfoBuffer);

        getProperty("user.name", "user.name", SYSinfoBuffer);
        getProperty("user.home", "user.home", SYSinfoBuffer);
        getProperty("user.dir", "user.dir", SYSinfoBuffer);

        return SYSinfoBuffer.toString();
    }

    private void getProperty(String desc, String property, StringBuffer tBuffer)
    {
        tBuffer.append(desc);//명칭
        tBuffer.append(" : ");
        tBuffer.append(System.getProperty(property));//값
        tBuffer.append("\n");
    }

    //OS정보
    private String ReadOSinfo() {
        ProcessBuilder cmd; //명령어 실행용도
        String result = "";

        try {
            String[] args = { "/system/bin/cat", "/proc/version" };
            cmd = new ProcessBuilder(args);

            Process process = cmd.start(); //명령어 실행

            InputStream in = process.getInputStream(); //명령어 실행 결과 저장
            byte[] re = new byte[1024];
            //한줄씩 읽어서 저장
            while (in.read(re) != -1) {
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {  //입축력 예외
            ex.printStackTrace();   //애러 출력
        }
        return result;
    }
}
