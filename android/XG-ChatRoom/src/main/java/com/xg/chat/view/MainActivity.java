package com.xg.chat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xg.chat.R;
import com.xg.chat.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.etHost)
    EditText etHost;
    @Bind(R.id.etProt)
    EditText etProt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnAdd)
    public void onClick(View v) {
        String host = etHost.getText().toString().trim();
        String prot = etProt.getText().toString().trim();
        if (TextUtils.isEmpty(host) || TextUtils.isEmpty(prot)) {
            ToastUtils.makeToast(this, "信息不能为空！");
            return;
        }
        Intent intent = new Intent(this, ChatRoomActivity.class);
        intent.putExtra("host", host);
        intent.putExtra("prot", prot);
        startActivity(intent);
    }
}
