package com.xg.server.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service  implements Runnable {
    private Socket socket;
    private BufferedReader in = null;
    private String msg = "";
    private static List<Socket> mList = new ArrayList<Socket>();
    
    public Service(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //客户端只要一连到服务器，便向客户端发送下面的信息。
            msg = "服务器地址：" +this.socket.getInetAddress() + "come toal:"
                +mList.size()+"（服务器发送）";
            this.sendmsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        try {
            while(true) {
                if((msg = in.readLine())!= null) {
                    //当客户端发送的信息为：exit时，关闭连接
                    if(msg.equals("exit")) {
                        System.out.println("ssssssss");
                        mList.remove(socket);
                        in.close();
                        msg = "user:" + socket.getInetAddress()
                            + "exit total:" + mList.size();
                        socket.close();
                        this.sendmsg();
                        break;
                        //接收客户端发过来的信息msg，然后发送给客户端。
                    } else {
                        msg = socket.getInetAddress() + ":" + msg+"（服务器发送）";
                        this.sendmsg();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  /**
   * 循环遍历客户端集合，给每个客户端都发送信息。
   */
   public void sendmsg() {
       System.out.println(msg);
       int num =mList.size();
       for (int index = 0; index < num; index ++) {
           Socket mSocket = mList.get(index);
           PrintWriter pout = null;
           try {
               pout = new PrintWriter(new BufferedWriter(
                       new OutputStreamWriter(mSocket.getOutputStream())),true);
               pout.println(msg);
           }catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}    