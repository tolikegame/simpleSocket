import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        //向本機的5469埠發出客戶請求
        Socket socket=new Socket(InetAddress.getLocalHost(),5469);
        //由Socket物件得到輸入流,並構造相應的BufferedReader物件
        BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //由Socket物件得到輸出流,並構造PrintWriter物件
        PrintWriter os=new PrintWriter(socket.getOutputStream());
        //由系統標準輸入裝置構造BufferedReader物件
        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String str=sin.readLine();//從系統標準輸入讀入一字串
            os.println(str);
            os.flush(); //重新整理輸出流,使Server馬上收到該字串

            String s=is.readLine();
            System.out.println("Server : "+s);//在標準輸出上列印從Server讀入的字串
            if(str.equals("end")){
                break;
            }
        }
        is.close();//關閉Socket輸入流
        os.close();//關閉Socket輸出流
        socket.close();//關閉Socket
    }
}
