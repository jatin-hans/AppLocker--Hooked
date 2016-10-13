package zynks.hooked;

import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {


    String[] string;
    int j=0;
    int p=0,q;
    private static int state=0;
    private LinearLayout mContent;
    // Main Content View of ScrollView
    private PackageManager pm;
    // To Get App and icon of the apps
    private ScrollView sv;
    // Main Scroll View of App List
    private LinearLayout mSuppler;
    // Suppler of AlmasPickerapp-debug.apk

    // On the Tab this will be shown
    private boolean DEBUG = true;
    // To Control Debug of The App
    private String TAG_NAME = "AlmasPicker";
    // To Sent Logs with this TAG
    private String PickerGetter = "PickGet";
    // To Indentify in sharedpreferance
    private String CurGet;
    // To Get the Current Choose App-
    private static SharedPreferences sec;
    private static int CurSor;




    android.support.v7.app.ActionBar actionbar;






    @Override
    protected void onCreate(Bundle RainOfAlmas) {

        super.onCreate(RainOfAlmas);


        Intent inservice=new Intent(getApplicationContext(),Ser.class);
        startService(inservice);

        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(0xff00BCD4));
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowTitleEnabled(true);
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'> <b> Choose an app </b> </font>"));
        actionbar.setDisplayHomeAsUpEnabled(true);


        mContent = new LinearLayout(this);
        mContent.setOrientation(LinearLayout.VERTICAL);
        mContent.setVerticalScrollBarEnabled(false);


        mSuppler = new LinearLayout(this);
        mSuppler.setOrientation(LinearLayout.VERTICAL);





        sec = PreferenceManager.getDefaultSharedPreferences(getBaseContext());



        sv = new ScrollView(getBaseContext());
        sv.addView(mContent);
        mSuppler.addView(sv);

        setContentView(mSuppler);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD || Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD_MR1) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        }


        SharedPreferences sp = getSharedPreferences("AlmasPicker", Context.MODE_PRIVATE);
        CurGet = sp.getString(PickerGetter, "");


        if (DEBUG) {
            if (CurGet == null | CurGet == "") {
                Log.e(TAG_NAME, "This is First View of Application List");
                Log.e(TAG_NAME, "Because No Saved Data");

            }
            else {
                Log.i(TAG_NAME, "Saved Data Has Found");
                Log.d(TAG_NAME, "Package Name is "+CurGet);
            }
        }


        ArrayList<ResolveInfo> apps = getApps();







        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo rInfo = apps.get(i);
            final Picker pick;


            PickerAdapter pa = new PickerAdapter(getApplicationContext(), null);

            String ss = rInfo.activityInfo.loadLabel(pm).toString();
            Drawable dd = rInfo.activityInfo.loadIcon(pm);
            pick = new Picker(ss,dd);
            pa.setName(pick.getName());
            pa.setIcon(pick.getIcon());



            pa.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                /*    if (state == 0) {
                        FileOutputStream fos1;
                        try {

                            fos1 = openFileOutput("status.txt", Context.MODE_APPEND);

                            fos1.write((pick.getName()).getBytes());
                            fos1.write("*".getBytes());
                            fos1.close();
                            state++;


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), (pick.getName() + " Locked"), Toast.LENGTH_LONG).show();

                    }else
                        Toast.makeText(getApplicationContext(), (pick.getName() + " UnLocked"), Toast.LENGTH_LONG).show();
               */ }





            });
            mContent.addView(pa);}
    }


    public void save(String key, String value) {

        Editor edit = sec.edit();
        edit.putString(key, value);
        edit.commit();

    }

    public class PickerAdapter extends LinearLayout {
        ImageView icon;
        TextView name;
        Switch sw;
        LayoutParams pro = new LayoutParams(95,85);
        public PickerAdapter(Context context, AttributeSet attrs) {
            super(context, attrs);
            icon = new ImageView(context);
            icon.setLayoutParams(pro);
            icon.setPadding(6, 2, 15, 2);
            addView(icon);
            name = new TextView(context);
            name.setPadding(6, 15, 2, 2);
            name.setTextSize(16);
            name.setTextColor(Color.BLACK);
            addView(name);
        }
        public void setName(String n) {
            name.setText(n);
        }
        public void setIcon(Drawable d) {
            icon.setImageDrawable(d);
        }
    }
    public class Picker {
        String ss;
        Drawable dd;
        public Picker(String name ,Drawable d) {
            ss = name;
            dd = d;
        }
        public String getName(){
            return ss;
        }
        public Drawable getIcon() {
            return dd;
        }
    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        View view = getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();

        lp.gravity = Gravity.CENTER;
        lp.width = android.view.ViewGroup.LayoutParams.FILL_PARENT;
        lp.height = android.view.ViewGroup.LayoutParams.FILL_PARENT;

        getWindowManager().updateViewLayout(view, lp);
    }

    public int getWidth() {
        DisplayMetrics di = getBaseContext().getResources().getDisplayMetrics();
        int w = (di.widthPixels/2)+100;
        return w;
    }
    public int getHeight() {
        DisplayMetrics di = getBaseContext().getResources().getDisplayMetrics();
        int h = (di.heightPixels/2)+110;
        return h;
    }




    private ArrayList<ResolveInfo> getApps(){

        pm = getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        ArrayList<ResolveInfo> list = (ArrayList<ResolveInfo>) pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);
        return list;
    }
    public static int getCurSor() {
        return CurSor;
    }
    public static void setCurSor(int curSor) {
        CurSor = curSor;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");

        alertDialogBuilder
                .setMessage("Click Yes to Exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Hooked:
                Intent in1=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in1);
                break;
            case R.id.lock_settings:
                Intent in=new Intent(getApplicationContext(),LockSelActivity.class);
                startActivity(in);
                break;
            case R.id.Recovery:
                Intent in3=new Intent(getApplicationContext(),RecoveryActivity.class);
                startActivity(in3);
                break;
            case R.id.About:
                Intent in2=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(in2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);


    }


}
