package zynks.hooked;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.AsyncTask;

import java.util.TimerTask;

public class Timer1 extends AsyncTask<Void, Void, Void> {
    Context mContext;
    private Handler threadHandler;
    public Timer1(Context context,Handler threadHandler) {
        super();
        this.threadHandler=threadHandler;
        mContext = context;
    }
    @Override
    protected Void doInBackground(Void...params) {
        try {
            Thread.sleep(ImageActivity.PERIOD);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Message.obtain(threadHandler, ImageActivity.DONE, "").sendToTarget();
        return null;
    }


}
