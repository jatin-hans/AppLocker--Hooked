package zynks.hooked;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LockActivity extends AppCompatActivity {

    EditText et1;
    Button b1;
    int a,i;
    public static int flag=0;
    public static int b;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        et1=(EditText)findViewById(R.id.editText1);
        b1=(Button)findViewById(R.id.button1);
        i=3;

        StringBuffer str = new StringBuffer();
        String string;
        try{
            BufferedReader inp = new BufferedReader(new InputStreamReader(openFileInput("flag.txt")));
            String input;

            while((input = inp.readLine())!=null){
                str.append(input );
            }
            string=str.toString();
            flag=Integer.parseInt(string);

        }catch(IOException e){
            e.printStackTrace();
        }

        et1.setText("");


        b1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if(flag==0){
                    String x=(et1.getText().toString());

                    if(x.equals("1234")){
                    finish();

                    }else{
                        Toast.makeText(getApplicationContext(), "Wrong Password ", Toast.LENGTH_LONG).show();
                        et1.setText("");
                        i--;
                        if(i<=0){

                            Toast.makeText(getApplicationContext(), "Camera gonna open", Toast.LENGTH_LONG).show();
                            Intent img=new Intent(getApplicationContext(),ImageActivity.class);
                            startActivity(img);
                            i=3;
                        }
                    }
                }else
                if(flag==1){
                    StringBuffer str = new StringBuffer();
                    String string;
                    try{
                        BufferedReader inp = new BufferedReader(new InputStreamReader(openFileInput("zynks1.txt")));
                        String input;

                        while((input = inp.readLine())!=null){
                            str.append(input );
                        }
                        string=str.toString();

                        if(et1.getText().toString().equals(string)){
                         finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Wrong Password ", Toast.LENGTH_LONG).show();
                            et1.setText("");
                            i--;
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }else
                if(flag==2){
                    Calendar cal = Calendar.getInstance();
                    int hour=cal.get(Calendar.HOUR_OF_DAY);
                    int min= cal.get(Calendar.MINUTE);
                    int a=hour*100 +min;

                    StringBuffer str = new StringBuffer();
                    String string;
                    try{
                        BufferedReader inp = new BufferedReader(new InputStreamReader(openFileInput("zynks2.txt")));
                        String input;

                        while((input = inp.readLine())!=null){
                            str.append(input );
                        }
                        string=str.toString();
                        int b= Integer.parseInt(string);
                        a=a+b;
                        String tim=String.valueOf(a);
                        String c=(et1.getText().toString());

                        if(c.equals(tim)){
                          finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Wrong Password ", Toast.LENGTH_LONG).show();
                            et1.setText("");
                            i--;
                            if(i<=0){

                                Toast.makeText(getApplicationContext(), "Camera gonna open", Toast.LENGTH_LONG).show();
                                Intent img=new Intent(getApplicationContext(),ImageActivity.class);
                                startActivity(img);
                                i=3;
                            }}
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                }
                else{

                    if(flag==3){
                        StringBuffer str = new StringBuffer();
                        String string;
                        try{
                            BufferedReader inp = new BufferedReader(new InputStreamReader(openFileInput("zynks.txt")));
                            String input;

                            while((input = inp.readLine())!=null){
                                str.append(input );
                            }
                            string=str.toString();

                            if(et1.getText().toString().equals(string)){
                               finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Wrong Password ", Toast.LENGTH_LONG).show();
                                et1.setText("");
                                i--;
                                if(i<=0){

                                    Toast.makeText(getApplicationContext(), "Camera gonna open", Toast.LENGTH_LONG).show();
                                    Intent img=new Intent(getApplicationContext(),ImageActivity.class);
                                    startActivity(img);
                                    i=3;
                                }
                            }
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }




                }
                et1.setText("");



            }}	);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_screen, menu);

        return true;
    }
    @Override
    public void onBackPressed() {

       Intent ex=new Intent(getApplicationContext(),LockActivity.class);
        startActivity(ex);


    }
@Override
    public void onPause(){

    finish();
    super.onPause();
}
}