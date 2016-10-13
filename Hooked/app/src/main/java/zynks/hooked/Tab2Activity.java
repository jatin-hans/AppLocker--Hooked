package zynks.hooked;

import android.support.v7.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tab2Activity extends AppCompatActivity {
    Button sb;
    EditText p,rp;
    public static int pin=0,rpin=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        sb=(Button)findViewById(R.id.button1);
        p=(EditText)findViewById(R.id.editText1);
        rp=(EditText)findViewById(R.id.editText2);
        p.setText("");
        rp.setText("");

        sb.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                pin=Integer.parseInt(p.getText().toString());
                rpin=Integer.parseInt(rp.getText().toString());
                if(p.getText().toString().equals("")&&rp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Pin", Toast.LENGTH_LONG).show();
                }
                else{
                    if(pin==rpin){
                        Toast.makeText(getApplicationContext(), "Pin Changed", Toast.LENGTH_LONG).show();

                        String data = p.getText().toString();
                        FileOutputStream fos;
                        try{

                            fos= openFileOutput("zynks1.txt",Context.MODE_PRIVATE);

                            fos.write(data.getBytes());
                            fos.close();


                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        p.setText("");
                        rp.setText("");
                        String dat = "1";
                        FileOutputStream fo;
                        try{

                            fo= openFileOutput("flag.txt",Context.MODE_PRIVATE);

                            fo.write(dat.getBytes());
                            fo.close();


                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        Intent in=new Intent(getApplicationContext(),WelcomeScreen.class);
                        startActivity(in);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Pin not matchs", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_screen, menu);
        return true;
    }

}
