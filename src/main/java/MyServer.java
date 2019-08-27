import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws Exception{
        ServerSocket server=new ServerSocket(5469);//建立一個ServerSocket在埠5469監聽客戶請求
        Socket client=server.accept();//使用accept()阻塞等待客戶請求
        BufferedReader is=new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter os=new PrintWriter(client.getOutputStream());
        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String str=is.readLine();
            System.out.println("Client : "+str);//在標準輸出上列印從Client讀入的字串

            os.println(sin.readLine());
            os.flush();//重新整理輸出流,使Client馬上收到該字串
            if(str.equals("end")){
                break;
            }
        }
        is.close();
        os.close();
        client.close();
        server.close();
    }
}