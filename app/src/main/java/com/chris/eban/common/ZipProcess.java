package com.chris.eban.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.chris.eban.R;

import org.jetbrains.annotations.NotNull;

public class ZipProcess {

    /*
     0 No error
     1 Warning (Non fatal error(s)). For example, one or more files were locked by some other application,
       so they were not compressed.
     2 Fatal error
     7 Command line error
     8 Not enough memory for operation
     255 User stopped the process
  */
    private static final int RET_SUCCESS = 0;
    private static final int RET_WARNING = 1;
    private static final int RET_FAULT = 2;
    private static final int RET_COMMAND = 7;
    private static final int RET_MEMORY = 8;
    private static final int RET_USER_STOP = 255;

    private Context context;
    private Thread thread;
    private ProgressDialog dialog;
    private Handler handler;
    private String command;

    public ZipProcess(Context context, String command) {
        // TODO Auto-generated method stub
        this.context = context;
        this.command = command;

        dialog = new ProgressDialog(context);
        dialog.setTitle(R.string.progress_title);
        dialog.setMessage(context.getText(R.string.progress_message));
        dialog.setCancelable(false);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NotNull Message msg) {
                // TODO Auto-generated method stub
                dialog.dismiss();

                int retMsgId = R.string.msg_ret_success;
                switch (msg.what) {
                    case RET_SUCCESS:
                        retMsgId = R.string.msg_ret_success;
                        break;
                    case RET_WARNING:
                        retMsgId = R.string.msg_ret_warning;
                        break;
                    case RET_FAULT:
                        retMsgId = R.string.msg_ret_fault;
                        break;
                    case RET_COMMAND:
                        retMsgId = R.string.msg_ret_command;
                        break;
                    case RET_MEMORY:
                        retMsgId = R.string.msg_ret_memory;
                        break;
                    case RET_USER_STOP:
                        retMsgId = R.string.msg_ret_user_stop;
                        break;
                    default:
                        break;
                }
                Toast.makeText(ZipProcess.this.context, retMsgId, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        thread = new Thread() {
            @Override
            public void run() {
                // 7z a '/storage/emulated/0/7z_demo/7zdemo.zip' '/storage/emulated/0/wifi_config.log' = 文件的压缩
                // 7z a '/storage/emulated/0/7z_demo/7zdemo.zip' '/storage/emulated/0/zp_7100' = 文件夹的压缩
                // 7z x '/storage/emulated/0/7z_demo/7zdemo.zip' '-o/storage/emulated/0/' -aoa  // 解压
                int ret = ZipUtils.executeCommand(ZipProcess.this.command);
                handler.sendEmptyMessage(ret); //send back return code
                super.run();
            }
        };
    }

    void start() {
        dialog.show();
        thread.start();
    }

}
