package com.xg.chat.view;

import com.xg.chat.bean.ChatBean;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public interface ChatView {

    public String getHost();
    public String getProt();
    public String getUserId();
    public void showDiaolg(String msg);
    public void receiveMsg(ChatBean bean);
}
