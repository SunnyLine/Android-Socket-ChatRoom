package com.xg.chat.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class ToastUtils {
    public static void makeToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void makeToast(Context context,@StringRes int msgId) {
        Toast.makeText(context, context.getText(msgId), Toast.LENGTH_SHORT).show();
    }
}
