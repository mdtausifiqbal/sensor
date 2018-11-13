package com.mti.sensormanager;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import android.os.*;
public class DebugApplication extends Application {
	private UncaughtExceptionHandler uncaughtExceptionHandler;

    public void onCreate() {
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				public void uncaughtException(Thread thread, Throwable ex) {
					Intent intent = new Intent(DebugApplication.this.getApplicationContext(), DebugActivity.class);
					intent.setFlags(32768);
					intent.putExtra("error", DebugApplication.this.getStackTrace(ex));
					((AlarmManager) DebugApplication.this.getSystemService("alarm")).set(2, 1000, PendingIntent.getActivity(DebugApplication.this.getApplicationContext(), 11111, intent, 1073741824));
					Process.killProcess(Process.myPid());
					System.exit(2);
					DebugApplication.this.uncaughtExceptionHandler.uncaughtException(thread, ex);
				}
			});
        super.onCreate();
    }

    private String getStackTrace(Throwable th) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String stacktraceAsString = result.toString();
        printWriter.close();
        return stacktraceAsString;
    }
}