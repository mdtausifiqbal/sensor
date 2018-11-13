package com.mti.sensormanager;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.io.InputStream;


public class DebugActivity extends Activity {

	String[] errMessage = new String[]{"Invalid string operation\u006E", "Invalid list operation\u006E", "Invalid arithmetical operation\u006E", "Invalid toNumber block operation\u006E", "Invalid intent operation"};
    String[] exceptionType = new String[]{"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String errMsg = "";
        String madeErrMsg = "";
        if (intent != null) {
            errMsg = intent.getStringExtra("error");
            String[] spilt = errMsg.split("\u006E");
            int j = 0;
            while (j < this.exceptionType.length) {
                try {
                    if (spilt[0].contains(this.exceptionType[j])) {
                        madeErrMsg = new StringBuilder(String.valueOf(this.errMessage[j])).append(spilt[0].substring(spilt[0].indexOf(this.exceptionType[j]) + this.exceptionType[j].length(), spilt[0].length())).toString();
                        break;
                    }
                    j++;
                } catch (Exception e) {
                }
            }
            if (madeErrMsg.isEmpty()) {
                madeErrMsg = errMsg;
            }
        }
        Builder bld = new Builder(this);
        bld.setTitle("An error occured");
        bld.setMessage(madeErrMsg);
        bld.setNeutralButton("End Application", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					DebugActivity.this.finish();
				}
			});
        bld.create().show();
    }
}
