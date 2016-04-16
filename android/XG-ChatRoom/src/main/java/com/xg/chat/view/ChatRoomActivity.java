package com.xg.chat.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.xg.chat.R;
import com.xg.chat.bean.ChatBean;
import com.xg.chat.socket.SocketThread;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatRoomActivity extends AppCompatActivity implements ChatView {
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.etContent)
    EditText etContent;
    private ChatAdapter mAdapter;

    private String host;
    private String prot;


    private String userId = String.valueOf(System.currentTimeMillis());

    private SocketThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        host = intent.getStringExtra("host");
        prot = intent.getStringExtra("prot");

        mAdapter = new ChatAdapter(this, userId);
        listView.setAdapter(mAdapter);


        //启动线程，接收服务器发送过来的数据
        thread = new SocketThread(this);
        thread.start();
    }

    /**
     * 如果连接出现异常，弹出AlertDialog！
     */
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("notification").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @OnClick(R.id.btnSend)
    public void onClick(View view) {
        String msg = etContent.getText().toString();
        etContent.setText(null);
        thread.sendMsg(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.sendMsg("exit");
        thread.interrupt();
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getProt() {
        return prot;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void showDiaolg(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        }).show();
    }

    @Override
    public void receiveMsg(ChatBean bean) {
        mAdapter.addChatBean(bean);
    }
}
