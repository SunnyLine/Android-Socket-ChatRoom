package com.xg.chat.bean;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public class ChatBean {
    public String content;
    public String name;
    public long time;

    public ChatBean(String content, String name) {
        this.content = content;
        this.name = name;
        time = System.currentTimeMillis();
    }
}
