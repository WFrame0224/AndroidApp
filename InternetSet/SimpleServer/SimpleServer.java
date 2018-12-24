import java.net.*;
import java.io.*;

/**
 * 这是服务器端的代码，运行于PC端，用于为手机端提供网络服务
 */
public class SimpleServer{
    public static void main(String[] args) throws IOException{
        // 创建一个ServerSocket，用于监听客户端Socket的连接请求
        ServerSocket ss = new ServerSocket(3000);
        // 采用循环不断接收来自客户端的请求
        while(true){
            // 每当接收到客户端的Socket的请求，服务器端也对应产生一个Socket
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            os.write(" 您好，祝您圣诞节快乐 Merry Christmas!!!\n ".getBytes("utf-8"));
            // 关闭输出流，关闭Socket
            os.close();
            s.close();
        }
    }
}