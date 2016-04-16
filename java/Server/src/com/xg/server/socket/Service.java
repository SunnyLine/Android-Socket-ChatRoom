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
            //�ͻ���ֻҪһ����������������ͻ��˷����������Ϣ��
            msg = "��������ַ��" +this.socket.getInetAddress() + "come toal:"
                +mList.size()+"�����������ͣ�";
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
                    //���ͻ��˷��͵���ϢΪ��exitʱ���ر�����
                    if(msg.equals("exit")) {
                        System.out.println("ssssssss");
                        mList.remove(socket);
                        in.close();
                        msg = "user:" + socket.getInetAddress()
                            + "exit total:" + mList.size();
                        socket.close();
                        this.sendmsg();
                        break;
                        //���տͻ��˷���������Ϣmsg��Ȼ���͸��ͻ��ˡ�
                    } else {
                        msg = socket.getInetAddress() + ":" + msg+"�����������ͣ�";
                        this.sendmsg();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  /**
   * ѭ�������ͻ��˼��ϣ���ÿ���ͻ��˶�������Ϣ��
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