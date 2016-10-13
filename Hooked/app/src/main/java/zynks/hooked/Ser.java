package zynks.hooked;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public  class Ser extends Service{



    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }



    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);



    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){


        final String str = "";
        Timer timer  =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            int phonelaunched = 0,phoneclosed =0;
            int phonelaunches = 1;

            @Override
            public void run() {


                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();




                for ( ActivityManager.RunningAppProcessInfo appProcess: runningAppProcessInfo ) {



                    if (appProcess.processName.equals("com.android.calendar")||appProcess.processName.equals("com.mediatek.filemanager")) {
                        if ( appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND  /*isForeground(getApplicationContext(),runningAppProcessInfo.get(i).processName)*/){
                            if (phonelaunched == 0 ){
                                phonelaunched = 1;

                                Intent dialogIntent = new Intent(getApplicationContext(),LockActivity.class)  ;
                                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(dialogIntent);
                            }
                            else if (phoneclosed == 1){
                                phonelaunches++;
                                phoneclosed = 0;
                                Log.d(String.valueOf(phonelaunches),"it was a counter");
                            }
                        }
                        else {
                            phoneclosed = 1;
                            Log.d(str,"phone has been closed");

                        }
                    }
                }
            }


        },100,100);

        return START_STICKY;
    }

}