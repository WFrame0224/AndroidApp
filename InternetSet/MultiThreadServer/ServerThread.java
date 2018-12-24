import java.io.*;
import java.net.*;
import java.util.*;

// 负责处理每个线程通信的线程类
public class ServerThread implements Runnable {
    // 定义当前线程处理的Socket
    Socket s = null;
    // 该线程所处理的Socket所对应的输入流
    BufferedReader br = null;

    public ServerThread(Socket s) throws IOException {
        this.s = s;
        // 初始化该Socket对应的输入流
        br = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
    }

    public void run(){
        try{
            String content = null;
            // 采用循环不断从Socket中读取客户端发送过来的数据
            while((content = readFromClinet()) != null){
                System.out.println("----" + Arrays.toString(content.getBytes("utf-8")));
                System.out.println("----" + content);
                // 遍历socketList中每个Socket
                // 将读到的内容向每个Socket发送一次
                for(Iterator<Socket> it = MyServer.socketList.iterator(); it.hasNext();){
                    Socket s = it.next();
                    try{
                        OutputStream os = s.getOutputStream();
                        os.write((content + "\n").getBytes("utf-8"));
                    }catch(SocketException e){
                        e.printStackTrace();
                        // 删除该Socket
                        it.remove();
                        System.out.print(MyServer.socketList);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // 定义读取客户端数据的方法
    private String readFromClinet() {
        try {
            return br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            // 删除该Socket
            MyServer.socketList.remove(s);
        }
        return null;
    }
}